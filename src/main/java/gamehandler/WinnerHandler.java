package gamehandler;


import DatabaseHelper.WinnerDatabaseHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class WinnerHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        WinnerDatabaseHelper winnerDatabaseHelper = new WinnerDatabaseHelper();
        String winner = winnerDatabaseHelper.getWinner();
        try {
            new WinnerDatabaseHelper().tableTruncate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        exchange.sendResponseHeaders(200, winner.length());
        OutputStream os = exchange.getResponseBody();
        os.write(winner.getBytes());
        os.close();
    }
}
