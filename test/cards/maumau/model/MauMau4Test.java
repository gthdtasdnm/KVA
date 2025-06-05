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

public class MauMau4Test {
    private static Card c(Rank r, Suit s) {
        return new Card(r, s);
    }

    @Test
    public void game() {
        final List<Card> deck = List.of(c(TEN, DIAMONDS), c(KING, DIAMONDS), c(JACK, CLUBS), c(TEN, SPADES), c(EIGHT, SPADES), c(JACK, HEARTS), c(SEVEN, DIAMONDS), c(JACK, SPADES), c(SEVEN, SPADES), c(QUEEN, SPADES), c(KING, SPADES), c(NINE, CLUBS), c(QUEEN, CLUBS), c(EIGHT, CLUBS), c(NINE, SPADES), c(TEN, CLUBS), c(ACE, HEARTS), c(SEVEN, HEARTS), c(NINE, DIAMONDS), c(EIGHT, HEARTS), c(SEVEN, CLUBS), c(QUEEN, DIAMONDS), c(ACE, SPADES), c(KING, HEARTS), c(QUEEN, HEARTS), c(EIGHT, DIAMONDS), c(ACE, CLUBS), c(TEN, HEARTS), c(JACK, DIAMONDS), c(NINE, HEARTS), c(KING, CLUBS), c(ACE, DIAMONDS));
        final MauMau game = new MauMau(5, deck);
        final Player jacqueline = game.addPlayer("Jacqueline");
        final Player chantal = game.addPlayer("Chantal");

        assertEquals(GAME_INITIALIZED, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[]", jacqueline.getCards().toString());
        assertEquals("[]", chantal.getCards().toString());
        assertEquals("[10♦︎, K♦︎, J♣︎, 10♠︎, 8♠︎, J♥︎, 7♦︎, J♠︎, 7♠︎, Q♠︎, K♠︎, 9♣︎, Q♣︎, 8♣︎, 9♠︎, 10♣︎, A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        game.startGame();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♦︎, J♣︎, 8♠︎, 7♦︎, 7♠︎]", jacqueline.getCards().toString());
        assertEquals("[K♦︎, 10♠︎, J♥︎, J♠︎, Q♠︎]", chantal.getCards().toString());
        assertEquals("[9♣︎, Q♣︎, 8♣︎, 9♠︎, 10♣︎, A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(SEVEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♦︎, 10♠︎, J♥︎, J♠︎, Q♠︎]", chantal.getCards().toString());
        assertEquals("[10♦︎, J♣︎, 8♠︎, 7♦︎]", jacqueline.getCards().toString());
        assertEquals("[9♣︎, Q♣︎, 8♣︎, 9♠︎, 10♣︎, A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        chantal.no7();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♦︎, 10♠︎, J♥︎, J♠︎, Q♠︎, 9♣︎, Q♣︎]", chantal.getCards().toString());
        assertEquals("[10♦︎, J♣︎, 8♠︎, 7♦︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, 9♠︎, 10♣︎, A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(TEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♦︎, J♣︎, 8♠︎, 7♦︎]", jacqueline.getCards().toString());
        assertEquals("[K♦︎, J♥︎, J♠︎, Q♠︎, 9♣︎, Q♣︎]", chantal.getCards().toString());
        assertEquals("[8♣︎, 9♠︎, 10♣︎, A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(EIGHT, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♦︎, J♣︎, 7♦︎]", jacqueline.getCards().toString());
        assertEquals("[K♦︎, J♥︎, J♠︎, Q♠︎, 9♣︎, Q♣︎]", chantal.getCards().toString());
        assertEquals("[8♣︎, 9♠︎, 10♣︎, A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(JACK, CLUBS));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♦︎, 7♦︎]", jacqueline.getCards().toString());
        assertEquals("[K♦︎, J♥︎, J♠︎, Q♠︎, 9♣︎, Q♣︎]", chantal.getCards().toString());
        assertEquals("[8♣︎, 9♠︎, 10♣︎, A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseSuit(DIAMONDS);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♦︎, J♥︎, J♠︎, Q♠︎, 9♣︎, Q♣︎]", chantal.getCards().toString());
        assertEquals("[10♦︎, 7♦︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, 9♠︎, 10♣︎, A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertEquals(DIAMONDS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(JACK, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♦︎, J♥︎, J♠︎, Q♠︎, 9♣︎, Q♣︎]", chantal.getCards().toString());
        assertEquals("[10♦︎, 7♦︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, 9♠︎, 10♣︎, A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertEquals(DIAMONDS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(KING, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♦︎, 7♦︎]", jacqueline.getCards().toString());
        assertEquals("[J♥︎, J♠︎, Q♠︎, 9♣︎, Q♣︎]", chantal.getCards().toString());
        assertEquals("[8♣︎, 9♠︎, 10♣︎, A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(SEVEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♥︎, J♠︎, Q♠︎, 9♣︎, Q♣︎]", chantal.getCards().toString());
        assertEquals("[10♦︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, 9♠︎, 10♣︎, A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        jacqueline.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♥︎, J♠︎, Q♠︎, 9♣︎, Q♣︎]", chantal.getCards().toString());
        assertEquals("[10♦︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, 9♠︎, 10♣︎, A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        chantal.no7();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♥︎, J♠︎, Q♠︎, 9♣︎, Q♣︎, 8♣︎, 9♠︎]", chantal.getCards().toString());
        assertEquals("[10♦︎]", jacqueline.getCards().toString());
        assertEquals("[10♣︎, A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(JACK, HEARTS));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♠︎, Q♠︎, 9♣︎, Q♣︎, 8♣︎, 9♠︎]", chantal.getCards().toString());
        assertEquals("[10♦︎]", jacqueline.getCards().toString());
        assertEquals("[10♣︎, A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseSuit(CLUBS);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♦︎]", jacqueline.getCards().toString());
        assertEquals("[J♠︎, Q♠︎, 9♣︎, Q♣︎, 8♣︎, 9♠︎]", chantal.getCards().toString());
        assertEquals("[10♣︎, A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertEquals(CLUBS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♠︎, Q♠︎, 9♣︎, Q♣︎, 8♣︎, 9♠︎]", chantal.getCards().toString());
        assertEquals("[10♦︎, 10♣︎]", jacqueline.getCards().toString());
        assertEquals("[A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertEquals(CLUBS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(EIGHT, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♠︎, Q♠︎, 9♣︎, Q♣︎, 9♠︎]", chantal.getCards().toString());
        assertEquals("[10♦︎, 10♣︎]", jacqueline.getCards().toString());
        assertEquals("[A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[8♣︎, J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(NINE, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♦︎, 10♣︎]", jacqueline.getCards().toString());
        assertEquals("[J♠︎, Q♠︎, Q♣︎, 9♠︎]", chantal.getCards().toString());
        assertEquals("[A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[9♣︎, 8♣︎, J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(TEN, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♠︎, Q♠︎, Q♣︎, 9♠︎]", chantal.getCards().toString());
        assertEquals("[10♦︎]", jacqueline.getCards().toString());
        assertEquals("[A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[10♣︎, 9♣︎, 8♣︎, J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♠︎, Q♠︎, Q♣︎, 9♠︎]", chantal.getCards().toString());
        assertEquals("[10♦︎]", jacqueline.getCards().toString());
        assertEquals("[A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[10♣︎, 9♣︎, 8♣︎, J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(QUEEN, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♦︎]", jacqueline.getCards().toString());
        assertEquals("[J♠︎, Q♠︎, 9♠︎]", chantal.getCards().toString());
        assertEquals("[A♥︎, 7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[Q♣︎, 10♣︎, 9♣︎, 8♣︎, J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♠︎, Q♠︎, 9♠︎]", chantal.getCards().toString());
        assertEquals("[10♦︎, A♥︎]", jacqueline.getCards().toString());
        assertEquals("[7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[Q♣︎, 10♣︎, 9♣︎, 8♣︎, J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(QUEEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♦︎, A♥︎]", jacqueline.getCards().toString());
        assertEquals("[J♠︎, 9♠︎]", chantal.getCards().toString());
        assertEquals("[7♥︎, 9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[Q♠︎, Q♣︎, 10♣︎, 9♣︎, 8♣︎, J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♠︎, 9♠︎]", chantal.getCards().toString());
        assertEquals("[10♦︎, A♥︎, 7♥︎]", jacqueline.getCards().toString());
        assertEquals("[9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[Q♠︎, Q♣︎, 10♣︎, 9♣︎, 8♣︎, J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(JACK, SPADES));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♠︎]", chantal.getCards().toString());
        assertEquals("[10♦︎, A♥︎, 7♥︎]", jacqueline.getCards().toString());
        assertEquals("[9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[J♠︎, Q♠︎, Q♣︎, 10♣︎, 9♣︎, 8♣︎, J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseSuit(SPADES);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♦︎, A♥︎, 7♥︎]", jacqueline.getCards().toString());
        assertEquals("[9♠︎]", chantal.getCards().toString());
        assertEquals("[9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[J♠︎, Q♠︎, Q♣︎, 10♣︎, 9♣︎, 8♣︎, J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertEquals(SPADES, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♦︎, A♥︎, 7♥︎]", jacqueline.getCards().toString());
        assertEquals("[9♠︎]", chantal.getCards().toString());
        assertEquals("[9♦︎, 8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[J♠︎, Q♠︎, Q♣︎, 10♣︎, 9♣︎, 8♣︎, J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertEquals(SPADES, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♠︎]", chantal.getCards().toString());
        assertEquals("[10♦︎, A♥︎, 7♥︎, 9♦︎]", jacqueline.getCards().toString());
        assertEquals("[8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[J♠︎, Q♠︎, Q♣︎, 10♣︎, 9♣︎, 8♣︎, J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertEquals(SPADES, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(NINE, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♦︎, A♥︎, 7♥︎, 9♦︎]", jacqueline.getCards().toString());
        assertEquals("[]", chantal.getCards().toString());
        assertEquals("[8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[9♠︎, J♠︎, Q♠︎, Q♣︎, 10♣︎, 9♣︎, 8♣︎, J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.maumau();
        assertEquals(GAME_OVER, game.getGameState());
        assertEquals(List.of(), game.getPlayers());
        assertEquals(List.of(chantal, jacqueline), game.getRanking());
        assertEquals("[8♥︎, 7♣︎, Q♦︎, A♠︎, K♥︎, Q♥︎, 8♦︎, A♣︎, 10♥︎, J♦︎, 9♥︎, K♣︎, A♦︎]", game.getDrawPile().toString());
        assertEquals("[9♠︎, J♠︎, Q♠︎, Q♣︎, 10♣︎, 9♣︎, 8♣︎, J♥︎, 7♦︎, K♦︎, J♣︎, 8♠︎, 10♠︎, 7♠︎, K♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());
    }
}