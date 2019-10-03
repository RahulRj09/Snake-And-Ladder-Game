package game;

import java.util.ArrayList;
import java.util.List;

public class GameRunner {
    public static void main(String[] args) {
        Token red = new Token("red");
        Token green = new Token("green");
        List<Token> tokens = new ArrayList<>();
        tokens.add(red);
        tokens.add(green);
        Yard yard = new Yard(tokens);
        Player player = new Player(red);
        Player player1 = new Player(green);
        List<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player1);
        Dice dice = new Dice();
        Board board = new Board(yard,dice);
        Game game = new Game(board,players);
        while (game.isRunning()){
            game.play();
        }
    }
}
