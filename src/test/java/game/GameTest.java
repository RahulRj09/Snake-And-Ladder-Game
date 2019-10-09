package game;

import org.junit.Test;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void gameShouldBeRun() throws IOException, SQLException {
        setEmailId();
        Yard green = new Yard(new Token(), "green");
        Yard red = new Yard(new Token(), "red");
        List<Yard> yards = new ArrayList<>();
        yards.add(red);
        yards.add(green);
        Player rahul = new Player(red, "hello@gmail.com");
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
    public void setEmailId() throws IOException {
        String emailId ="hello@gmail.com";
        File file = new File("/Users/rahul.joshi/SnakeAndLadderGame/src/main/java/resources/static/email.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(emailId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
