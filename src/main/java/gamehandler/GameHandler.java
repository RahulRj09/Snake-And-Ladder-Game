package gamehandler;

import DatabaseHelper.Token;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import game.*;
import org.json.simple.JSONObject;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameHandler implements HttpHandler {
    private String emailId;
    private Token token = new Token();

    @Override
    public void handle(HttpExchange exchange) {
        String uriQuery = exchange.getRequestURI().getQuery();
        List<String> userInfo = Arrays.asList(uriQuery.split("="));
        setEmailId(userInfo.get(1));
        play(exchange);
    }

    private void play(HttpExchange exchange) {
        JSONObject position;
        Yard green = new Yard(new game.Token());
        Yard red = new Yard(new game.Token());
        List<Yard> yards = new ArrayList<>();
        yards.add(red);
        yards.add(green);
        Player rahul = new Player(red, getEmailId());
        Player nitesh = new Player(green, "computer@gmail.com");
        List<Player> players = new ArrayList<>();
        players.add(rahul);
        players.add(nitesh);
        Dice dice = new Dice();
        Board board = new Board(yards, dice);
        Game game = new Game(board, players);
        String currentPlayerEmailId = game.play();
        position = token.getCurrentPosition(currentPlayerEmailId);
        try {
            String response = position.toString();
            exchange.getResponseHeaders().set("Content-Type", "appication/json");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
