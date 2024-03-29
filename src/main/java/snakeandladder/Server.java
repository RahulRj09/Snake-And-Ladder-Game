package snakeandladder;

import authentication.LoginHandler;
import authentication.Logout;
import authentication.ProfileHandler;
import authentication.RegistrationHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import gamehandler.GameHandler;
import gamehandler.WinnerHandler;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.file.Files;


public class Server {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);
        server.createContext("/", new MyHandler());
        server.createContext("/registration", new RegistrationHandler());
        server.createContext("/getProfile", new ProfileHandler());
        server.createContext("/login", new LoginHandler());
        server.createContext("/getCurrentPosition", new GameHandler());
        server.createContext("/getWinner", new WinnerHandler());
        server.createContext("/logout", new Logout());
        server.setExecutor(null);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            URI requestURI = exchange.getRequestURI();
            File root = FileSystemView.getFileSystemView().getHomeDirectory();
            String newUri = requestURI.getPath().replaceAll("SnakeAndLadderGame", "pages");
            if (newUri.contains("pages")) {
                String path = root + "/SnakeAndLadderGame/src/main/java/resources/" + newUri + ".html";
                File file = new File(path);
                exchange.sendResponseHeaders(200, file.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    Files.copy(file.toPath(), os);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            String path = root + "/SnakeAndLadderGame/src/main/java/resources/" + newUri;
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

