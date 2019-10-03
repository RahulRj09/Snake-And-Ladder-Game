package game;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void gameShouldBeRun() {
        Token red = new Token("red");
        Token green = new Token("green");
        List<Token> tokens = new ArrayList<>();
        tokens.add(red);
        tokens.add(green);
        Yard yard = new Yard(tokens);
        Player player = new Player(red);
        Player player1 = new Player(green);
        Dice dice = new Dice();
        Board board = new Board();
    }
}
