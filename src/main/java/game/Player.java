package game;

public class Player {
    private final Token token;
    private Yard yard;
    private int numberOfTokenOut = 0;

    public Player(Yard yard) {
        this.yard = yard;
        this.token = yard.getToken();
    }

    public boolean play(Dice dice) {
        int numberOnDice = dice.roll();
        if (numberOnDice == 1 && numberOfTokenOut == 0) {
            moveATokenOut();
            return true;
        }
        return moveAToken(numberOnDice);
    }

    private boolean moveAToken(int numberOnDice) {
        int position = token.getPosition();
        if (position + numberOnDice > yard.getEndingPoint()) {

        } else {
            token.setPosition(numberOnDice);
            if (token.getPosition() == yard.getEndingPoint()) {
                return false;
            }
        }
        return true;
    }

    private void moveATokenOut() {
        token.place(yard.getStartingPoint());
        numberOfTokenOut++;
    }
}
