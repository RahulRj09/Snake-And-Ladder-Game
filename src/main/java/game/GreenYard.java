package game;

public class GreenYard extends Yard{
    public GreenYard(Token token, String color) {
        this.token=token;
        this.color=color;
        this.startingPoint=1;
        this.endingPoint=100;
    }
}
