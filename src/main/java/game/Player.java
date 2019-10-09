package game;

import DatabaseHelper.TokenDatabaseHelper;

import java.sql.SQLException;

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

    public boolean play(Dice dice) throws SQLException {
        int numberOnDice = dice.roll();
        if (numberOnDice == 1 && numberOfTokenOut == 0) {
            moveATokenOut();
            return true;
        }
        return moveAToken(numberOnDice);
    }

    private boolean moveAToken(int numberOnDice) throws SQLException {
        int position = token.getPosition(this.emailId);
        if (position + numberOnDice <= yard.getEndingPoint()) {
            token.setPosition(this.emailId,numberOnDice);
            if (token.getPosition(this.emailId) == yard.getEndingPoint()) {
                TokenDatabaseHelper tokenDatabaseHelper = new TokenDatabaseHelper();
                tokenDatabaseHelper.tableTruncate();
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
