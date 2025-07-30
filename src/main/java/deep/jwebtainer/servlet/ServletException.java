package deep.jwebtainer.servlet;

import java.io.Serial;

/**
 * The <code>ServletException</code> class represents an exception that can be
 * thrown by a servlet during its operation. It extends the standard Java
 * Exception class.
 * This exception is typically used to indicate issues that occur while
 * processing a request, such as errors in request handling or response
 * generation.
 */
public class ServletException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ServletException with no detail message.
     */
    public ServletException() {
        super();
    }

    /**
     * Constructs a new ServletException with the specified detail message.
     *
     * @param message the detail message
     */
    public ServletException(String message) {
        super(message);
    }

    /**
     * Constructs a new ServletException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public ServletException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new ServletException with the specified detail message
     * and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public ServletException(String message, Throwable cause) {
        super(message, cause);
    }
}
