package authentication;

import DatabaseHelper.TokenDatabaseHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class Logout implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String emailId = exchange.getRequestURI().getQuery();
        List<String> emailIdA = Arrays.asList(emailId.split("="));
        TokenDatabaseHelper tokenDatabaseHelper = new TokenDatabaseHelper();
        boolean result = tokenDatabaseHelper.positionRowExistsOrNotForCurrentUser(emailIdA.get(1));
        if (!result) {
            String response = String.valueOf(false);
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
