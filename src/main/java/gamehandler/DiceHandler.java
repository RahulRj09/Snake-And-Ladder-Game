package gamehandler;

import DatabaseHelper.DiceDatabaseHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import game.Dice;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class DiceHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String emailId = exchange.getRequestURI().getQuery();
        List<String> emailIdA = Arrays.asList(emailId.split("="));
        System.out.println(emailIdA.get(1));
        DiceDatabaseHelper diceDatabaseHelper = new DiceDatabaseHelper();
        JSONObject numberOnDice = new JSONObject();
        numberOnDice.put("dice", new Dice().roll());
        String response = numberOnDice.toString();
        exchange.getResponseHeaders().set("Content-Type", "appication/json");
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
