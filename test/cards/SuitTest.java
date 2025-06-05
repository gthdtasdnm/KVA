package cards;

import org.junit.Test;

import static cards.Suit.CLUBS;
import static cards.Suit.DIAMONDS;
import static cards.Suit.HEARTS;
import static cards.Suit.SPADES;
import static org.junit.Assert.assertEquals;

public class SuitTest {
    @Test
    public void toStringTest() {
        assertEquals("♥︎", HEARTS.toString());
        assertEquals("♦︎", DIAMONDS.toString());
        assertEquals("♣︎", CLUBS.toString());
        assertEquals("♠︎", SPADES.toString());
    }
}
