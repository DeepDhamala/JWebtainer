package deep.jwebtainer;

import java.io.IOException;

/**
 * Interface for loading servlets into the {@link DefaultServletRegistry}.
 * Implementations of this interface should define how servlets are loaded,
 * typically from a configuration file or other source.
 */
public interface ServletLoader {

    /**
     * Loads servlets into the provided {@link DefaultServletRegistry}.
     * @param registry the {@link DefaultServletRegistry} where servlets will
     *                be registered
     * @throws IOException if an I/O error occurs while loading the servlets
     */
    void load(DefaultServletRegistry registry) throws IOException;
}
