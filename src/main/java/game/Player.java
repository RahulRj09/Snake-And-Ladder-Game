package game;

import DatabaseHelper.ProfileDatabaseHelper;
import DatabaseHelper.TokenDatabaseHelper;

import java.io.FileNotFoundException;
import java.sql.SQLException;

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

    public void play(Dice dice) throws SQLException, FileNotFoundException {
        TokenDatabaseHelper tokenDatabaseHelper = new TokenDatabaseHelper();
        int numberOnDice = dice.roll();
        boolean b = tokenDatabaseHelper.positionRowExistsOrNotForCurrentUser(getEmailId());
        if (!b && numberOnDice == 1) {
            moveATokenOut();
        } else if (b) {
            moveAToken(numberOnDice);
        }
    }

    private void moveAToken(int numberOnDice) {
        int position = token.getPosition(getEmailId());
        if (position + numberOnDice <= yard.getEndingPoint()) {
            token.setPosition(getEmailId(), numberOnDice);
            if (token.getPosition(getEmailId()) == yard.getEndingPoint()) {
                if (!getEmailId().equals(getEmailId())) {
                    profileDatabaseHelper.updateLostGames(getEmailId());
                }
                if (getEmailId().equals(getEmailId())) {
                    profileDatabaseHelper.updateWinningGames(getEmailId());
                }
                profileDatabaseHelper.totalPlayedGame(getEmailId());
            }
        }
    }

    public void moveATokenOut() {
        token.place(getEmailId(), yard.getStartingPoint());
    }

    public String getEmailId() {
        return this.emailId;
    }
}
