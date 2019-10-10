package game;

import DatabaseHelper.TokenDatabaseHelper;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


public class PlayerTest {
    @Test
    public  void  tokenShouldBeMoveOut() throws FileNotFoundException, SQLException {
        Yard red = new Yard(new Token(), "red");
        Player rahul = new Player(red, "hello1@gmail.com");
        Dice dice = spy(new Dice());
        when(dice.roll()).thenReturn(1);
        rahul.play(dice);
        JSONObject position  = new TokenDatabaseHelper().getCurrentPosition("hello1@gmail.com");
        assertEquals(1, position.get("position"));
    }
}
