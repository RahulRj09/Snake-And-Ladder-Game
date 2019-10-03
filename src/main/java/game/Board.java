package game;

public class Board {
    private Dice dice;
    private Yard yard;

    public Board(Yard yard, Dice dice) {
        this.yard = yard;
        this.dice = dice;
    }
}
