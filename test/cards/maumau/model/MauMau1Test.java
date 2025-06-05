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
import static cards.maumau.model.GameState.CHOOSE_SUIT;
import static cards.maumau.model.GameState.GAME_INITIALIZED;
import static cards.maumau.model.GameState.GAME_OVER;
import static cards.maumau.model.GameState.PLAY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MauMau1Test {
    private static Card c(Rank r, Suit s) {
        return new Card(r, s);
    }

    @Test
    public void game() {
        final List<Card> deck = List.of(c(JACK, DIAMONDS), c(SEVEN, CLUBS), c(QUEEN, DIAMONDS), c(NINE, CLUBS), c(ACE, SPADES), c(NINE, SPADES), c(ACE, DIAMONDS), c(EIGHT, CLUBS), c(EIGHT, DIAMONDS), c(KING, HEARTS), c(ACE, HEARTS), c(SEVEN, SPADES), c(KING, CLUBS), c(KING, SPADES), c(QUEEN, SPADES), c(SEVEN, DIAMONDS), c(TEN, DIAMONDS), c(EIGHT, HEARTS), c(KING, DIAMONDS), c(QUEEN, CLUBS), c(JACK, HEARTS), c(EIGHT, SPADES), c(TEN, CLUBS), c(ACE, CLUBS), c(JACK, CLUBS), c(QUEEN, HEARTS), c(SEVEN, HEARTS), c(JACK, SPADES), c(NINE, DIAMONDS), c(NINE, HEARTS), c(TEN, HEARTS), c(TEN, SPADES));
        final MauMau game = new MauMau(5, deck);
        final Player jacqueline = game.addPlayer("Jacqueline");
        final Player chantal = game.addPlayer("Chantal");

        assertEquals(GAME_INITIALIZED, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[]", jacqueline.getCards().toString());
        assertEquals("[]", chantal.getCards().toString());
        assertEquals("[J♦︎, 7♣︎, Q♦︎, 9♣︎, A♠︎, 9♠︎, A♦︎, 8♣︎, 8♦︎, K♥︎, A♥︎, 7♠︎, K♣︎, K♠︎, Q♠︎, 7♦︎, 10♦︎, 8♥︎, K♦︎, Q♣︎, J♥︎, 8♠︎, 10♣︎, A♣︎, J♣︎, Q♥︎, 7♥︎, J♠︎, 9♦︎, 9♥︎, 10♥︎, 10♠︎]", game.getDrawPile().toString());
        assertEquals("[]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        game.startGame();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♦︎, Q♦︎, A♠︎, A♦︎, 8♦︎]", jacqueline.getCards().toString());
        assertEquals("[7♣︎, 9♣︎, 9♠︎, 8♣︎, K♥︎]", chantal.getCards().toString());
        assertEquals("[7♠︎, K♣︎, K♠︎, Q♠︎, 7♦︎, 10♦︎, 8♥︎, K♦︎, Q♣︎, J♥︎, 8♠︎, 10♣︎, A♣︎, J♣︎, Q♥︎, 7♥︎, J♠︎, 9♦︎, 9♥︎, 10♥︎, 10♠︎]", game.getDrawPile().toString());
        assertEquals("[A♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(ACE, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♣︎, 9♣︎, 9♠︎, 8♣︎, K♥︎]", chantal.getCards().toString());
        assertEquals("[J♦︎, Q♦︎, A♦︎, 8♦︎]", jacqueline.getCards().toString());
        assertEquals("[7♠︎, K♣︎, K♠︎, Q♠︎, 7♦︎, 10♦︎, 8♥︎, K♦︎, Q♣︎, J♥︎, 8♠︎, 10♣︎, A♣︎, J♣︎, Q♥︎, 7♥︎, J♠︎, 9♦︎, 9♥︎, 10♥︎, 10♠︎]", game.getDrawPile().toString());
        assertEquals("[A♠︎, A♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(NINE, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♦︎, Q♦︎, A♦︎, 8♦︎]", jacqueline.getCards().toString());
        assertEquals("[7♣︎, 9♣︎, 8♣︎, K♥︎]", chantal.getCards().toString());
        assertEquals("[7♠︎, K♣︎, K♠︎, Q♠︎, 7♦︎, 10♦︎, 8♥︎, K♦︎, Q♣︎, J♥︎, 8♠︎, 10♣︎, A♣︎, J♣︎, Q♥︎, 7♥︎, J♠︎, 9♦︎, 9♥︎, 10♥︎, 10♠︎]", game.getDrawPile().toString());
        assertEquals("[9♠︎, A♠︎, A♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(JACK, DIAMONDS));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♦︎, A♦︎, 8♦︎]", jacqueline.getCards().toString());
        assertEquals("[7♣︎, 9♣︎, 8♣︎, K♥︎]", chantal.getCards().toString());
        assertEquals("[7♠︎, K♣︎, K♠︎, Q♠︎, 7♦︎, 10♦︎, 8♥︎, K♦︎, Q♣︎, J♥︎, 8♠︎, 10♣︎, A♣︎, J♣︎, Q♥︎, 7♥︎, J♠︎, 9♦︎, 9♥︎, 10♥︎, 10♠︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, 9♠︎, A♠︎, A♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseSuit(DIAMONDS);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♣︎, 9♣︎, 8♣︎, K♥︎]", chantal.getCards().toString());
        assertEquals("[Q♦︎, A♦︎, 8♦︎]", jacqueline.getCards().toString());
        assertEquals("[7♠︎, K♣︎, K♠︎, Q♠︎, 7♦︎, 10♦︎, 8♥︎, K♦︎, Q♣︎, J♥︎, 8♠︎, 10♣︎, A♣︎, J♣︎, Q♥︎, 7♥︎, J♠︎, 9♦︎, 9♥︎, 10♥︎, 10♠︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, 9♠︎, A♠︎, A♥︎]", game.getDiscardPile().toString());
        assertEquals(DIAMONDS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♦︎, A♦︎, 8♦︎]", jacqueline.getCards().toString());
        assertEquals("[7♣︎, 9♣︎, 8♣︎, K♥︎, 7♠︎]", chantal.getCards().toString());
        assertEquals("[K♣︎, K♠︎, Q♠︎, 7♦︎, 10♦︎, 8♥︎, K♦︎, Q♣︎, J♥︎, 8♠︎, 10♣︎, A♣︎, J♣︎, Q♥︎, 7♥︎, J♠︎, 9♦︎, 9♥︎, 10♥︎, 10♠︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, 9♠︎, A♠︎, A♥︎]", game.getDiscardPile().toString());
        assertEquals(DIAMONDS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(EIGHT, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♦︎, A♦︎]", jacqueline.getCards().toString());
        assertEquals("[7♣︎, 9♣︎, 8♣︎, K♥︎, 7♠︎]", chantal.getCards().toString());
        assertEquals("[K♣︎, K♠︎, Q♠︎, 7♦︎, 10♦︎, 8♥︎, K♦︎, Q♣︎, J♥︎, 8♠︎, 10♣︎, A♣︎, J♣︎, Q♥︎, 7♥︎, J♠︎, 9♦︎, 9♥︎, 10♥︎, 10♠︎]", game.getDrawPile().toString());
        assertEquals("[8♦︎, J♦︎, 9♠︎, A♠︎, A♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(QUEEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♣︎, 9♣︎, 8♣︎, K♥︎, 7♠︎]", chantal.getCards().toString());
        assertEquals("[A♦︎]", jacqueline.getCards().toString());
        assertEquals("[K♣︎, K♠︎, Q♠︎, 7♦︎, 10♦︎, 8♥︎, K♦︎, Q♣︎, J♥︎, 8♠︎, 10♣︎, A♣︎, J♣︎, Q♥︎, 7♥︎, J♠︎, 9♦︎, 9♥︎, 10♥︎, 10♠︎]", game.getDrawPile().toString());
        assertEquals("[Q♦︎, 8♦︎, J♦︎, 9♠︎, A♠︎, A♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♣︎, 9♣︎, 8♣︎, K♥︎, 7♠︎]", chantal.getCards().toString());
        assertEquals("[A♦︎]", jacqueline.getCards().toString());
        assertEquals("[K♣︎, K♠︎, Q♠︎, 7♦︎, 10♦︎, 8♥︎, K♦︎, Q♣︎, J♥︎, 8♠︎, 10♣︎, A♣︎, J♣︎, Q♥︎, 7♥︎, J♠︎, 9♦︎, 9♥︎, 10♥︎, 10♠︎]", game.getDrawPile().toString());
        assertEquals("[Q♦︎, 8♦︎, J♦︎, 9♠︎, A♠︎, A♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♦︎]", jacqueline.getCards().toString());
        assertEquals("[7♣︎, 9♣︎, 8♣︎, K♥︎, 7♠︎, K♣︎]", chantal.getCards().toString());
        assertEquals("[K♠︎, Q♠︎, 7♦︎, 10♦︎, 8♥︎, K♦︎, Q♣︎, J♥︎, 8♠︎, 10♣︎, A♣︎, J♣︎, Q♥︎, 7♥︎, J♠︎, 9♦︎, 9♥︎, 10♥︎, 10♠︎]", game.getDrawPile().toString());
        assertEquals("[Q♦︎, 8♦︎, J♦︎, 9♠︎, A♠︎, A♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(ACE, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♣︎, 9♣︎, 8♣︎, K♥︎, 7♠︎, K♣︎]", chantal.getCards().toString());
        assertEquals("[]", jacqueline.getCards().toString());
        assertEquals("[K♠︎, Q♠︎, 7♦︎, 10♦︎, 8♥︎, K♦︎, Q♣︎, J♥︎, 8♠︎, 10♣︎, A♣︎, J♣︎, Q♥︎, 7♥︎, J♠︎, 9♦︎, 9♥︎, 10♥︎, 10♠︎]", game.getDrawPile().toString());
        assertEquals("[A♦︎, Q♦︎, 8♦︎, J♦︎, 9♠︎, A♠︎, A♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.maumau();
        assertEquals(GAME_OVER, game.getGameState());
        assertEquals(List.of(), game.getPlayers());
        assertEquals(List.of(jacqueline, chantal), game.getRanking());
        assertEquals("[K♠︎, Q♠︎, 7♦︎, 10♦︎, 8♥︎, K♦︎, Q♣︎, J♥︎, 8♠︎, 10♣︎, A♣︎, J♣︎, Q♥︎, 7♥︎, J♠︎, 9♦︎, 9♥︎, 10♥︎, 10♠︎]", game.getDrawPile().toString());
        assertEquals("[A♦︎, Q♦︎, 8♦︎, J♦︎, 9♠︎, A♠︎, A♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());
    }
}
