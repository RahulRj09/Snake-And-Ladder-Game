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
        if(numberOfTokenOut>0){
            moveAToken(numberOnDice);
        }
    }

    private void moveAToken(int numberOnDice) {
        int position =token.setPosition(numberOnDice);
        System.out.println(position);
    }

    private void moveATokenOut() {
        int position =token.place(yard.getStartingPoint());
        System.out.println(position);
        numberOfTokenOut++;
    }
}
