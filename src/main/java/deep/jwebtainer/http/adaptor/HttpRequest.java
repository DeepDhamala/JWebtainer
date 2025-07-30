package deep.jwebtainer.http.adaptor;

import deep.jwebtainer.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an HTTP request in the JWebtainer framework.
 * This class implements the HttpServletRequest interface and provides methods
 * to access request details such as method, URI, headers, and parameters.
 */
public class HttpRequest implements HttpServletRequest {

    /**
     * The HTTP method of the request (e.g., GET, POST).
     */
    private String method;

    /**
     * The request URI of the HTTP request.
     */
    private String requestURI;

    /**
     * A map to hold HTTP headers, where the key is the header name and the
     * value is an array of header values.
     */
    private final Map<String, String[]> headers = new HashMap<>();

    /**
     * A map to hold request parameters, where the key is the parameter name and
     * the value is an array of parameter values.
     */
    private final Map<String, String[]> parameters = new HashMap<>();


    /**
     * Returns the HTTP method of the request.
     *
     * @return the HTTP method as a String (e.g., "GET", "POST")
     */
    @Override
    public String getMethod() {
        return method;
    }


    /**
     * Returns the request URI that the client used when making the request.
     * The URI is the part of the request URL that follows the domain name.
     *
     * @return the request URI as a String, or null if not set
     */
    @Override
    public String getRequestURI() {
        return requestURI;
    }

    /**
     * Returns the value of the specified request header as a String.
     * If multiple values exist for the header, returns the first value.
     *
     * @param name the name of the header to retrieve
     * @return the header value as a String, or null if the header does not
     * exist
     */
    @Override
    public String getHeader(String name) {
        String[] values = headers.get(name);
        return (values != null && values.length > 0) ? values[0] : null;
    }

    /**
     * Returns the value of the specified request parameter as a String.
     * If multiple values exist for the parameter, returns the first value.
     *
     * @param name the name of the parameter to retrieve
     * @return the parameter value as a String, or null if the parameter does
     * not exist
     */
    @Override
    public String getParameter(String name) {
        String[] values = parameters.get(name);
        return (values != null && values.length > 0) ? values[0] : null;
    }

    /**
     * Returns the value of the specified request attribute.
     * This implementation always returns null as attributes are not yet
     * supported.
     *
     * @param name the name of the attribute to retrieve
     * @return null as attributes are not yet supported
     */
    @Override
    public Object getAttribute(String name) {
        return null;
    }

    /**
     * Sets the HTTP method for this request.
     *
     * @param method the HTTP method to set (e.g., "GET", "POST")
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Sets the request URI for this request.
     *
     * @param requestURI the URI to set
     */
    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    /**
     * Adds a parameter to the request.
     * If a parameter with the same name already exists, it will be replaced.
     *
     * @param name  the name of the parameter
     * @param value the value of the parameter
     */
    public void addParameter(String name, String value) {
        parameters.put(name, new String[]{value});
    }

    /**
     * Adds a header to the request.
     * If a header with the same name already exists, it will be replaced.
     *
     * @param headerName  the name of the header
     * @param headerValue the value of the header
     */
    public void addHeader(String headerName, String headerValue) {
        headers.put(headerName, new String[]{headerValue});
    }
}
