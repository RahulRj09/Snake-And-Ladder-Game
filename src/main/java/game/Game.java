package game;

import java.util.List;

public class Game {
    private final Board board;
    private final List<Player> players;
    private int currentPlayerIndex;
    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    public boolean isRunning() {
        return true;
    }

    public void play() {

        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.play(board.getDice());
    }
}
