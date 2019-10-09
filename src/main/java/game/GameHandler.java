package game;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameHandler {
    public static void main(String[] args) throws SQLException {
        Yard green = new Yard(new Token(), "green");
        Yard red = new Yard(new Token(), "red");
        List<Yard> yards = new ArrayList<>();
        yards.add(red);
        yards.add(green);
        Player rahul = new Player(red,"rahul@navgurukul.org");
        Player nitesh = new Player(green,"compuetr@gmail.com");
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
}
