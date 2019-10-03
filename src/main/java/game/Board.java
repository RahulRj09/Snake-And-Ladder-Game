package game;

public class Board {
    private Dice dice;
    private Yard yard;

    public Board(Yard yard, Dice dice) {
        this.yard = yard;
        this.dice = dice;
    }

    public Dice getDice() {
        return dice;
    }

    public Yard getYard() {
        return yard;
    }
}
