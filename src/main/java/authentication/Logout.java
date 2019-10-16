package authentication;

import DatabaseHelper.Token;
import DatabaseHelper.Winner;
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
            Winner winner = new Winner();
            try {
                winner.tableTruncate();
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
        Token token = new Token();
        boolean result = token.positionRowExistsOrNotForCurrentUser(emailIdA.get(1));
        String response = String.valueOf(result);
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
