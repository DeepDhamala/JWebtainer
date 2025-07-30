package deep.jwebtainer;

import deep.jwebtainer.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Implementation of {@link ServletLoader} that loads servlets from a
 * properties file.
 * The properties file should map servlet names to their fully qualified
 * class names.
 */
public class PropertiesServletLoader implements ServletLoader {

    /**
     * The name of the properties file containing servlet configurations.
     */
    private final String configFileName;

    /**
     * The factory used to create servlet instances.
     */
    private final ServletFactory servletFactory;

    /**
     * Constructs a new PropertiesServletLoader with the specified
     * configuration  file name and servlet factory.
     *
     * @param configFileName the name of the properties file containing
     *                       servlet configurations
     * @param servletFactory the factory used to create servlet instances
     */
    public PropertiesServletLoader(String configFileName,
                                   ServletFactory servletFactory) {
        this.configFileName = configFileName;
        this.servletFactory = servletFactory;
    }

    /**
     * Loads servlets from the specified properties file and registers them
     * in the provided registry.
     *
     * @param registry the {@link DefaultServletRegistry} where servlets will
     *                be registered
     * @throws IOException if an I/O error occurs while loading the
     * properties file
     */
    @Override
    public void load(DefaultServletRegistry registry) throws IOException {
        try (InputStream input =
                     getClass().getClassLoader()
                             .getResourceAsStream(configFileName)) {
            if (input == null) {
                throw new IOException("Configuration file not found: "
                        + configFileName);
            }
            Properties properties = new Properties();
            properties.load(input);
            properties.forEach((key, value) -> {
                String className = (String) value;
                HttpServlet servlet = servletFactory.createServlet(className);
                registry.registerServlet((String) key, servlet);
            });
        } catch (IOException e) {
            throw new IOException("Error loading servlets from "
                    + "configuration file: " + configFileName, e);
        }
    }
}
