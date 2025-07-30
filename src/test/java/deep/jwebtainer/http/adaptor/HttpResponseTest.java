package deep.jwebtainer.http.adaptor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class HttpResponseTest {

    private ByteArrayOutputStream outputStream;
    private HttpResponse response;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        response = new HttpResponse(outputStream);
    }

    @Test
    void testDefaultStatusAndHeaders() {
        PrintWriter writer = response.getWriter();
        writer.flush();

        String output = outputStream.toString(StandardCharsets.UTF_8);
        assertTrue(output.startsWith("HTTP/1.1 200 OK\r\n"));
        assertTrue(output.contains("Content-Type: text/html\r\n"));
        assertTrue(output.endsWith("\r\n\r\n"));
    }

    @Test
    void testSetStatus() {
        response.setStatus(404);
        PrintWriter writer = response.getWriter();
        writer.flush();

        String output = outputStream.toString(StandardCharsets.UTF_8);
        assertTrue(output.startsWith("HTTP/1.1 404 Not Found\r\n"));
    }

    @Test
    void testSetHeader() {
        response.setHeader("X-Custom-Header", "TestValue");
        PrintWriter writer = response.getWriter();
        writer.flush();

        String output = outputStream.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("X-Custom-Header: TestValue\r\n"));
    }

    @Test
    void testGetOutputStream() {
        assertNotNull(response.getOutputStream());
    }

    @Test
    void testFlush() {
        PrintWriter writer = response.getWriter();
        writer.print("Hello");
        response.flush();

        String output = outputStream.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Hello"));
    }

}
