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

public class MauMau7Test {
    private static Card c(Rank r, Suit s) {
        return new Card(r, s);
    }

    @Test
    public void game() {
        final List<Card> deck = List.of(c(EIGHT, DIAMONDS), c(JACK, DIAMONDS), c(ACE, DIAMONDS), c(NINE, CLUBS), c(TEN, HEARTS), c(TEN, DIAMONDS), c(NINE, SPADES), c(SEVEN, HEARTS), c(QUEEN, CLUBS), c(ACE, SPADES), c(JACK, SPADES), c(JACK, CLUBS), c(SEVEN, DIAMONDS), c(JACK, HEARTS), c(TEN, SPADES), c(QUEEN, DIAMONDS), c(EIGHT, SPADES), c(QUEEN, SPADES), c(NINE, DIAMONDS), c(QUEEN, HEARTS), c(KING, CLUBS), c(KING, HEARTS), c(NINE, HEARTS), c(KING, DIAMONDS), c(EIGHT, HEARTS), c(EIGHT, CLUBS), c(TEN, CLUBS), c(SEVEN, CLUBS), c(SEVEN, SPADES), c(ACE, HEARTS), c(KING, SPADES), c(ACE, CLUBS));
        final MauMau game = new MauMau(5, deck);
        final Player jacqueline = game.addPlayer("Jacqueline");
        final Player chantal = game.addPlayer("Chantal");

        assertEquals(GAME_INITIALIZED, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[]", jacqueline.getCards().toString());
        assertEquals("[]", chantal.getCards().toString());
        assertEquals("[8♦︎, J♦︎, A♦︎, 9♣︎, 10♥︎, 10♦︎, 9♠︎, 7♥︎, Q♣︎, A♠︎, J♠︎, J♣︎, 7♦︎, J♥︎, 10♠︎, Q♦︎, 8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        game.startGame();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, A♦︎, 10♥︎, 9♠︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[J♦︎, 9♣︎, 10♦︎, 7♥︎, A♠︎]", chantal.getCards().toString());
        assertEquals("[J♣︎, 7♦︎, J♥︎, 10♠︎, Q♦︎, 8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(NINE, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♦︎, 9♣︎, 10♦︎, 7♥︎, A♠︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, A♦︎, 10♥︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[J♣︎, 7♦︎, J♥︎, 10♠︎, Q♦︎, 8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(ACE, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, A♦︎, 10♥︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[J♦︎, 9♣︎, 10♦︎, 7♥︎]", chantal.getCards().toString());
        assertEquals("[J♣︎, 7♦︎, J♥︎, 10♠︎, Q♦︎, 8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(ACE, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♦︎, 9♣︎, 10♦︎, 7♥︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, 10♥︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[J♣︎, 7♦︎, J♥︎, 10♠︎, Q♦︎, 8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(TEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, 10♥︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[J♦︎, 9♣︎, 7♥︎]", chantal.getCards().toString());
        assertEquals("[J♣︎, 7♦︎, J♥︎, 10♠︎, Q♦︎, 8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(TEN, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♦︎, 9♣︎, 7♥︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[J♣︎, 7♦︎, J♥︎, 10♠︎, Q♦︎, 8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(SEVEN, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[J♦︎, 9♣︎]", chantal.getCards().toString());
        assertEquals("[J♣︎, 7♦︎, J♥︎, 10♠︎, Q♦︎, 8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        jacqueline.no7();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, Q♣︎, J♣︎, 7♦︎]", jacqueline.getCards().toString());
        assertEquals("[J♦︎, 9♣︎]", chantal.getCards().toString());
        assertEquals("[J♥︎, 10♠︎, Q♦︎, 8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(SEVEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♦︎, 9♣︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, Q♣︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[J♥︎, 10♠︎, Q♦︎, 8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[7♦︎, 7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        chantal.no7();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♦︎, 9♣︎, J♥︎, 10♠︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, Q♣︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[Q♦︎, 8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[7♦︎, 7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(JACK, DIAMONDS));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♣︎, J♥︎, 10♠︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, Q♣︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[Q♦︎, 8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, 7♦︎, 7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseSuit(CLUBS);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, Q♣︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[9♣︎, J♥︎, 10♠︎]", chantal.getCards().toString());
        assertEquals("[Q♦︎, 8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, 7♦︎, 7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertEquals(CLUBS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(QUEEN, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♣︎, J♥︎, 10♠︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[Q♦︎, 8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[Q♣︎, J♦︎, 7♦︎, 7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(NINE, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[J♥︎, 10♠︎]", chantal.getCards().toString());
        assertEquals("[Q♦︎, 8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[9♣︎, Q♣︎, J♦︎, 7♦︎, 7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♥︎, 10♠︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, J♣︎, Q♦︎]", jacqueline.getCards().toString());
        assertEquals("[8♠︎, Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[9♣︎, Q♣︎, J♦︎, 7♦︎, 7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, J♣︎, Q♦︎]", jacqueline.getCards().toString());
        assertEquals("[J♥︎, 10♠︎, 8♠︎]", chantal.getCards().toString());
        assertEquals("[Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[9♣︎, Q♣︎, J♦︎, 7♦︎, 7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(JACK, CLUBS));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, Q♦︎]", jacqueline.getCards().toString());
        assertEquals("[J♥︎, 10♠︎, 8♠︎]", chantal.getCards().toString());
        assertEquals("[Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[J♣︎, 9♣︎, Q♣︎, J♦︎, 7♦︎, 7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseSuit(DIAMONDS);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♥︎, 10♠︎, 8♠︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, Q♦︎]", jacqueline.getCards().toString());
        assertEquals("[Q♠︎, 9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[J♣︎, 9♣︎, Q♣︎, J♦︎, 7♦︎, 7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertEquals(DIAMONDS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, Q♦︎]", jacqueline.getCards().toString());
        assertEquals("[J♥︎, 10♠︎, 8♠︎, Q♠︎]", chantal.getCards().toString());
        assertEquals("[9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[J♣︎, 9♣︎, Q♣︎, J♦︎, 7♦︎, 7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertEquals(DIAMONDS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(EIGHT, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♦︎]", jacqueline.getCards().toString());
        assertEquals("[J♥︎, 10♠︎, 8♠︎, Q♠︎]", chantal.getCards().toString());
        assertEquals("[9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[8♦︎, J♣︎, 9♣︎, Q♣︎, J♦︎, 7♦︎, 7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♦︎]", jacqueline.getCards().toString());
        assertEquals("[J♥︎, 10♠︎, 8♠︎, Q♠︎]", chantal.getCards().toString());
        assertEquals("[9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[8♦︎, J♣︎, 9♣︎, Q♣︎, J♦︎, 7♦︎, 7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(QUEEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♥︎, 10♠︎, 8♠︎, Q♠︎]", chantal.getCards().toString());
        assertEquals("[]", jacqueline.getCards().toString());
        assertEquals("[9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[Q♦︎, 8♦︎, J♣︎, 9♣︎, Q♣︎, J♦︎, 7♦︎, 7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.maumau();
        assertEquals(GAME_OVER, game.getGameState());
        assertEquals(List.of(), game.getPlayers());
        assertEquals(List.of(jacqueline, chantal), game.getRanking());
        assertEquals("[9♦︎, Q♥︎, K♣︎, K♥︎, 9♥︎, K♦︎, 8♥︎, 8♣︎, 10♣︎, 7♣︎, 7♠︎, A♥︎, K♠︎, A♣︎]", game.getDrawPile().toString());
        assertEquals("[Q♦︎, 8♦︎, J♣︎, 9♣︎, Q♣︎, J♦︎, 7♦︎, 7♥︎, 10♥︎, 10♦︎, A♦︎, A♠︎, 9♠︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());
    }
}