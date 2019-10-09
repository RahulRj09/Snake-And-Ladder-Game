package game;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameHandler implements HttpHandler {
    private String emailId = "";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String emailId = exchange.getRequestURI().getQuery();
        List<String> emailIdA = Arrays.asList(emailId.split("="));
        setEmailId(emailIdA.get(1));
        try {
            play();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void play() throws SQLException {
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
        while (game.isRunning()) {
            game.play();
            game.isRunning();
        }
    }


    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId)
    {
        this.emailId = emailId;
    }
}
