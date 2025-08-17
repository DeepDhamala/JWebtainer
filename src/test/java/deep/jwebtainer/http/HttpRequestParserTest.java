package deep.jwebtainer.http;

import deep.jwebtainer.http.adaptor.HttpRequest;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestParserTest {

    @Test
    void parse_validRequestWithQueryAndHeaders_shouldParseCorrectly() throws IOException{
        String rawRequest = """
                GET /test?param=value http/1.1
                Host: localhost
                Connection: keep-alive
                """;

        BufferedReader reader =
                new BufferedReader(new StringReader(rawRequest));

        HttpRequest request = HttpRequestParser.parse(reader);

        assertEquals("GET", request.getMethod());
        assertEquals("/test", request.getRequestURI());
        assertEquals("value",
                request.getParameter("param"));
        assertEquals("localhost", request.getHeader("Host"));
        assertEquals("keep-alive",
                request.getHeader("Connection"));
    }

    @Test
    void parse_validRequestWithoutQuery_shouldParseURIAndHeaders() throws IOException{
        String rawRequest = """
        POST /submit HTTP/1.1
        Content-Type: application/json

        """;
        BufferedReader reader = new BufferedReader(new StringReader(rawRequest));
        HttpRequest request = HttpRequestParser.parse(reader);

        assertEquals("POST", request.getMethod());
        assertEquals("/submit", request.getRequestURI());
        assertTrue(request.getParameters().isEmpty());
        assertEquals("application/json",
                request.getHeader("Content-Type"));
    }

    @Test
    void parse_validPostRequestWithBody_shouldParseBodyParameters() throws IOException {
        String rawRequest = """
                POST /submit HTTP/1.1
                Content-Type: application/x-www-form-urlencoded
                Content-Length: 16
                
                name=John&age=25""";

        InputStream inputStream = new ByteArrayInputStream(rawRequest.getBytes(StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        HttpRequest request = HttpRequestParser.parse(reader);

        assertEquals("POST", request.getMethod());
        assertEquals("/submit", request.getRequestURI());
        assertEquals("16", request.getHeader("Content-Length"));
        assertEquals("John", request.getParameter("name"));
        assertEquals("25", request.getParameter("age"));
    }


    @Test
    void parse_invalidRequestLineParts_shouldThrow() {
        String invalidRequestLine = "INVALID_REQUEST_LINE";

        BufferedReader reader = new BufferedReader(new StringReader(invalidRequestLine + "\n"));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> HttpRequestParser.parse(reader));
        assertTrue(ex.getMessage().contains("Invalid HTTP request line"));
    }

    @Test
    void parse_malformedHeaderLine_shouldThrow() {
        String rawRequest =
                """
                GET /test HTTP/1.1
                BadHeaderLine withoutColon
                """;
        BufferedReader reader = new BufferedReader(new StringReader(rawRequest));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> HttpRequestParser.parse(reader));
        assertTrue(ex.getMessage().contains("Invalid HTTP header line"));
    }

}