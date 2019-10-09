package gamehandler;

import DatabaseHelper.DiceDatabaseHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import game.Dice;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class DiceHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        int numberOnDice = new Dice().roll();
        String emailId = exchange.getRequestURI().getQuery();
        List<String> emailIdA = Arrays.asList(emailId.split("="));
        DiceDatabaseHelper diceDatabaseHelper = new DiceDatabaseHelper();
        diceDatabaseHelper.updatePosition(emailIdA.get(1), numberOnDice);
        JSONObject res = diceDatabaseHelper.getCurrentPosition(emailIdA.get(1));
        int position = (int) res.get("position");
        if(position>=100){
            try {
                diceDatabaseHelper.tableTruncate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(res.get("position"));
        }
        String response = res.toString();
        exchange.getResponseHeaders().set("Content-Type", "appication/json");
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
