package game;

public class Player {
    private final Token token;
    private Yard yard;
    private int  numberOfTokenOut = 0;
    public Player(Yard yard) {
        this.yard = yard;
        this.token= yard.getToken();
    }

    public void play(Dice dice) {
        int numberOnDice = dice.roll();
        if(numberOnDice == 1 && numberOfTokenOut ==0){
            moveATokenOut();
        }
    }

    private void moveATokenOut() {
        int position =token.place(yard.getStartingPoint());
        System.out.println(position);
        numberOfTokenOut++;
    }
}
