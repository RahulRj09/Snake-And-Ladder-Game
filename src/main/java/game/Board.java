package game;

import java.util.List;

public class Board {
    private Dice dice;
    List<Yard> yards;

    public Board(List<Yard> yards, Dice dice) {
        this.yards = yards;
        this.dice = dice;
    }

    public Dice getDice() {
        return dice;
    }

    public List<Yard> getYard() {
        return yards;
    }
}
