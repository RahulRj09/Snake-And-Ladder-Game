package game;

import DatabaseHelper.ProfileDatabaseHelper;
import DatabaseHelper.TokenDatabaseHelper;

import java.sql.SQLException;

public class Player {
    private final Token token;
    private Yard yard;
    private boolean tokenOut = false;
    String emailId;

    public Player(Yard yard, String  emailId) {
        this.yard = yard;
        this.token = yard.getToken();
        this.emailId = emailId;
    }

    public boolean play(Dice dice) throws SQLException {
        int numberOnDice = dice.roll();
        if (numberOnDice == 1 && !isTokenOut()) {
            moveATokenOut();
            return true;
        }else if(isTokenOut()){
            return moveAToken(numberOnDice);
        }
        return true;
    }

    private boolean moveAToken(int numberOnDice) throws SQLException {
        int position = token.getPosition(this.emailId);
        if (position + numberOnDice <= yard.getEndingPoint()) {
            token.setPosition(this.emailId,numberOnDice);
            if (token.getPosition(this.emailId) == yard.getEndingPoint()) {
                TokenDatabaseHelper tokenDatabaseHelper = new TokenDatabaseHelper();
                tokenDatabaseHelper.tableTruncate();
                ProfileDatabaseHelper profileDatabaseHelper = new ProfileDatabaseHelper();
                if(this.emailId.equals("rahul18@navgurukul.org")){

                }
                System.out.println(this.emailId);
                return false;
            }
        }
        return true;
    }

    private void moveATokenOut() {
        token.place(this.emailId,yard.getStartingPoint());
        tokenOut = true;
    }

    public boolean isTokenOut() {
        return tokenOut;
    }
}
