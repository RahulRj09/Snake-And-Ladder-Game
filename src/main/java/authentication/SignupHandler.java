package authentication;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class SignupHandler implements HttpHandler {
    private  int indexOfAndForPassword;
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String query = bufferedReader.readLine();
        int indexOfAnd = query.indexOf("&");
        StringBuffer name = getName(query,indexOfAnd);
        StringBuffer emailId = getEmailId(query,indexOfAnd+10);
        StringBuffer password = getPassword(indexOfAndForPassword+10,query);
        Signup signup = new Signup(name.toString(), emailId.toString(), password.toString());
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

    private StringBuffer getPassword(int i, String query) {
        StringBuffer password = new StringBuffer();
        for (int j = i; j < query.length(); j++) {
            password.append(query.charAt(j));
        }
        return password;
    }

    private StringBuffer getName(String query, int indexOfAnd) {
        StringBuffer name = new StringBuffer();
        for (int i = 5; i < indexOfAnd; i++) {
            if (query.charAt(i) == '+') {
                name.append(" ");
            }  else {
                name.append(query.charAt(i));
            }
        }
        return name;
    }

    private StringBuffer getEmailId(String query, int indexOfAnd) {
        StringBuffer emailId = new StringBuffer();
        for (int i = indexOfAnd; i < query.length(); i++) {
            if(query.charAt(i)=='&'){
                this.indexOfAndForPassword = i;
                break;
            }else {
                emailId.append(query.charAt(i));
            }
        }
        return emailId;
    }

}
