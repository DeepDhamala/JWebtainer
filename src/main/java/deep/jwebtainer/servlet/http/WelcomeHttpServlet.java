package deep.jwebtainer.servlet.http;

public class WelcomeHttpServlet extends HttpServlet {

    /**
     * Handles HTTP GET requests by serving an HTML response for the JWebtainer framework welcome page.
     * This method generates and writes an HTML document to the response, displaying an overview,
     * features, usage examples, and contact information for JWebtainer.
     *
     * @param request  the HttpServletRequest object that contains the request the client has made
     * @param response the HttpServletResponse object that contains the response the servlet sends back to the client*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setHeader("Content-Type", "text/html; charset=UTF-8");
            response.getWriter().write("""
                    <!DOCTYPE html>
                    <html lang='en'>
                    <head>
                      <meta charset='UTF-8'>
                      <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                      <title>Welcome to JWebtainer</title>
                      <style>
                        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; background: #f4f7f9; color: #333; }
                        header { background-color: #003366; color: white; padding: 20px 40px; text-align: center; }
                        h1 { margin: 0; font-size: 3em; }
                        nav { margin-top: 10px; }
                        nav a { color: #a9d6ff; margin: 0 15px; text-decoration: none; font-weight: bold; }
                        nav a:hover { text-decoration: underline; }
                        main { max-width: 900px; margin: 30px auto; padding: 0 20px; }
                        section { margin-bottom: 50px; }
                        h2 { color: #003366; border-bottom: 2px solid #0055aa; padding-bottom: 5px; }
                        p { line-height: 1.6; font-size: 1.1em; }
                        code { background-color: #eaeaea; padding: 3px 6px; border-radius: 3px; font-family: monospace; }
                        pre { background-color: #eaeaea; padding: 15px; border-radius: 6px; overflow-x: auto; }
                        footer { background-color: #003366; color: white; text-align: center; padding: 20px; position: fixed; bottom: 0; width: 100%; }
                        ul { list-style: disc inside; }
                      </style>
                    </head>
                    <body>
                    
                    <header>
                      <h1>JWebtainer</h1>
                      <nav>
                        <a href='#overview'>Overview</a>
                        <a href='#features'>Features</a>
                        <a href='#usage'>Usage</a>
                        <a href='#contact'>Contact</a>
                      </nav>
                    </header>
                    
                    <main>
                    
                    <section id='overview'>
                      <h2>Welcome to JWebtainer</h2>
                      <p>
                        JWebtainer is a lightweight, customizable Java web server and servlet container framework. 
                        It is designed for developers who want full control over the request lifecycle, servlet management, and response handling without the bloat of heavy application servers.
                      </p>
                      <p>
                        Built from scratch, JWebtainer supports HTTP/1.1, servlet API essentials, and a simple but extensible servlet registration mechanism. 
                        Whether you are building microservices, learning how web servers work, or prototyping embedded server solutions, JWebtainer provides a clean, understandable foundation.
                      </p>
                    </section>
                    
                    <section id='features'>
                      <h2>Key Features</h2>
                      <ul>
                        <li>Simple socket-based HTTP request handling with multithreaded processing.</li>
                        <li>Custom servlet registry for easy servlet deployment and mapping.</li>
                        <li>HttpServletRequest and HttpServletResponse abstraction for ease of use.</li>
                        <li>Support for GET, POST methods and header management.</li>
                        <li>Static resource serving utilities.</li>
                        <li>Clean API inspired by standard Java Servlet specs but lightweight.</li>
                        <li>Extensible to add filters, session management, or other servlet features.</li>
                      </ul>
                    </section>
                    <section id='formtest'>
                                          <h2>Test POST Request</h2>
                                          <form method='POST'>
                                            <label for='username'>Username:</label><br>
                                            <input type='text' id='username' name='username' required><br><br>
                                            <label for='age'>Age:</label><br>
                                            <input type='number' id='age' name='age' required><br><br>
                                            <button type='submit'>Submit</button>
                                          </form>
                                        </section>
                    <section id='usage'>
                      <h2>Getting Started</h2>
                      <p>Here is a minimal servlet example in JWebtainer:</p>
                      <pre><code>package deep.jwebtainer.servlet.http;
                    public class HelloWorldServlet extends HttpServlet {
                        @Override
                        protected void doGet(HttpServletRequest request, HttpServletResponse response) {
                            try {
                                response.setHeader("Content-Type", "text/html; charset=UTF-8");
                                response.getWriter().write("&lt;h1&gt;Hello, JWebtainer!&lt;/h1&gt;");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }</code></pre>
                      <p>To register your servlet, add it to the servlet registry in your server bootstrap:</p>
                      <pre><code>servletRegistry.registerServlet("/hello", new HelloWorldServlet());</code></pre>
                    </section>
                    
                    <section id='contact' style='margin-bottom: 200px;'>
                      <h2>Contact &amp; Contribution</h2>
                      <p>
                        JWebtainer is open source and welcomes contributions from the community. Feel free to 
                        fork the repo, report issues, or submit pull requests on GitHub.
                      </p>
                      <p>Email: <a href='mailto:dev@deepdhamala.com.np'>dev@deepdhamala.com.np</a></p>
                    </section>
                    </main>
                    
                    <footer>
                      <p>© 2025 JWebtainer — Lightweight Java Web Server & Servlet Container</p>
                    </footer>
                    
                    </body>
                    </html>""");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Handles HTTP POST requests from the welcome page form submission.
     * Processes username and age parameters from the form data and generates
     * an HTML response displaying the submitted information in a styled page.
     * The response includes the submitted values formatted in a user-friendly layout
     * with navigation links and consistent styling matching the welcome page theme.
     *
     * @param request  the HttpServletRequest object containing the form parameters
     * @param response the HttpServletResponse object for sending the HTML response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = request.getParameter("username");
            String age = request.getParameter("age");

            response.setHeader("Content-Type", "text/html; charset=UTF-8");
            response.getWriter().write("""
                <!DOCTYPE html>
                <html lang='en'>
                <head>
                  <meta charset='UTF-8'>
                  <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                  <title>Form Result - JWebtainer</title>
                  <style>
                    body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; background: #f4f7f9; color: #333; }
                    header { background-color: #003366; color: white; padding: 20px 40px; text-align: center; }
                    h1 { margin: 0; font-size: 3em; }
                    nav { margin-top: 10px; }
                    nav a { color: #a9d6ff; margin: 0 15px; text-decoration: none; font-weight: bold; }
                    nav a:hover { text-decoration: underline; }
                    main { max-width: 900px; margin: 30px auto; padding: 0 20px; text-align: center; }
                    section { margin-bottom: 50px; }
                    h2 { color: #003366; border-bottom: 2px solid #0055aa; padding-bottom: 5px; }
                    p { line-height: 1.6; font-size: 1.2em; }
                    footer { background-color: #003366; color: white; text-align: center; padding: 20px; position: fixed; bottom: 0; width: 100%; }
                    .highlight { font-weight: bold; color: #0055aa; }
                    button, a.button { background-color: #0055aa; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; }
                    button:hover, a.button:hover { background-color: #003f80; }
                  </style>
                </head>
                <body>
                
                <header>
                  <h1>Form Result</h1>
                  <nav>
                    <a href='/'>Home</a>
                    <a href='#'>Docs</a>
                  </nav>
                </header>
                
                <main>
                  <section>
                    <h2>Submission Successful ✅</h2>
                    <p>Hi <span class='highlight'>""" + username + """
                    </span>, nice to meet you!</p>
                    <p>You are <span class='highlight'>""" + age + """
                    </span> years old.</p>
                    <p><a href='/' class='button'>Back to Home</a></p>
                  </section>
                </main>
                
                <footer>
                  <p>© 2025 JWebtainer — Lightweight Java Web Server & Servlet Container</p>
                </footer>
                
                </body>
                </html>
                """);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}