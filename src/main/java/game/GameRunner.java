package game;

import java.util.ArrayList;
import java.util.List;

public class GameRunner {
    public static void main(String[] args) {
        RedYard red = new RedYard(new Token(), "red");
        GreenYard green = new GreenYard(new Token(), "green");
        List<Yard> yards = new ArrayList<>();
        yards.add(red);
        yards.add(green);
        Player rahul = new Player(red);
        Player nitesh = new Player(green);
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
