package game;

import DatabaseHelper.DiceDatabaseHelper;
import org.json.simple.JSONObject;

public class Player {
    private final Token token;
    private Yard yard;
    private int numberOfTokenOut = 0;
    String emailId;

    public Player(Yard yard, String  emailId) {
        this.yard = yard;
        this.token = yard.getToken();
        this.emailId = emailId;
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
        int position = token.getPosition(this.emailId);
        if (position + numberOnDice <= yard.getEndingPoint()) {
            token.setPosition(numberOnDice);
            DiceDatabaseHelper diceDatabaseHelper = new DiceDatabaseHelper();
            diceDatabaseHelper.updatePosition(this.emailId, position+numberOnDice);
            JSONObject res = diceDatabaseHelper.getCurrentPosition(this.emailId);
            System.out.println(res.toString());
            if (token.getPosition(this.emailId) == yard.getEndingPoint()) {
                System.out.println(this.emailId);
                return false;
            }
        }
        return true;
    }

    private void moveATokenOut() {
        token.place(this.emailId,yard.getStartingPoint());
        numberOfTokenOut++;
    }
}
