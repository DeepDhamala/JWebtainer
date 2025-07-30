package deep.jwebtainer;

import deep.jwebtainer.servlet.http.HttpServlet;
import deep.jwebtainer.servlet.http.WelcomeHttpServlet;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PropertiesServletLoaderTest {

    @Test
    void shouldLoadServletsFromPropertiesFile() throws Exception {

        ServletFactory factory = mock(ServletFactory.class);
        DefaultServletRegistry registry = mock(DefaultServletRegistry.class);
        HttpServlet dummyServlet = mock(WelcomeHttpServlet.class);

        when(factory.createServlet("deep.jwebtainer.servlet.http.WelcomeHttpServlet"))
                .thenReturn(dummyServlet);

        PropertiesServletLoader loader = new PropertiesServletLoader(
                "servlet.properties", factory
        );

        loader.load(registry);
        verify(factory).createServlet("deep.jwebtainer.servlet.http" +
                ".WelcomeHttpServlet");
        verify(registry).registerServlet("/", dummyServlet);

    }

    @Test
    void shouldThrowIfPropertiesFileIsMissing() {
        ServletFactory factory = mock(ServletFactory.class);
        DefaultServletRegistry registry = mock(DefaultServletRegistry.class);

        PropertiesServletLoader loader = new PropertiesServletLoader(
                "nonexistent.properties", factory
        );

        assertThrows(IOException.class, () -> loader.load(registry));
    }

}
