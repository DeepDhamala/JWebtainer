package deep.jwebtainer.http.adaptor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestTest {

    @Test
    void testSetAndGetMethod() {
        HttpRequest req = new HttpRequest();
        req.setMethod("GET");
        assertEquals("GET", req.getMethod());
    }

    @Test
    void testSetAndGetRequestURI() {
        HttpRequest req = new HttpRequest();
        req.setRequestURI("/test/path");
        assertEquals("/test/path", req.getRequestURI());
    }

    @Test
    void testAddAndGetHeader() {
        HttpRequest req = new HttpRequest();
        req.addHeader("Content-Type", "application/json");
        assertEquals("application/json", req.getHeader("Content-Type"));
        // Also check map contents
        assertTrue(req.getHeaders().containsKey("Content-Type"));
    }

    @Test
    void testGetHeaderWhenNotPresent() {
        HttpRequest req = new HttpRequest();
        assertNull(req.getHeader("Non-Existent-Header"));
    }

    @Test
    void testAddAndGetParameter() {
        HttpRequest req = new HttpRequest();
        req.addParameter("username", "deep");
        assertEquals("deep", req.getParameter("username"));
        // Also check map contents
        assertTrue(req.getParameters().containsKey("username"));
    }

    @Test
    void testGetParameterWhenNotPresent() {
        HttpRequest req = new HttpRequest();
        assertNull(req.getParameter("missingParam"));
    }

    @Test
    void testGetAttributeAlwaysNull() {
        HttpRequest req = new HttpRequest();
        assertNull(req.getAttribute("any"));
    }

}