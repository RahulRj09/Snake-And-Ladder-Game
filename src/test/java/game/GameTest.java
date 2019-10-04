package game;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void gameShouldBeRun() {
        RedYard red = new RedYard(new Token(), "red");
        GreenYard green = new GreenYard(new Token(), "green");
        List<Yard> yards = new ArrayList<>();
        yards.add(red);
        yards.add(green);
        Player player = new Player(red);
        Player player1 = new Player(green);
        List<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player1);
        Dice dice = new Dice();
        Board board = new Board(yards, dice);
        Game game = new Game(board, players);
        while (game.isRunning()) {
            game.play();
            game.isRunning();
        }
    }
}
