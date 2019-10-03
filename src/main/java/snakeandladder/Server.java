package snakeandladder;

import authentication.LoginHandler;
import authentication.ProfileHandler;
import authentication.RegistrationHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class Server {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new MyHandler());
        server.createContext("/registration", new RegistrationHandler());
        server.createContext("/getProfile", new ProfileHandler());
        server.createContext("/login", new LoginHandler());
        server.setExecutor(null);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            URI requestURI = exchange.getRequestURI();
            File root = FileSystemView.getFileSystemView().getHomeDirectory();
            String path = root + "/SnakeAndLadderGame/src/main/java/resources/" + requestURI;
            File file = new File(path);
            exchange.sendResponseHeaders(200, file.length());
            try (OutputStream os = exchange.getResponseBody()) {
                Files.copy(file.toPath(), os);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
