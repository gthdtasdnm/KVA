package cards;

import org.junit.Test;

import static cards.Rank.THREE;
import static cards.Rank.TWO;
import static cards.Suit.HEARTS;
import static cards.Suit.SPADES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CardTest {
    private static Card c(Rank r, Suit s) {
        return new Card(r, s);
    }

    @Test
    public void compareToTest() {
        assertEquals(0, c(TWO, HEARTS).compareTo(c(TWO, HEARTS)));
        assertTrue(c(TWO, HEARTS).compareTo(c(TWO, SPADES)) < 0);
        assertTrue(c(TWO, SPADES).compareTo(c(TWO, HEARTS)) > 0);
        assertTrue(c(TWO, SPADES).compareTo(c(THREE, HEARTS)) < 0);
        assertTrue(c(THREE, HEARTS).compareTo(c(TWO, SPADES)) > 0);
    }
}
