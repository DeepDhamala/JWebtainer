package deep.jwebtainer;

import deep.jwebtainer.servlet.http.HttpServlet;

/**
 * Factory class for creating instances of servlets.
 * This class provides a method to create a servlet instance based on its
 * class name.
 * @see HttpServlet
 */
public class ServletFactory {
    /**
     * Creates a new instance of a servlet by its class name.
     *
     * @param className the fully qualified class name of the servlet
     * @return an instance of the servlet
     * @throws IllegalArgumentException if the class cannot be found or
     * instantiated
     */
    public HttpServlet createServlet(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return (HttpServlet) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to create servlet: "
                    + className, e);
        }
    }
}
