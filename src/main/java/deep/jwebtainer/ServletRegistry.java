package deep.jwebtainer;

import deep.jwebtainer.servlet.Servlet;

/**
 * The {@code ServletRegistry} interface defines methods for managing servlets
 * in a web application. It allows for registering servlets with specific paths,
 * retrieving them by path, and destroying all registered servlets.
 */
public interface ServletRegistry {

    /**
     * Registers a servlet with a specific path.
     *
     * @param path    the URL path to associate with the servlet
     * @param servlet the servlet instance to register
     * @throws IllegalArgumentException if a servlet is already registered
     * for the given path
     */
    void registerServlet(String path, Servlet servlet);

    /**
     * Retrieves the servlet registered for the specified path.
     *
     * @param path the URL path associated with the servlet
     * @return the registered servlet instance, or {@code null} if no
     * servlet is registered for the path
     */
    Servlet getServlet(String path);

    /**
     * Destroys all registered servlets by invoking their
     * {@link Servlet#destroy()} method.
     * This should typically be called during container shutdown to ensure
     * resource cleanup.
     */
    void destroyAll();
}
