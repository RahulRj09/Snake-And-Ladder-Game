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
    private boolean tokenOut = false;
    private String emailId;

    public Player(Yard yard, String emailId) {
        this.yard = yard;
        this.token = yard.getToken();
        this.emailId = emailId;
    }

    public boolean play(Dice dice) throws SQLException, FileNotFoundException {
        int numberOnDice = dice.roll();
        if (numberOnDice == 1 && !isTokenOut()) {
            moveATokenOut();
            return true;
        } else if (isTokenOut()) {
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
                ProfileDatabaseHelper profileDatabaseHelper = new ProfileDatabaseHelper();
                if (!getEmailId().equals(getLoggedUserEmailId())) {
                    profileDatabaseHelper.updateLostGames(getLoggedUserEmailId());
                    return false;
                }
                profileDatabaseHelper.updateWinningGames(getEmailId());
                return false;
            }
        }
        return true;
    }

    private String getLoggedUserEmailId() throws FileNotFoundException {
        File file = new File("/Users/rahul.joshi/SnakeAndLadderGame/src/main/java/resources/static/email.txt");
        Scanner sc = new Scanner(file);
        String input = String.valueOf(sc.next());
        return input;
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
