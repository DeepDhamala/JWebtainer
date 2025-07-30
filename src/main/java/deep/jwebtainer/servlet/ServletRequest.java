package deep.jwebtainer.servlet;

/**
 * Represents a request sent to a servlet.
 * This interface provides methods to retrieve request parameters and
 * attributes.
 */
public interface ServletRequest {

    /**
     * Retrieves the value of a request parameter as a String.
     *
     * @param name the name of the parameter to retrieve
     * @return the value of the parameter, or null if the parameter does not
     * exist
     */
    String getParameter(String name);

    /**
     * Retrieves the value of a request parameter as an array of Strings.
     *
     * @param name the name of the parameter to retrieve
     * @return an array of String values for the parameter, or null if the
     * parameter does not exist
     */
    Object getAttribute(String name);
}
