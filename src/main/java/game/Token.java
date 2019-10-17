package game;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Token {
    private DatabaseHelper.Token token = new DatabaseHelper.Token();
    private Map<Integer, Integer> laddersPositions = new HashMap<>() {{
        put(4, 25);
        put(13, 46);
        put(33, 49);
        put(42, 63);
        put(50, 69);
        put(62, 81);
        put(74, 92);
    }};

    private Map<Integer, Integer> snakesPositions = new HashMap<>() {{
        put(27, 5);
        put(40, 3);
        put(43, 18);
        put(54, 31);
        put(66, 45);
        put(76, 58);
        put(89, 53);
        put(99, 41);
    }};

    public void place(String emailId, int startingPoint) {
        token.insert(emailId, startingPoint);
    }

    public void setPosition(String emailId, int numberOnDice) {
        int position = numberOnDice + getPosition(emailId);

        token.updatePosition(emailId, checkSnakeAndLadderPosition(position),numberOnDice);
    }

    public int getPosition(String emailId) {
        JSONObject position = token.getCurrentPosition(emailId);
        JSONArray positionArray = (JSONArray) position.get("details");
        JSONObject positionA = (JSONObject) positionArray.get(0);
        return (int) positionA.get("position");
    }

    public int checkSnakeAndLadderPosition(int position) {
        if (laddersPositions.containsKey(position)) {
            return laddersPositions.get(position);
        }
        if (snakesPositions.containsKey(position)) {
            return snakesPositions.get(position);
        }
        return position;
    }
}
