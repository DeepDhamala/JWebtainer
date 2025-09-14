package deep.jwebtainer;

import deep.jwebtainer.http.adaptor.HttpResponse;
import deep.jwebtainer.servlet.http.HttpServlet;
import deep.jwebtainer.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SocketHandlerTest {

    private Socket socket;
    private ServletRegistry servletRegistry;
    private HttpServlet servlet;
    private PipedOutputStream pipedOutputStream;
    private PipedInputStream pipedInputStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    @BeforeEach
    void setUp() throws Exception {
        socket = mock(Socket.class);
        servletRegistry = mock(ServletRegistry.class);
        servlet = mock(HttpServlet.class);

        pipedOutputStream = new PipedOutputStream();
        pipedInputStream = new PipedInputStream(pipedOutputStream);

        byteArrayOutputStream = new ByteArrayOutputStream();

        when(socket.getInputStream()).thenReturn(pipedInputStream);
        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
    }

    @Test
    void run_servletFound_servletServiceCalledAndResponseSent() throws Exception {

        String httpRequest = "GET /test HTTP/1.1\r\nHost: localhost\r\n\r\n";

        pipedOutputStream.write(httpRequest.getBytes());
        pipedOutputStream.flush();
        pipedOutputStream.close();

        doAnswer(invocation -> {
            HttpResponse response = invocation.getArgument(1);
            PrintWriter writer = response.getWriter(); // Triggers header flush
            writer.println("Hello from servlet");
            return null;
        }).when(servlet).service(any(), any());

        when(servletRegistry.getServlet("/test")).thenReturn(servlet);

        SocketHandler socketHandler = new SocketHandler(socket, servletRegistry);
        socketHandler.run();

        ArgumentCaptor<HttpServletRequest> reqCaptor = ArgumentCaptor.forClass(HttpServletRequest.class);
        ArgumentCaptor<HttpResponse> respCaptor = ArgumentCaptor.forClass(HttpResponse.class);

        verify(servlet).service(reqCaptor.capture(), respCaptor.capture());

        HttpServletRequest capturedRequest = reqCaptor.getValue();
        assertEquals("GET", capturedRequest.getMethod());
        assertEquals("/test", capturedRequest.getRequestURI());

        String responseString = byteArrayOutputStream.toString();
        assertTrue(responseString.startsWith("HTTP/1.1 200 OK"));
        assertTrue(responseString.contains("Hello from servlet"));
    }

    @Test
    void run_servletNotFound_sends404() throws Exception {
        String httpRequest = """
                   GET /notfound HTTP/1.1
                   Host: localhost
                   """;

        pipedOutputStream.write(httpRequest.getBytes());
        pipedOutputStream.flush();
        pipedOutputStream.close();

        when(servletRegistry.getServlet("/notfound")).thenReturn(null);

        SocketHandler socketHandler = new SocketHandler(socket, servletRegistry);
        socketHandler.run();

        String responseString = byteArrayOutputStream.toString();

        assertTrue(responseString.contains("HTTP/1.1 404 Not Found"), "Response should contain 404 status");
        assertTrue(responseString.contains("<h1>HTTP Status 404 – Not Found</h1>"),
                "Response should contain the proper error heading");
        assertTrue(responseString.contains("The server encountered an error while processing your request."),
                "Response should contain the standard error message");
        assertTrue(responseString.contains("JWebtainer (Java Web Container)"),
                "Response should contain footer info");
    }
}
