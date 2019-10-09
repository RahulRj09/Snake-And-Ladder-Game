package game;

import java.sql.SQLException;
import java.util.List;

public class Game {
    private final Board board;
    private final List<Player> players;
    private int currentPlayerIndex;
    boolean result = true;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    public boolean isRunning() {
        return result;
    }

    public void play() throws SQLException {

        Player currentPlayer = players.get(currentPlayerIndex);
        this.result = currentPlayer.play(board.getDice());
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}
