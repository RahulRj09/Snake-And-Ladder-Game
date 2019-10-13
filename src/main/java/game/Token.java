package game;

import DatabaseHelper.TokenDatabaseHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Token {
    private TokenDatabaseHelper tokenDatabaseHelper = new TokenDatabaseHelper();
    Map<Integer, Integer> laddersPositions = new HashMap<>() {{
        put(4, 25);
        put(13, 46);
        put(33, 49);
        put(42, 63);
        put(50, 69);
        put(62, 89);
        put(74, 92);
    }};

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

    public int checkSnakeAndLadderPosition() {
        return 0;
    }

}
