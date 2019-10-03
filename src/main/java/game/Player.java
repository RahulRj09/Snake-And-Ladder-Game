package game;

public class Player {
    private Yard yard;
    private int  numberOfTokenOut = 0;
    public Player(Yard yard) {
        this.yard = yard;
    }

    public void play(Dice dice) {
        int numberOnDice = dice.roll();
        if(numberOnDice == 1 && numberOfTokenOut ==0){
            moveATokenOut();
        }
    }

    private void moveATokenOut() {
        yard.token.place();

    }
}
