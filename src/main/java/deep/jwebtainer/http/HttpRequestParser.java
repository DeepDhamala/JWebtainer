package deep.jwebtainer.http;

import deep.jwebtainer.http.adaptor.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * The HttpRequestParser class is responsible for parsing HTTP requests from a
 * BufferedReader. It reads the request line, headers, and query parameters
 * and constructs an HttpRequest object.
 */
public final class HttpRequestParser {
    /**
     * The expected number of parts in the first line of an HTTP request.
     * This is typically 3 parts: method, request URI, and HTTP version.
     */
    private static final int EXPECTED_HTTP_FIRST_LINE_PARTS = 3;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private HttpRequestParser() {
        throw new UnsupportedOperationException(
                "Utility class cannot be instantiated");
    }

    /**
     * Parses an HTTP request from the given BufferedReader.
     *
     * @param in the BufferedReader to read the HTTP request from
     * @return an HttpRequest object representing the parsed request
     * @throws IOException if an I/O error occurs while reading the request
     */
    public static HttpRequest parse(BufferedReader in) throws IOException {
        HttpRequest httpRequest = new HttpRequest();

        // Parse request line
        String line = in.readLine(); // e.g., "GET /path?name=deep HTTP/1.1"
        if (line == null || line.isBlank()) {
            throw new IllegalArgumentException("Empty HTTP request line");
        }

        String[] requestLineParts = line.split(" ");
        if (requestLineParts.length != EXPECTED_HTTP_FIRST_LINE_PARTS) {
            throw new IllegalArgumentException(
                    "Invalid HTTP request line: " + line);
        }

        String method = requestLineParts[0];
        String rawPath = requestLineParts[1];

        httpRequest.setMethod(method);

        // Parse URI and query string
        int queryIndex = rawPath.indexOf("?");
        if (queryIndex >= 0) {
            httpRequest.setRequestURI(rawPath.substring(0, queryIndex));
            parseRequestParameters(httpRequest,
                    rawPath.substring(queryIndex + 1));
        } else {
            httpRequest.setRequestURI(rawPath);
        }

        // Parse headers
        while ((line = in.readLine()) != null && !line.isEmpty()) {
            int colonIndex = line.indexOf(":");
            if (colonIndex <= 0) {
                throw new IllegalArgumentException("Invalid HTTP header "
                        + "line: " + line);
            }
            String headerName = line.substring(0, colonIndex).trim();
            String headerValue = line.substring(colonIndex + 1).trim();
            httpRequest.addHeader(headerName, headerValue);
        }

        if ("POST".equals(method)) {
            String contentLengthHeader = httpRequest.getHeader("Content-Length");
            if (contentLengthHeader != null && Integer.parseInt(contentLengthHeader) > 0) {
                StringBuilder body = new StringBuilder();
            while(in.ready()) {
                body.append((char) in.read());
            }
                if (!body.isEmpty()) {
                    parseRequestParameters(httpRequest, body.toString());
                }
            }

        }
        return httpRequest;
    }


    /**
     * Parses the query string and form-data from the request URI and adds parameters to
     * the HttpRequest object.
     *
     * @param httpRequest the HttpRequest object to add parameters to
     * @param queryString the query string to parse (e.g., "name=deep&age=30")
     */
    private static void parseRequestParameters(HttpRequest httpRequest,
                                                String queryString) {
        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            if (keyValue.length == 2) {
                httpRequest.addParameter(keyValue[0], keyValue[1]);
            } else if (keyValue.length == 1) {
                httpRequest.addParameter(keyValue[0], "");
            } else {
                throw new IllegalArgumentException("Invalid query parameter "
                        + "format: " + pair);
            }
        }
    }

}
