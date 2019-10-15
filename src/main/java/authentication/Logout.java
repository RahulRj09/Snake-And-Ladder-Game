package authentication;

import DatabaseHelper.TokenDatabaseHelper;
import DatabaseHelper.WinnerDatabaseHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Logout implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("DELETE")) {
            WinnerDatabaseHelper winnerDatabaseHelper = new WinnerDatabaseHelper();
            try {
                winnerDatabaseHelper.tableTruncate();
                String response = "/SnakeAndLadderGame/login.html";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
        String response = String.valueOf(result);
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
