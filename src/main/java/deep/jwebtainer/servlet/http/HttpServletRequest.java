package deep.jwebtainer.servlet.http;

import deep.jwebtainer.servlet.ServletRequest;

/**
 * Represents an HTTP request sent to a servlet.
 * This interface extends the ServletRequest interface to provide additional
 * methods specific to HTTP requests.
 */
public interface HttpServletRequest extends ServletRequest {
    /**
     * Retrieves the HTTP method used for the request (e.g., GET, POST).
     *
     * @return the HTTP method as a String
     */
    String getMethod();

    /**
     * Retrieves the requested URI.
     *
     * @return the requested URI as a String
     */
    String getRequestURI();

    /**
     * Retrieves the value of a request header.
     *
     * @param name the name of the header to retrieve
     * @return the value of the header, or null if the header does not exist
     */
    String getHeader(String name);
}
