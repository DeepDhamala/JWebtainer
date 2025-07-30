package deep.jwebtainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public final class Main {

    /**
     * Private constructor to prevent instantiation of the Main class.
     * This class is designed to be a utility class with a static main method,
     * and should not be instantiated.
     */
    private Main() {
        // Private constructor to prevent instantiation
    }

    /**
     * The main class for the JWebtainer framework.
     * This class initializes
     * the servlet loader and the web container
     * and starts the web application.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    /**
     * The port on which the web container will listen for incoming requests.
     * This is set to 8080 by default, but can be changed in the configuration.
     */
    private static final int PORT = 8080;

    /**
     * The main method serves as the entry point for the JWebtainer framework.
     * It initializes the servlet loader, loads the servlets into the registry,
     * and starts the web container to handle incoming requests.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ServletLoader servletLoader =
                new PropertiesServletLoader("servlet.properties",
                        new ServletFactory());
        DefaultServletRegistry registry = new DefaultServletRegistry();
        try {
            servletLoader.load(registry);
        } catch (Exception e) {
            LOGGER.error("Error loading servlets {}", e.getMessage());
        }
        WebContainer webContainer = new WebContainer(PORT, registry);
        try {
            webContainer.start();
        } catch (IOException e) {
            LOGGER.error("Error starting WebContainer: {}", e.getMessage());
        }
        LOGGER.info("WebContainer started successfully on port 8080");
    }
}
