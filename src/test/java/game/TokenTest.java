package game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TokenTest {
    @Test
    public void placeMethodShouldBeWorking(){
        Token token = new Token();
        token.place("rahul@gmail.com",1);
        assertEquals(1,token.getPosition("rahul@gmail.com"));
    }
}
