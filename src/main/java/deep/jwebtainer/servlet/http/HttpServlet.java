package deep.jwebtainer.servlet.http;

import deep.jwebtainer.http.HttpMethod;
import deep.jwebtainer.servlet.GenericServlet;
import deep.jwebtainer.servlet.ServletException;
import deep.jwebtainer.servlet.ServletRequest;
import deep.jwebtainer.servlet.ServletResponse;

/**
 * The <code>HttpServlet</code> class extends the <code>GenericServlet</code>
 * class and provides an abstract class for handling HTTP requests in a web
 * application. It is designed to be subclassed by developers to implement
 * specific HTTP request handling logic.
 */
public abstract class HttpServlet extends GenericServlet {

    /**
     * Process the HTTP GET request. This method is called by the servlet
     * @param request
     * @param response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        // TODO: Send a Error Response not just throw an exception
        throw new UnsupportedOperationException("GET method not implemented");
    }

    /**
     * Process the HTTP POST request. This method is called by the servlet
     * @param request
     * @param response
     */
    protected void doPost(ServletRequest request, ServletResponse response) {
        // TODO: Send a Error Response not just throw an exception
        throw new UnsupportedOperationException("POST method not implemented");
    }

    /**
     * Delegates the request to
     * {@link #service(HttpServletRequest, HttpServletResponse)} for HTTP
     * processing.
     *
     * @param request  The servlet request object containing client request
     *                 data.
     * @param response The servlet response object to send data back to the
     *                 client.
     * @throws ServletException if an error occurs during processing of the
     * request.
     */
    @Override
    public void service(ServletRequest request,
                        ServletResponse response)
            throws ServletException {

        if (!(request instanceof HttpServletRequest httpRequest
                && response instanceof HttpServletResponse httpResponse)) {
            throw new ServletException("Not an HTTP request/response pair");
        }

        service(httpRequest, httpResponse);
    }

    /**
     * Processes the incoming HTTP request and generates a response.
     *
     * @param request  The HTTP servlet request object containing client
     *                request data.
     * @param response The HTTP servlet response object to send data back
     *                to the client.
     * @throws ServletException if an error occurs during processing of the
     * request.
     */
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException {

        String methodName = request.getMethod();

        HttpMethod httpMethod;
        httpMethod = HttpMethod.valueOf(methodName);

        switch (httpMethod) {
            case GET -> doGet(request, response);
            case POST -> doPost(request, response);
            default ->
                // TODO: Send a Error Response not just throw an exception
                    throw new ServletException("Method not Supported: "
                            + methodName);
        }
    }

}
