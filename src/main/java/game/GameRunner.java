package game;

import java.util.ArrayList;
import java.util.List;

public class GameRunner {
    public static void main(String[] args) {
        RedYard red = new RedYard(new Token(), "red");
        Player player = new Player();
        Player player1 = new Player();
        List<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player1);
        Dice dice = new Dice();
        Board board = new Board(,dice);
        Game game = new Game(board,players);
        while (game.isRunning()){
            game.play();
        }
    }
}
