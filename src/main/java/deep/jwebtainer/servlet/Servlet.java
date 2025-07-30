package deep.jwebtainer.servlet;

/**
 * The <code>Servlet</code> interface defines the basic contract for a servlet
 * in a web application. It is a Java-based component that dynamically processes
 * requests and generates responses.
 */
public interface Servlet {

    /**
     * This method is called by the servlet container once after a servlet gets
     * instantiated.
     */
    void init();

    /**
     * Processes the incoming request and generates a response.
     *
     * @param request  The servlet request object containing client
     *                 request data.
     * @param response The servlet response object to send data back to
     *                 the client.
     * @throws ServletException if an error occurs during processing of
     * the request.
     */
    void service(ServletRequest request, ServletResponse response)
            throws  ServletException;

    /**
     * Clean up resources and perform any necessary shutdown operations.
     */
    void destroy();
}
