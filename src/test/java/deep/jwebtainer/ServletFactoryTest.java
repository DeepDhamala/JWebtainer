package deep.jwebtainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServletFactoryTest {

    ServletFactory factory = new ServletFactory();

    @Test
    void createServlet_validServlet_shouldReturnInstance(){
        String servletClassName = "deep.jwebtainer.servlet.http" +
                ".WelcomeHttpServlet";
        var servlet = factory.createServlet(servletClassName);
        assertNotNull(servlet, "Servlet instance should not be null");
        assertEquals("deep.jwebtainer.servlet.http.WelcomeHttpServlet",
                servlet.getClass().getName(), "Servlet class should match");
    }

    @Test
    void createServlet_nonexistentClass_shouldThrow(){
        String servletClassName = "deep.jwebtainer.servlet.http.NonExistentServlet";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createServlet(servletClassName);
        });
        assertTrue(exception.getMessage().contains("Failed to create servlet: " + servletClassName),
                "Exception message should indicate failure to create servlet");
    }

    @Test
    void createServlet_invalidClass_shouldThrow(){
        String servletClassName = "java.lang.String"; // Not a servlet class
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createServlet(servletClassName);
        });
        assertTrue(exception.getMessage().contains("Failed to create servlet: " + servletClassName),
                "Exception message should indicate failure to create servlet");
    }

    @Test
    void createServlet_noDefaultConstructor_shouldThrow(){
        String servletClassName = "deep.jwebtainer.servlet.http.NoDefaultConstructorServlet";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createServlet(servletClassName);
        });
        assertTrue(exception.getMessage().contains("Failed to create servlet: " + servletClassName),
                "Exception message should indicate failure to create servlet");
    }
}