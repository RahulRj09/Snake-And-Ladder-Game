package game;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class DiceTest {

    @Test
    public void diceShouldBeRoll() {
        Dice dice = spy(new Dice());
        when(dice.roll()).thenReturn(1);
        assertEquals(1, dice.roll());
    }
}