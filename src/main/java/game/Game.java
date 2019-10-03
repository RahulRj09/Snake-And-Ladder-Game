package game;

import java.util.List;

public class Game {
    private final Board board;
    private final List<Player> players;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
    }

    public boolean isRunning() {
        return true;
    }
}
