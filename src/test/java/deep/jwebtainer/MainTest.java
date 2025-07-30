package deep.jwebtainer;

import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URI;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void applicationStartsAndResponds() throws Exception {
        DefaultServletRegistry servletRegistry = new DefaultServletRegistry();
        ServletLoader servletLoader =
                new PropertiesServletLoader("servlet.properties", new ServletFactory());
        servletLoader.load(servletRegistry);

        WebContainer webContainer = new WebContainer(8080, servletRegistry);

        Thread serverThread = new Thread(() -> {
            try {
                webContainer.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        serverThread.start();

        Thread.sleep(500);

        var conn = (HttpURLConnection) new URI("http://localhost:8080/").toURL().openConnection();
        int responseCode = conn.getResponseCode();

        assertEquals(HttpURLConnection.HTTP_OK, responseCode, "Expected HTTP 200 OK response");

        webContainer.stop();
        serverThread.interrupt();
    }


}