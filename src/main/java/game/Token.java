package game;

public class Token {
    private int position = -1;

    public void place(int startingPoint) {
        this.position = startingPoint;
    }

    public void setPosition(int numberOnDice) {
        this.position +=numberOnDice;
    }

    public int getPosition() {
        return position;
    }
}
