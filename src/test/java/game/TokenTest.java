package game;

import DatabaseHelper.Winner;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class TokenTest {
    @Test
    public void placeMethodShouldBeWorking() throws SQLException {
        Token token = new Token();
        token.place("rahul@gmail.com", 1);
        assertEquals(1, token.getPosition("rahul@gmail.com"));
        new Winner().tableTruncate();
    }
}
