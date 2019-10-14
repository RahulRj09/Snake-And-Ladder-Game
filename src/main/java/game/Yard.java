package game;


public class Yard {
    private Token token;
    private int startingPoint;
    private int endingPoint;
    private String color;

    public Yard(Token token, String color) {
        this.token = token;
        this.startingPoint = 1;
        this.endingPoint = 100;
        this.color = color;
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

    public String getColor() {
        return color;
    }
}
