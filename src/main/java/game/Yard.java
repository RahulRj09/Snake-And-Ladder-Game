package game;

import java.util.List;

public class Yard {
    List<Token> tokens;
    int startingPoint = 1;
    int endingPoint = 100;

    public Yard(List<Token> tokens) {
        this.tokens = tokens;
    }
}
