package cards;

import org.junit.Test;

import static cards.Rank.ACE;
import static cards.Rank.EIGHT;
import static cards.Rank.FIVE;
import static cards.Rank.FOUR;
import static cards.Rank.JACK;
import static cards.Rank.KING;
import static cards.Rank.NINE;
import static cards.Rank.QUEEN;
import static cards.Rank.SEVEN;
import static cards.Rank.SIX;
import static cards.Rank.TEN;
import static cards.Rank.THREE;
import static cards.Rank.TWO;
import static org.junit.Assert.assertEquals;

public class RankTest {
    @Test
    public void toStringTest() {
        assertEquals("2", TWO.toString());
        assertEquals("3", THREE.toString());
        assertEquals("4", FOUR.toString());
        assertEquals("5", FIVE.toString());
        assertEquals("6", SIX.toString());
        assertEquals("7", SEVEN.toString());
        assertEquals("8", EIGHT.toString());
        assertEquals("9", NINE.toString());
        assertEquals("10", TEN.toString());
        assertEquals("J", JACK.toString());
        assertEquals("Q", QUEEN.toString());
        assertEquals("K", KING.toString());
        assertEquals("A", ACE.toString());
    }
}
