package game;

import DatabaseHelper.Profile;
import DatabaseHelper.TokenDatabaseHelper;

public class Player {
    private final Token token;
    private Yard yard;
    private String emailId;
    private Profile profile = new Profile();

    public Player(Yard yard, String emailId) {
        this.yard = yard;
        this.token = yard.getToken();
        this.emailId = emailId;
    }

    public void play(Dice dice) {
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
                    profile.updateLostGames(getEmailId());
                }
                if (getEmailId().equals(getEmailId())) {
                    profile.updateWinningGames(getEmailId());
                }
                profile.totalPlayedGame(getEmailId());
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
