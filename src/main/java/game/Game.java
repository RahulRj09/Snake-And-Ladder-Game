package game;

import gamehandler.PlayerHandler;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class Game {
    private final Board board;
    private final List<Player> players;
    private int currentPlayerIndex;
    boolean result = true;
    private PlayerHandler playerHandler = new PlayerHandler();

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.currentPlayerIndex = playerHandler.getCurrentIndex();
    }

    public boolean isRunning() {
        return result;
    }

    public String play() throws SQLException, FileNotFoundException {

        Player currentPlayer = players.get(currentPlayerIndex);
        this.result = currentPlayer.play(board.getDice());
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        playerHandler.updateIndex(currentPlayerIndex);
        return currentPlayer.getEmailId();
    }
}
