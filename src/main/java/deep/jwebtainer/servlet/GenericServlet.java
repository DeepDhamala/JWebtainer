package deep.jwebtainer.servlet;

/**
 * The <code>GenericServlet</code> class is a base class for creating servlets
 * in a web application. It provides a simple implementation of the
 * <code>Servlet</code> interface, allowing developers to focus on the
 * specific logic of their servlets without worrying about the underlying
 * servlet container details.
 */
public abstract class GenericServlet implements Servlet {

    /**
     * Initializes the servlet. This method is called by the servlet container
     * once after the servlet instance is created.
     */
    @Override
    public void init() { }

    /**
     * Processes the incoming request and generates a response.
     *
     * @param request  The servlet request object containing client request
     *                data.
     * @param response The servlet response object to send data back to the
     *                client.
     * @throws ServletException if an exception occurs during a normal servlet
     *          operation.
     */
    @Override
    public abstract void service(ServletRequest request,
                                 ServletResponse response)
            throws ServletException;

    /**
     * Called by the servlet container to clean up resources associated with
     * the servlet. See {@link Servlet#destroy()}.
     */
    @Override
    public void destroy() { }


}
