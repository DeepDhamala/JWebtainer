package deep.jwebtainer.servlet.http;

import deep.jwebtainer.servlet.ServletResponse;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Represents an HTTP response sent by a servlet.
 * This interface extends the ServletResponse interface to provide additional
 * methods specific to HTTP responses.
 */
public interface HttpServletResponse extends ServletResponse {
    /**
     * Sets the status code for the HTTP response.
     *
     * @param statusCode the HTTP status code to set
     */
    void setStatus(int statusCode);

    /**
     * Sets a header in the HTTP response.
     *
     * @param name  the name of the header
     * @param value the value of the header
     */
    void setHeader(String name, String value);

    /**
     * Sends a redirect response to the client.
     *
     * @param location the URL to redirect to
     */
    void sendRedirect(String location);

    /**
     * Gets the PrintWriter for writing character data to the response.
     *
     * @return the PrintWriter associated with this response
     */
    PrintWriter getWriter();

    /**
     * Gets the output stream for writing binary data to the response.
     *
     * @return the OutputStream associated with this response
     */
    OutputStream getOutputStream();
}
