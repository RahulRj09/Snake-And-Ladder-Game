package game;

import DatabaseHelper.TokenDatabaseHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Token {
    private TokenDatabaseHelper tokenDatabaseHelper = new TokenDatabaseHelper();

    public void place(String emailId, int startingPoint) {
        tokenDatabaseHelper.insert(emailId, startingPoint);
    }

    public void setPosition(String emailId, int numberOnDice) {
        int position = numberOnDice + getPosition(emailId);
        tokenDatabaseHelper.updatePosition(emailId, position);
    }

    public int getPosition(String emailId) {
        JSONObject position = tokenDatabaseHelper.getCurrentPosition(emailId);
        JSONArray positionArray = (JSONArray) position.get("details");
        JSONObject positionA = (JSONObject) positionArray.get(0);
        return (int) positionA.get("position");
    }
}
