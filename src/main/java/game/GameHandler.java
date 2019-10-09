package game;

import DatabaseHelper.TokenDatabaseHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.simple.JSONObject;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameHandler implements HttpHandler {
    private String emailId;
    TokenDatabaseHelper tokenDatabaseHelper = new TokenDatabaseHelper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println(exchange.getRequestMethod());
        String emailId = exchange.getRequestURI().getQuery();
        List<String> emailIdA = Arrays.asList(emailId.split("="));
        setEmailId(emailIdA.get(1));
        try {
            JSONObject jsonObject= play();
            String response = jsonObject.toString();
            exchange.getResponseHeaders().set("Content-Type", "appication/json");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private JSONObject play() throws SQLException, FileNotFoundException {
        JSONObject position = new JSONObject();
        Yard green = new Yard(new Token(), "green");
        Yard red = new Yard(new Token(), "red");
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
        if (game.isRunning()) {
            game.play();
            position = tokenDatabaseHelper.getCurrentPosition(getEmailId());
            game.isRunning();
        }
        return position;
    }


    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) throws IOException {
        this.emailId = emailId;
        File file = new File("/Users/rahul.joshi/SnakeAndLadderGame/src/main/java/resources/static/email.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(emailId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
