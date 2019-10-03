package game;

public class Player {
    Token token;
    int  numberOfTokenOut = 0;
    public Player(Token token) {
        this.token = token;
    }

    public void play(Dice dice) {
        int numberOnDice = dice.roll();
        if(numberOnDice == 1 && numberOfTokenOut ==0){
            moveATokenOut();
        }
    }

    private void moveATokenOut() {
    }
}
