package game;

public class Player {
    private Token token;
    private int  numberOfTokenOut = 0;
    public Player(Yard yard) {
        this.token = yard;
    }

    public void play(Dice dice) {
        int numberOnDice = dice.roll();
        if(numberOnDice == 1 && numberOfTokenOut ==0){
            moveATokenOut();
        }
    }

    private void moveATokenOut() {
        token.place();

    }
}
