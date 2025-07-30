package deep.jwebtainer;

import deep.jwebtainer.servlet.Servlet;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link ServletRegistry} interface.
 * This class manages the registration and retrieval of servlets by their path.
 * It also provides a method to destroy all registered servlets.
 */
public class DefaultServletRegistry implements ServletRegistry {

    /**
     * A map that holds the servlet mappings, where the key is the path and the
     * value is the servlet instance.
     */
    private final Map<String, Servlet> servletMappings = new HashMap<>();

    /**
     * Registers a servlet with a specific path.
     *
     * @param path    the URL path to associate with the servlet
     * @param servlet the servlet instance to register
     * @throws IllegalArgumentException if a servlet is already registered for
     * the given path
     */
    @Override
    public void registerServlet(String path, Servlet servlet) {
        if (servletMappings.containsKey(path)) {
            throw new IllegalArgumentException("Servlet already registered "
                    + "for path: " + path);
        }
        servletMappings.put(path, servlet);
        servlet.init();
    }

    /**
     * Retrieves the servlet registered for the specified path.
     *
     * @param path the URL path associated with the servlet
     * @return the registered servlet instance, or {@code null} if no servlet is
     * registered for the path
     */
    @Override
    public Servlet getServlet(String path) {
        return servletMappings.get(path);
    }

    /**
     * Destroys all registered servlets by invoking their
     * {@link Servlet#destroy()} method. This should typically be called
     * during container shutdown to ensure resource cleanup.
     */
    @Override
    public void destroyAll() {
        for (Servlet servlet : servletMappings.values()) {
            servlet.destroy();
        }
    }
}
