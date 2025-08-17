package deep.jwebtainer;

import deep.jwebtainer.http.HttpRequestParser;
import deep.jwebtainer.http.adaptor.HttpResponse;
import deep.jwebtainer.servlet.ServletException;
import deep.jwebtainer.servlet.http.HttpServlet;
import deep.jwebtainer.servlet.http.HttpServletRequest;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The SocketHandler class is responsible for handling incoming socket
 * connections
 * in the JWebtainer framework. It processes HTTP requests, invokes the
 * appropriate
 * servlets, and sends back HTTP responses.
 */
public class SocketHandler implements Runnable {

    /**
     * Logger for logging socket handling events and errors.
     */
    private static final Logger LOGGER =
            Logger.getLogger(SocketHandler.class.getName());

    /**
     * The socket representing the client connection.
     */
    private final Socket socket;

    /**
     * A registry of servlets that can handle incoming requests.
     * This map is keyed by the request path.
     */
    private final ServletRegistry servletRegistry;

    /**
     * Constructs a SocketHandler with the specified socket and servlet
     * registry.
     *
     * @param socket the socket representing the client connection
     * @param servletRegistry the registry of servlets to handle requests
     */
    public SocketHandler(Socket socket, ServletRegistry servletRegistry) {
        this.socket = socket;
        this.servletRegistry = servletRegistry;
    }

    /**
     * The run method is executed when the thread is started.
     * It reads the request from the socket, processes it, and sends a response.
     */
    @Override
    public void run() {
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader
                             (socket.getInputStream()));
             OutputStream outputStream = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(outputStream, true)) {

            HttpServletRequest request = HttpRequestParser.parse(reader);

            HttpServlet servlet =
                    (HttpServlet) servletRegistry
                                        .getServlet(request.getRequestURI());

            if (servlet == null) {
                sendErrorResponse(writer, 404, "Not Found");
                return;
            }

            HttpResponse response = new HttpResponse(outputStream);

            // TODO: add response part

            servlet.service(request, response);

            response.flush();

        } catch (IOException | ServletException e) {
            LOGGER.log(Level.SEVERE, "Socket handling failed", e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Error closing socket", e);
            }
        }
    }

    private void sendErrorResponse(PrintWriter writer, int statusCode,
                                   String message) {
        writer.printf(String.format("HTTP/1.1 %d %s\r%n", statusCode, message));
        writer.println("Content-Type: text/html");
        writer.println("Connection: close");
        writer.println();
        writer.printf("<html><body>%s</body></html>", message);
    }
}
