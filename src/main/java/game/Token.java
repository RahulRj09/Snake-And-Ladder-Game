package game;

public class Token {
    private int position = -1;

    public int place(int startingPoint) {
        this.position = startingPoint;
        return this.position;
    }

    public int setPosition(int numberOnDice) {
        this.position +=numberOnDice;
        return this.position;
    }
}
