package game;

import DatabaseHelper.PlayerChanger;

import java.util.List;

public class Game {
    private final Board board;
    private final List<game.Player> players;
    private int currentPlayerIndex;
    private PlayerChanger playerChanger = new PlayerChanger();

    public Game(Board board, List<game.Player> players) {
        this.board = board;
        this.players = players;
        this.currentPlayerIndex = playerChanger.getCurrentIndex();
    }

    public String play() {

        game.Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.play(board.getDice());
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        playerChanger.updateIndex(currentPlayerIndex);
        return currentPlayer.getEmailId();
    }
}
