package game;

public class RedYard extends Yard{

    public RedYard(Token token, String color) {
        this.token= token;
        this.color = color;
        this.startingPoint =1;
        this.endingPoint = 100;
    }
}
