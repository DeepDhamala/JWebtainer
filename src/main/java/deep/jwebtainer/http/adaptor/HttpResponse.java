package deep.jwebtainer.http.adaptor;

import deep.jwebtainer.servlet.http.HttpServletResponse;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents an HTTP response in the JWebtainer framework.
 * This class implements the HttpServletResponse interface and provides methods
 * to send response data back to the client through an output stream.
 */
public class HttpResponse implements HttpServletResponse {

    /**
     * The status code of the HTTP response. Defaults to 200 (OK).
     */
    private int statusCode = 200;

    /**
     * The status message corresponding to the status code. Defaults to "OK".
     */
    private String statusMessage = "OK";

    /**
     * The output stream used to write the raw response data.
     */
    private final OutputStream out;

    /**
     * PrintWriter wrapper around the output stream for text-based writing.
     */
    private final PrintWriter printWriter;

    /**
     * Map containing the response headers with their values.
     */
    private final Map<String, String> headers = new LinkedHashMap<>();

    /**
     * Constructs a new HttpResponse with the specified output stream.
     *
     * @param out the output stream to write the response to
     */
    public HttpResponse(OutputStream out) {
        this.out = out;
        this.printWriter = new PrintWriter(out);
    }

    /**
     * Sets the HTTP status code for this response.
     *
     * @param statusCode the status code to set
     */
    @Override
    public void setStatus(int statusCode) {
        this.statusCode = statusCode;
        this.statusMessage = resolveStatusMessage(statusCode);
    }

    /**
     * Sets a response header with the given name and value.
     *
     * @param name  the name of the header
     * @param value the value of the header
     */
    @Override
    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    /**
     * Sends a redirect response to the specified location.
     *
     * @param location the URL to redirect to
     */
    @Override
    public void sendRedirect(String location) {
        setStatus(302);
        setHeader("Location", location);
        flushHeaders(); // send headers immediately for redirect
    }

    /**
     * Gets the PrintWriter for writing character data to the response.
     *
     * @return the PrintWriter for this response
     */
    @Override
    public PrintWriter getWriter() {
        flushHeaders();
        return printWriter;
    }

    /**
     * Gets the underlying output stream for writing raw data.
     *
     * @return the output stream for this response
     */
    public OutputStream getOutputStream() {
        return out;
    }

    /**
     * Flushes the response headers and content to the client.
     */
    public void flush() {
        printWriter.flush();
    }

    /**
     * Writes the response headers to the output stream.
     */
    private void flushHeaders() {
        printWriter.printf("HTTP/1.1 %d %s\r\n", statusCode, statusMessage);
        String contentType = "text/html";
        headers.putIfAbsent("Content-Type", contentType);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            printWriter.printf("%s: %s\r\n", entry.getKey(), entry.getValue());
        }
        printWriter.print("\r\n"); // End of headers
    }

    /**
     * Resolves the appropriate status message for a given status code.
     *
     * @param statusCode the HTTP status code
     * @return the corresponding status message
     */
    private String resolveStatusMessage(int statusCode) {
        return switch (statusCode) {
            case 200 -> "OK";
            case 201 -> "Created";
            case 204 -> "No Content";
            case 301 -> "Moved Permanently";
            case 302 -> "Found";
            case 400 -> "Bad Request";
            case 401 -> "Unauthorized";
            case 403 -> "Forbidden";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            default -> "HTTP Status " + statusCode;
        };
    }
}
