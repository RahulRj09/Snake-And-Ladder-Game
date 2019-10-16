package game;


public class Yard {
    private Token token;
    private int startingPoint;
    private int endingPoint;

    public Yard(Token token) {
        this.token = token;
        this.startingPoint = 1;
        this.endingPoint = 100;
    }

    public Token getToken() {
        return token;
    }

    public int getStartingPoint() {
        return startingPoint;
    }

    public int getEndingPoint() {
        return endingPoint;
    }

}
