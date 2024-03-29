package game;

import DatabaseHelper.Token;
import DatabaseHelper.Winner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


public class PlayerTest {
    @Test
    public void tokenShouldBeMoveOut() throws SQLException {

        Yard red = new Yard(new game.Token());
        Player rahul = new Player(red, "hello1@gmail.com");
        Dice dice = spy(new Dice());
        when(dice.roll()).thenReturn(1);
        rahul.play(dice);
        Token token = new Token();
        JSONObject position = token.getCurrentPosition("hello1@gmail.com");
        JSONArray positionArray = (JSONArray) position.get("details");
        JSONObject positionA = (JSONObject) positionArray.get(0);
        assertEquals(1, positionA.get("position"));
        Winner winner = new Winner();
        winner.tableTruncate();
    }

    @Test
    public void tokenShouldBeMove() throws SQLException {
        Yard red = new Yard(new game.Token());
        Player rahul = new Player(red, "hello1@gmail.com");
        Dice dice = spy(new Dice());
        when(dice.roll()).thenReturn(1);
        rahul.play(dice);
        when(dice.roll()).thenReturn(6);
        rahul.play(dice);
        Token token = new Token();
        JSONObject position = token.getCurrentPosition("hello1@gmail.com");
        JSONArray positionArray = (JSONArray) position.get("details");
        JSONObject positionA = (JSONObject) positionArray.get(0);
        assertEquals(7, positionA.get("position"));
        Winner winner = new Winner();
        winner.tableTruncate();

    }
}
