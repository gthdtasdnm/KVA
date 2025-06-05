package cards.maumau.model;

import cards.Card;
import cards.Rank;
import cards.Suit;
import org.junit.Test;

import java.util.List;

import static cards.Rank.ACE;
import static cards.Rank.EIGHT;
import static cards.Rank.JACK;
import static cards.Rank.KING;
import static cards.Rank.NINE;
import static cards.Rank.QUEEN;
import static cards.Rank.SEVEN;
import static cards.Rank.TEN;
import static cards.Suit.CLUBS;
import static cards.Suit.DIAMONDS;
import static cards.Suit.HEARTS;
import static cards.Suit.SPADES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CardHandlerTest {
    private static Card c(Rank r, Suit s) {
        return new Card(r, s);
    }

    @Test
    public void dealCardsTest() {
        final List<Card> deck = List.of(c(JACK, DIAMONDS), c(SEVEN, CLUBS), c(QUEEN, DIAMONDS), c(NINE, CLUBS), c(ACE, SPADES), c(NINE, SPADES), c(ACE, DIAMONDS), c(EIGHT, CLUBS), c(EIGHT, DIAMONDS), c(KING, HEARTS), c(ACE, HEARTS), c(SEVEN, SPADES), c(KING, CLUBS), c(KING, SPADES), c(QUEEN, SPADES), c(SEVEN, DIAMONDS), c(TEN, DIAMONDS), c(EIGHT, HEARTS), c(KING, DIAMONDS), c(QUEEN, CLUBS), c(JACK, HEARTS), c(EIGHT, SPADES), c(TEN, CLUBS), c(ACE, CLUBS), c(JACK, CLUBS), c(QUEEN, HEARTS), c(SEVEN, HEARTS), c(JACK, SPADES), c(NINE, DIAMONDS), c(NINE, HEARTS), c(TEN, HEARTS), c(TEN, SPADES));
        final MauMau game = new MauMau(5, deck);
        final Player jacqueline = game.addPlayer("Jacqueline");
        final Player chantal = game.addPlayer("Chantal");
        assertTrue(jacqueline.getCards().isEmpty());
        assertTrue(chantal.getCards().isEmpty());
        game.getCardHandler().dealCards();
        assertEquals("[J♦︎, Q♦︎, A♠︎, A♦︎, 8♦︎]", jacqueline.getCards().toString());
        assertEquals("[7♣︎, 9♣︎, 9♠︎, 8♣︎, K♥︎]", chantal.getCards().toString());
        assertEquals(c(ACE, HEARTS), game.getCardHandler().top());
    }
}
