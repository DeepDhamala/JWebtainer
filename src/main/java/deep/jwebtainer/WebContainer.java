package deep.jwebtainer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The WebContainer class serves as the main entry point for the JWebtainer
 * framework.
 * It is responsible for initializing and managing the web application
 * lifecycle,
 * including servlet management, request handling, and response generation.
 */
public class WebContainer {

    /**
     * Logger for logging events and errors in the WebContainer.
     * It provides a way to log messages at different levels (INFO, SEVERE,
     * etc.)
     * for debugging and monitoring purposes.
     */
    private static final Logger LOGGER =
            Logger.getLogger(WebContainer.class.getName());

    /** The port on which the web container listens for incoming requests. */
    private final int port;

    /** The registry that holds the servlets available in the web application.
     * */
    private final ServletRegistry servletRegistry;

    /** The server socket used to accept incoming connections. */
    private ServerSocket serverSocket;

    /**
     * The executor service used to handle incoming requests concurrently.
     * It allows for efficient management of threads and task execution.
     */
    private final ExecutorService executorService =
            Executors.newCachedThreadPool();

    /**
     * Constructs a WebContainer with the specified port and servlet.
     * @param port
     * @param servletRegistry
     */
    public WebContainer(int port, ServletRegistry servletRegistry) {
        this.port = port;
        this.servletRegistry = servletRegistry;
    }

    /**
     * Starts the web container, initializing the server socket and accepting
     * incoming client connections. Each connection is handled in a separate
     * thread.
     *
     * @throws IOException if an I/O error occurs when opening the socket.
     */
    public void start() throws IOException {
        LOGGER.log(Level.INFO, "Starting WebContainer on port {0}", port);
        // TODO: Receive the port from the configuration file
        serverSocket = new ServerSocket(port);

        while (!serverSocket.isClosed()) {
            Socket clientSocket = serverSocket.accept();
            executorService.submit(new SocketHandler(clientSocket,
                    servletRegistry));
        }
    }

    /**
     * Stops the web container, closing the server socket and shutting down the
     * executor service.
     *
     * @throws IOException if an I/O error occurs when closing the socket.
     */
    public void stop() {
        LOGGER.log(Level.INFO, "Stopping WebContainer on port {0}", port);
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error closing server socket: {0}",
                     e.getMessage());
        }
        servletRegistry.destroyAll();
        executorService.shutdown();
    }


}
