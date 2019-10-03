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
        }
    }
}
