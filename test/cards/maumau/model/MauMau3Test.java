package cards.maumau.model;

import cards.Card;
import cards.Rank;
import cards.Suit;
import org.junit.Test;

import java.util.List;

import static cards.Rank.ACE;
import static cards.Rank.KING;
import static cards.Rank.QUEEN;
import static cards.Rank.SEVEN;
import static cards.Rank.TEN;
import static cards.Suit.CLUBS;
import static cards.Suit.DIAMONDS;
import static cards.Suit.HEARTS;
import static cards.Suit.SPADES;
import static cards.maumau.model.GameState.GAME_CANCELED;
import static cards.maumau.model.GameState.GAME_INITIALIZED;
import static cards.maumau.model.GameState.PLAY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MauMau3Test {
    private static Card c(Rank r, Suit s) {
        return new Card(r, s);
    }

    @Test
    public void game() {
        final List<Card> deck = List.of(c(SEVEN, SPADES), c(SEVEN, DIAMONDS), c(SEVEN, DIAMONDS), c(SEVEN, SPADES), c(SEVEN, SPADES), c(SEVEN, HEARTS), c(SEVEN, SPADES), c(ACE, DIAMONDS), c(KING, DIAMONDS), c(SEVEN, SPADES), c(TEN, HEARTS), c(ACE, HEARTS), c(QUEEN, CLUBS));
        final MauMau game = new MauMau(5, deck);
        final Player jacqueline = game.addPlayer("Jacqueline");
        final Player chantal = game.addPlayer("Chantal");

        assertEquals(GAME_INITIALIZED, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[]", jacqueline.getCards().toString());
        assertEquals("[]", chantal.getCards().toString());
        assertEquals("[7♠︎, 7♦︎, 7♦︎, 7♠︎, 7♠︎, 7♥︎, 7♠︎, A♦︎, K♦︎, 7♠︎, 10♥︎, A♥︎, Q♣︎]", game.getDrawPile().toString());
        assertEquals("[]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        game.startGame();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♠︎, 7♦︎, 7♠︎, 7♠︎, K♦︎]", jacqueline.getCards().toString());
        assertEquals("[7♦︎, 7♠︎, 7♥︎, A♦︎, 7♠︎]", chantal.getCards().toString());
        assertEquals("[A♥︎, Q♣︎]", game.getDrawPile().toString());
        assertEquals("[10♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♦︎, 7♠︎, 7♥︎, A♦︎, 7♠︎]", chantal.getCards().toString());
        assertEquals("[7♠︎, 7♦︎, 7♠︎, 7♠︎, K♦︎, A♥︎]", jacqueline.getCards().toString());
        assertEquals("[Q♣︎]", game.getDrawPile().toString());
        assertEquals("[10♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(SEVEN, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♠︎, 7♦︎, 7♠︎, 7♠︎, K♦︎, A♥︎]", jacqueline.getCards().toString());
        assertEquals("[7♦︎, 7♠︎, A♦︎, 7♠︎]", chantal.getCards().toString());
        assertEquals("[Q♣︎]", game.getDrawPile().toString());
        assertEquals("[7♥︎, 10♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        jacqueline.chooseCard(c(SEVEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♦︎, 7♠︎, A♦︎, 7♠︎]", chantal.getCards().toString());
        assertEquals("[7♠︎, 7♠︎, 7♠︎, K♦︎, A♥︎]", jacqueline.getCards().toString());
        assertEquals("[Q♣︎]", game.getDrawPile().toString());
        assertEquals("[7♦︎, 7♥︎, 10♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(2, game.get7Counter());

        chantal.chooseCard(c(SEVEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♠︎, 7♠︎, 7♠︎, K♦︎, A♥︎]", jacqueline.getCards().toString());
        assertEquals("[7♠︎, A♦︎, 7♠︎]", chantal.getCards().toString());
        assertEquals("[Q♣︎]", game.getDrawPile().toString());
        assertEquals("[7♦︎, 7♦︎, 7♥︎, 10♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(3, game.get7Counter());

        jacqueline.chooseCard(c(SEVEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♠︎, A♦︎, 7♠︎]", chantal.getCards().toString());
        assertEquals("[7♠︎, 7♠︎, K♦︎, A♥︎]", jacqueline.getCards().toString());
        assertEquals("[Q♣︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, 7♦︎, 7♦︎, 7♥︎, 10♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(4, game.get7Counter());

        chantal.chooseCard(c(SEVEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♠︎, 7♠︎, K♦︎, A♥︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎, 7♠︎]", chantal.getCards().toString());
        assertEquals("[Q♣︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, 7♠︎, 7♦︎, 7♦︎, 7♥︎, 10♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(5, game.get7Counter());

        jacqueline.chooseCard(c(SEVEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♦︎, 7♠︎]", chantal.getCards().toString());
        assertEquals("[7♠︎, K♦︎, A♥︎]", jacqueline.getCards().toString());
        assertEquals("[Q♣︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, 7♠︎, 7♠︎, 7♦︎, 7♦︎, 7♥︎, 10♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(6, game.get7Counter());

        chantal.chooseCard(c(SEVEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♠︎, K♦︎, A♥︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎]", chantal.getCards().toString());
        assertEquals("[Q♣︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, 7♠︎, 7♠︎, 7♠︎, 7♦︎, 7♦︎, 7♥︎, 10♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(7, game.get7Counter());

        chantal.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♠︎, K♦︎, A♥︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎]", chantal.getCards().toString());
        assertEquals("[Q♣︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, 7♠︎, 7♠︎, 7♠︎, 7♦︎, 7♦︎, 7♥︎, 10♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(7, game.get7Counter());

        jacqueline.chooseCard(c(SEVEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♦︎]", chantal.getCards().toString());
        assertEquals("[K♦︎, A♥︎]", jacqueline.getCards().toString());
        assertEquals("[Q♣︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, 7♠︎, 7♠︎, 7♠︎, 7♠︎, 7♦︎, 7♦︎, 7♥︎, 10♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(8, game.get7Counter());

        chantal.no7();
        assertEquals(GAME_CANCELED, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
    }
}