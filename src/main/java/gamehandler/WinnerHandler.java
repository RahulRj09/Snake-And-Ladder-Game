package gamehandler;


import DatabaseHelper.Winner;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class WinnerHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Winner winnerDatabaseHelper = new Winner();
        String winner = winnerDatabaseHelper.getWinner();
        try {
            new Winner().tableTruncate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        exchange.sendResponseHeaders(200, winner.length());
        OutputStream os = exchange.getResponseBody();
        os.write(winner.getBytes());
        os.close();
    }
}
