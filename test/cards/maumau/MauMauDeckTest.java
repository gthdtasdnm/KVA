package cards.maumau;

import cards.Card;
import cards.Rank;
import cards.Suit;
import org.junit.Test;

import java.util.List;

import static cards.Rank.ACE;
import static cards.Rank.SIX;
import static cards.Rank.TEN;
import static cards.Rank.TWO;
import static cards.Suit.DIAMONDS;
import static cards.Suit.HEARTS;
import static cards.Suit.SPADES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MauMauDeckTest {
    private static Card c(Rank r, Suit s) {
        return new Card(r, s);
    }

    @Test
    public void dealCardsTest() {
        final List<Card> deck = MauMauDeck.makeDeck(5);
        assertEquals(160, deck.size());
        assertFalse(deck.contains(c(TWO, DIAMONDS)));
        assertFalse(deck.contains(c(SIX, SPADES)));
        assertEquals(5, deck.stream().filter(c -> c.equals(c(TEN, HEARTS))).count());
        assertEquals(5, deck.stream().filter(c -> c.equals(c(ACE, SPADES))).count());
    }
}
