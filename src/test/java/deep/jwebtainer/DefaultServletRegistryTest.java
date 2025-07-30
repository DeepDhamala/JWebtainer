package deep.jwebtainer;

import deep.jwebtainer.servlet.Servlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DefaultServletRegistryTest {

    DefaultServletRegistry registry;
    Servlet servlet;

    @BeforeEach
    void setUp() {
        registry = new DefaultServletRegistry();
        servlet = mock(Servlet.class);
    }

    @Test
    void registerServlet_shouldRegisterAndInit(){
        registry.registerServlet("/test", servlet);
        assertEquals(servlet, registry.getServlet("/test"), "Servlet should be registered");
    }

    @Test
    void registerServlet_duplicatePath_shouldThrow() {
        registry.registerServlet("/test", servlet);
        Servlet anotherServlet = mock(Servlet.class);
        assertThrows(IllegalArgumentException.class, () -> {
            registry.registerServlet("/test", anotherServlet);
        });
    }

    @Test
    void getServlet_nonExistentPath_shouldReturnNull() {
        assertNull(registry.getServlet("/nonexistent"), "Should return null for non-existent path");
    }

    @Test
    void destroyAll_shouldCallDestroyOnAllServlets() {
        Servlet servlet1 = mock(Servlet.class);
        Servlet servlet2 = mock(Servlet.class);
        registry.registerServlet("/servlet1", servlet1);
        registry.registerServlet("/servlet2", servlet2);
        registry.destroyAll();

        verify(servlet1).destroy();
        verify(servlet2).destroy();
    }

}