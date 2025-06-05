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
import static cards.maumau.model.GameState.GAME_INITIALIZED;
import static cards.maumau.model.GameState.GAME_OVER;
import static cards.maumau.model.GameState.PLAY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MauMau2Test {
    private static Card c(Rank r, Suit s) {
        return new Card(r, s);
    }

    @Test
    public void game() {
        final List<Card> deck = List.of(c(KING, SPADES), c(QUEEN, DIAMONDS), c(EIGHT, DIAMONDS), c(ACE, SPADES), c(TEN, SPADES), c(EIGHT, HEARTS), c(QUEEN, SPADES), c(ACE, DIAMONDS), c(KING, DIAMONDS), c(SEVEN, SPADES), c(TEN, CLUBS), c(ACE, HEARTS), c(QUEEN, CLUBS), c(NINE, HEARTS), c(KING, CLUBS), c(SEVEN, CLUBS), c(JACK, DIAMONDS), c(NINE, DIAMONDS), c(ACE, CLUBS), c(EIGHT, SPADES), c(TEN, DIAMONDS), c(JACK, SPADES), c(JACK, HEARTS), c(JACK, CLUBS), c(TEN, HEARTS), c(KING, HEARTS), c(QUEEN, HEARTS), c(SEVEN, HEARTS), c(SEVEN, DIAMONDS), c(EIGHT, CLUBS), c(NINE, CLUBS), c(NINE, SPADES));
        final MauMau game = new MauMau(5, deck);
        final Player jacqueline = game.addPlayer("Jacqueline");
        final Player chantal = game.addPlayer("Chantal");

        assertEquals(GAME_INITIALIZED, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[]", jacqueline.getCards().toString());
        assertEquals("[]", chantal.getCards().toString());
        assertEquals("[K♠︎, Q♦︎, 8♦︎, A♠︎, 10♠︎, 8♥︎, Q♠︎, A♦︎, K♦︎, 7♠︎, 10♣︎, A♥︎, Q♣︎, 9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        game.startGame();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♠︎, 8♦︎, 10♠︎, Q♠︎, K♦︎]", jacqueline.getCards().toString());
        assertEquals("[Q♦︎, A♠︎, 8♥︎, A♦︎, 7♠︎]", chantal.getCards().toString());
        assertEquals("[A♥︎, Q♣︎, 9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(TEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♦︎, A♠︎, 8♥︎, A♦︎, 7♠︎]", chantal.getCards().toString());
        assertEquals("[K♠︎, 8♦︎, Q♠︎, K♦︎]", jacqueline.getCards().toString());
        assertEquals("[A♥︎, Q♣︎, 9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(SEVEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♠︎, 8♦︎, Q♠︎, K♦︎]", jacqueline.getCards().toString());
        assertEquals("[Q♦︎, A♠︎, 8♥︎, A♦︎]", chantal.getCards().toString());
        assertEquals("[A♥︎, Q♣︎, 9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        jacqueline.no7();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♠︎, 8♦︎, Q♠︎, K♦︎, A♥︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[Q♦︎, A♠︎, 8♥︎, A♦︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(EIGHT, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♠︎, 8♦︎, Q♠︎, K♦︎, A♥︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[Q♦︎, A♠︎, 8♥︎, A♦︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(KING, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♦︎, A♠︎, 8♥︎, A♦︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, Q♠︎, K♦︎, A♥︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[K♠︎, 7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(ACE, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, Q♠︎, K♦︎, A♥︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[Q♦︎, 8♥︎, A♦︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[A♠︎, K♠︎, 7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(ACE, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♦︎, 8♥︎, A♦︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, Q♠︎, K♦︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[A♥︎, A♠︎, K♠︎, 7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(ACE, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, Q♠︎, K♦︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[Q♦︎, 8♥︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[A♦︎, A♥︎, A♠︎, K♠︎, 7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(EIGHT, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♠︎, K♦︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[Q♦︎, 8♥︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[8♦︎, A♦︎, A♥︎, A♠︎, K♠︎, 7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(KING, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♦︎, 8♥︎]", chantal.getCards().toString());
        assertEquals("[Q♠︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[K♦︎, 8♦︎, A♦︎, A♥︎, A♠︎, K♠︎, 7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(QUEEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♠︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[8♥︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[Q♦︎, K♦︎, 8♦︎, A♦︎, A♥︎, A♠︎, K♠︎, 7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♠︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[8♥︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[Q♦︎, K♦︎, 8♦︎, A♦︎, A♥︎, A♠︎, K♠︎, 7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(QUEEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♥︎]", chantal.getCards().toString());
        assertEquals("[Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[Q♠︎, Q♦︎, K♦︎, 8♦︎, A♦︎, A♥︎, A♠︎, K♠︎, 7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♥︎]", chantal.getCards().toString());
        assertEquals("[Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[9♥︎, K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[Q♠︎, Q♦︎, K♦︎, 8♦︎, A♦︎, A♥︎, A♠︎, K♠︎, 7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[8♥︎, 9♥︎]", chantal.getCards().toString());
        assertEquals("[K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[Q♠︎, Q♦︎, K♦︎, 8♦︎, A♦︎, A♥︎, A♠︎, K♠︎, 7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(QUEEN, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♥︎, 9♥︎]", chantal.getCards().toString());
        assertEquals("[]", jacqueline.getCards().toString());
        assertEquals("[K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[Q♣︎, Q♠︎, Q♦︎, K♦︎, 8♦︎, A♦︎, A♥︎, A♠︎, K♠︎, 7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.maumau();
        assertEquals(GAME_OVER, game.getGameState());
        assertEquals(List.of(), game.getPlayers());
        assertEquals(List.of(jacqueline, chantal), game.getRanking());
        assertEquals("[K♣︎, 7♣︎, J♦︎, 9♦︎, A♣︎, 8♠︎, 10♦︎, J♠︎, J♥︎, J♣︎, 10♥︎, K♥︎, Q♥︎, 7♥︎, 7♦︎, 8♣︎, 9♣︎, 9♠︎]", game.getDrawPile().toString());
        assertEquals("[Q♣︎, Q♠︎, Q♦︎, K♦︎, 8♦︎, A♦︎, A♥︎, A♠︎, K♠︎, 7♠︎, 10♠︎, 10♣︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());
    }
}