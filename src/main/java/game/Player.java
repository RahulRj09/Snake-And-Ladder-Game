package game;

import DatabaseHelper.ProfileDatabaseHelper;
import DatabaseHelper.TokenDatabaseHelper;

import java.sql.SQLException;

public class Player {
    private final Token token;
    private Yard yard;
    private boolean tokenOut = false;
    private String emailId;

    public Player(Yard yard, String emailId) {
        this.yard = yard;
        this.token = yard.getToken();
        this.emailId = emailId;
    }

    public boolean play(Dice dice) throws SQLException {
        int numberOnDice = dice.roll();
        if (numberOnDice == 1 && !isTokenOut()) {
            moveATokenOut();
            return true;
        } else if (isTokenOut()) {
            return moveAToken(numberOnDice);
        }
        return true;
    }

    private boolean moveAToken(int numberOnDice) throws SQLException {
        int position = token.getPosition(getEmailId());
        if (position + numberOnDice <= yard.getEndingPoint()) {
            token.setPosition(getEmailId(), numberOnDice);
            if (token.getPosition(getEmailId()) == yard.getEndingPoint()) {
                TokenDatabaseHelper tokenDatabaseHelper = new TokenDatabaseHelper();
                tokenDatabaseHelper.tableTruncate();
                ProfileDatabaseHelper profileDatabaseHelper = new ProfileDatabaseHelper();
                if (getEmailId().equals("rahul18@navgurukul.org")) {
                    profileDatabaseHelper.updateWinningGames(getEmailId());
                }
                profileDatabaseHelper.updateLostGames("rahul18@navgurukul.org");
                return false;
            }
        }
        return true;
    }

    private void moveATokenOut() {
        token.place(getEmailId(), yard.getStartingPoint());
        tokenOut = true;
    }

    public boolean isTokenOut() {
        return tokenOut;
    }

    public String getEmailId() {
        return this.emailId;
    }
}
