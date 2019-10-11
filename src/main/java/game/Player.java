package game;

import DatabaseHelper.ProfileDatabaseHelper;
import DatabaseHelper.TokenDatabaseHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class Player {
    private final Token token;
    private Yard yard;
    private String emailId;
    private ProfileDatabaseHelper profileDatabaseHelper = new ProfileDatabaseHelper();

    public Player(Yard yard, String emailId) {
        this.yard = yard;
        this.token = yard.getToken();
        this.emailId = emailId;
    }

    public boolean play(Dice dice) throws SQLException, FileNotFoundException {
        TokenDatabaseHelper tokenDatabaseHelper = new TokenDatabaseHelper();
        int numberOnDice = dice.roll();
        boolean b = tokenDatabaseHelper.positionRowExistsOrNotForCurrentUser(getEmailId());
        if (!b && numberOnDice == 1) {
            moveATokenOut();
            return true;
        } else if (b) {
            return moveAToken(numberOnDice);
        }
        return true;
    }

    private boolean moveAToken(int numberOnDice) throws SQLException, FileNotFoundException {
        int position = token.getPosition(getEmailId());
        if (position + numberOnDice <= yard.getEndingPoint()) {
            token.setPosition(getEmailId(), numberOnDice);
            if (token.getPosition(getEmailId()) == yard.getEndingPoint()) {
                TokenDatabaseHelper tokenDatabaseHelper = new TokenDatabaseHelper();
                tokenDatabaseHelper.tableTruncate();

                if (!getEmailId().equals(getEmailId())) {
                    profileDatabaseHelper.updateLostGames(getEmailId());
                }
                if (getEmailId().equals(getEmailId())) {
                    profileDatabaseHelper.updateWinningGames(getEmailId());
                }
                profileDatabaseHelper.totalPlayedGame(getEmailId());
                return false;
            }
        }
        return true;
    }
    private void moveATokenOut() {
        token.place(getEmailId(), yard.getStartingPoint());
    }

    public String getEmailId() {
        return this.emailId;
    }
}
