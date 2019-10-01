package authentication;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class SignupHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String query = bufferedReader.readLine();
        File root = FileSystemView.getFileSystemView().getHomeDirectory();
        String path = root + "/SnakeAndLadderGame/src/main/java/resources/pages/home.html";
        File file = new File(path);
        exchange.sendResponseHeaders(200, file.length());
        try (OutputStream os = exchange.getResponseBody()) {
            Files.copy(file.toPath(), os);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
