package deep.jwebtainer.servlet.http;

import deep.jwebtainer.http.HttpMethod;
import deep.jwebtainer.servlet.ServletException;
import deep.jwebtainer.servlet.ServletRequest;
import deep.jwebtainer.servlet.ServletResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HttpServletTest extends HttpServlet{

    boolean getCalled = false;
    boolean postCalled = false;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        getCalled = true;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        postCalled = true;
    }

    @Test
    void request_withGetMethod_shouldCallDoGet() throws ServletException {

        HttpServletTest servlet = new HttpServletTest();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getMethod()).thenReturn(HttpMethod.GET.name());
        servlet.service(req, resp);
        assertTrue(servlet.getCalled);
    }

    @Test
    void service_withPost_shouldCallDoPost() throws ServletException {
        HttpServletTest servlet = new HttpServletTest();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getMethod()).thenReturn(HttpMethod.POST.name());

        servlet.service(req, resp);

        assertTrue(servlet.postCalled);
    }

    @Test
    void service_withUnsupportedMethod_shouldThrow() {
        HttpServletTest servlet = new HttpServletTest();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getMethod()).thenReturn("PUT"); // Unsupported method for now

        assertThrows(ServletException.class, () -> servlet.service(req, resp));
    }

    @Test
    void service_withNonHttpRequest_shouldThrow() {
        HttpServletTest servletTest = new HttpServletTest();

        ServletRequest nonHttpRequest = mock(ServletRequest.class);
        ServletResponse nonHttpResponse = mock(ServletResponse.class);

        assertThrows(ServletException.class, () -> servletTest.service(nonHttpRequest,
                nonHttpResponse));
    }
}