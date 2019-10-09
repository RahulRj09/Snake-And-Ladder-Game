package game;

import DatabaseHelper.TokenDatabaseHelper;
import org.json.simple.JSONObject;

public class Token {
    private int position = -1;
    private TokenDatabaseHelper tokenDatabaseHelper = new TokenDatabaseHelper();

    public void place(String emailId, int startingPoint) {
        tokenDatabaseHelper.insert(emailId, startingPoint);
        this.position = startingPoint;
    }

    public void setPosition(String emailId, int numberOnDice) {
        this.position += numberOnDice;
        tokenDatabaseHelper.updatePosition(emailId, this.position);
    }

    public int getPosition(String emailId) {
        JSONObject positions = tokenDatabaseHelper.getCurrentPosition(emailId);
        return (int) positions.get("position");
    }
}
