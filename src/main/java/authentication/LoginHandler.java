package authentication;

import DatabaseHelper.Login;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.SQLException;

public class LoginHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String query = bufferedReader.readLine();
        int indexOfAnd = query.indexOf("&");
        StringBuffer emailId = getEmailId(query, indexOfAnd);
        StringBuffer password = getPassword(indexOfAnd + 10, query);
        Login login = new Login();
        try {
            boolean result = login.checkEmailForLogin(emailId.toString(), password.toString());
            if (result) {
                File root = FileSystemView.getFileSystemView().getHomeDirectory();
                String path = root + "/SnakeAndLadderGame/src/main/java/resources/pages/home.html";
                File file = new File(path);
                exchange.sendResponseHeaders(200, file.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    Files.copy(file.toPath(), os);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                String response = "hello !";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private StringBuffer getPassword(int i, String query) {
        StringBuffer password = new StringBuffer();
        for (int j = i; j < query.length(); j++) {
            password.append(query.charAt(j));
        }
        return password;
    }

    private StringBuffer getEmailId(String query, int indexOfAnd) {
        StringBuffer emailId = new StringBuffer();
        for (int i = 8; i < indexOfAnd; i++) {
            if (query.charAt(i) == '&') {
                break;
            } else if (query.charAt(i) == '%' || query.charAt(i) == '4' || query.charAt(i) == '0') {
                if (query.charAt(i) == '%') {
                    emailId.append('@');
                }
            } else {
                emailId.append(query.charAt(i));
            }
        }
        return emailId;
    }
}
