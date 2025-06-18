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

public class MauMau5Test {
    private static Card c(Rank r, Suit s) {
        return new Card(r, s);
    }

    @Test
    public void game() {
        final List<Card> deck = List.of(c(ACE, DIAMONDS), c(NINE, HEARTS), c(JACK, HEARTS), c(KING, SPADES), c(SEVEN, CLUBS), c(NINE, CLUBS), c(SEVEN, HEARTS), c(KING, DIAMONDS), c(TEN, HEARTS), c(ACE, CLUBS), c(JACK, SPADES), c(ACE, SPADES), c(SEVEN, DIAMONDS), c(ACE, HEARTS), c(QUEEN, SPADES), c(EIGHT, CLUBS), c(TEN, DIAMONDS), c(NINE, DIAMONDS), c(EIGHT, DIAMONDS), c(SEVEN, SPADES), c(QUEEN, HEARTS), c(JACK, CLUBS), c(KING, CLUBS), c(EIGHT, SPADES), c(JACK, DIAMONDS), c(EIGHT, HEARTS), c(QUEEN, CLUBS), c(NINE, SPADES), c(TEN, CLUBS), c(KING, HEARTS), c(TEN, SPADES), c(QUEEN, DIAMONDS));
        final MauMau game = new MauMau(5, deck);
        final Player jacqueline = game.addPlayer("Jacqueline");
        final Player chantal = game.addPlayer("Chantal");

        assertEquals(GAME_INITIALIZED, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[]", jacqueline.getCards().toString());
        assertEquals("[]", chantal.getCards().toString());
        assertEquals("[A♦︎, 9♥︎, J♥︎, K♠︎, 7♣︎, 9♣︎, 7♥︎, K♦︎, 10♥︎, A♣︎, J♠︎, A♠︎, 7♦︎, A♥︎, Q♠︎, 8♣︎, 10♦︎, 9♦︎, 8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        game.startGame();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♦︎, J♥︎, 7♣︎, 7♥︎, 10♥︎]", jacqueline.getCards().toString());
        assertEquals("[9♥︎, K♠︎, 9♣︎, K♦︎, A♣︎]", chantal.getCards().toString());
        assertEquals("[A♠︎, 7♦︎, A♥︎, Q♠︎, 8♣︎, 10♦︎, 9♦︎, 8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(JACK, HEARTS));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♦︎, 7♣︎, 7♥︎, 10♥︎]", jacqueline.getCards().toString());
        assertEquals("[9♥︎, K♠︎, 9♣︎, K♦︎, A♣︎]", chantal.getCards().toString());
        assertEquals("[A♠︎, 7♦︎, A♥︎, Q♠︎, 8♣︎, 10♦︎, 9♦︎, 8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseSuit(HEARTS);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎, K♠︎, 9♣︎, K♦︎, A♣︎]", chantal.getCards().toString());
        assertEquals("[A♦︎, 7♣︎, 7♥︎, 10♥︎]", jacqueline.getCards().toString());
        assertEquals("[A♠︎, 7♦︎, A♥︎, Q♠︎, 8♣︎, 10♦︎, 9♦︎, 8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertEquals(HEARTS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(NINE, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♦︎, 7♣︎, 7♥︎, 10♥︎]", jacqueline.getCards().toString());
        assertEquals("[K♠︎, 9♣︎, K♦︎, A♣︎]", chantal.getCards().toString());
        assertEquals("[A♠︎, 7♦︎, A♥︎, Q♠︎, 8♣︎, 10♦︎, 9♦︎, 8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(SEVEN, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♠︎, 9♣︎, K♦︎, A♣︎]", chantal.getCards().toString());
        assertEquals("[A♦︎, 7♣︎, 10♥︎]", jacqueline.getCards().toString());
        assertEquals("[A♠︎, 7♦︎, A♥︎, Q♠︎, 8♣︎, 10♦︎, 9♦︎, 8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        chantal.no7();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♠︎, 9♣︎, K♦︎, A♣︎, A♠︎, 7♦︎]", chantal.getCards().toString());
        assertEquals("[A♦︎, 7♣︎, 10♥︎]", jacqueline.getCards().toString());
        assertEquals("[A♥︎, Q♠︎, 8♣︎, 10♦︎, 9♦︎, 8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(SEVEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♦︎, 7♣︎, 10♥︎]", jacqueline.getCards().toString());
        assertEquals("[K♠︎, 9♣︎, K♦︎, A♣︎, A♠︎]", chantal.getCards().toString());
        assertEquals("[A♥︎, Q♠︎, 8♣︎, 10♦︎, 9♦︎, 8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        jacqueline.chooseCard(c(SEVEN, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♠︎, 9♣︎, K♦︎, A♣︎, A♠︎]", chantal.getCards().toString());
        assertEquals("[A♦︎, 10♥︎]", jacqueline.getCards().toString());
        assertEquals("[A♥︎, Q♠︎, 8♣︎, 10♦︎, 9♦︎, 8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(2, game.get7Counter());

        chantal.no7();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♠︎, 9♣︎, K♦︎, A♣︎, A♠︎, A♥︎, Q♠︎, 8♣︎, 10♦︎]", chantal.getCards().toString());
        assertEquals("[A♦︎, 10♥︎]", jacqueline.getCards().toString());
        assertEquals("[9♦︎, 8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(EIGHT, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♠︎, 9♣︎, K♦︎, A♣︎, A♠︎, A♥︎, Q♠︎, 10♦︎]", chantal.getCards().toString());
        assertEquals("[A♦︎, 10♥︎]", jacqueline.getCards().toString());
        assertEquals("[9♦︎, 8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(NINE, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♦︎, 10♥︎]", jacqueline.getCards().toString());
        assertEquals("[K♠︎, K♦︎, A♣︎, A♠︎, A♥︎, Q♠︎, 10♦︎]", chantal.getCards().toString());
        assertEquals("[9♦︎, 8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♠︎, K♦︎, A♣︎, A♠︎, A♥︎, Q♠︎, 10♦︎]", chantal.getCards().toString());
        assertEquals("[A♦︎, 10♥︎, 9♦︎]", jacqueline.getCards().toString());
        assertEquals("[8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(ACE, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♦︎, 10♥︎, 9♦︎]", jacqueline.getCards().toString());
        assertEquals("[K♠︎, K♦︎, A♠︎, A♥︎, Q♠︎, 10♦︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(ACE, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♠︎, K♦︎, A♠︎, A♥︎, Q♠︎, 10♦︎]", chantal.getCards().toString());
        assertEquals("[10♥︎, 9♦︎]", jacqueline.getCards().toString());
        assertEquals("[8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(ACE, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♥︎, 9♦︎]", jacqueline.getCards().toString());
        assertEquals("[K♠︎, K♦︎, A♥︎, Q♠︎, 10♦︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, 7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♠︎, K♦︎, A♥︎, Q♠︎, 10♦︎]", chantal.getCards().toString());
        assertEquals("[10♥︎, 9♦︎, 8♦︎]", jacqueline.getCards().toString());
        assertEquals("[7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(ACE, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♥︎, 9♦︎, 8♦︎]", jacqueline.getCards().toString());
        assertEquals("[K♠︎, K♦︎, Q♠︎, 10♦︎]", chantal.getCards().toString());
        assertEquals("[7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(TEN, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♠︎, K♦︎, Q♠︎, 10♦︎]", chantal.getCards().toString());
        assertEquals("[9♦︎, 8♦︎]", jacqueline.getCards().toString());
        assertEquals("[7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(TEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♦︎, 8♦︎]", jacqueline.getCards().toString());
        assertEquals("[K♠︎, K♦︎, Q♠︎]", chantal.getCards().toString());
        assertEquals("[7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(EIGHT, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♦︎]", jacqueline.getCards().toString());
        assertEquals("[K♠︎, K♦︎, Q♠︎]", chantal.getCards().toString());
        assertEquals("[7♠︎, Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(NINE, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♠︎, K♦︎, Q♠︎]", chantal.getCards().toString());
        assertEquals("[7♠︎]", jacqueline.getCards().toString());
        assertEquals("[Q♥︎, J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(KING, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♠︎, Q♥︎]", jacqueline.getCards().toString());
        assertEquals("[K♠︎, Q♠︎]", chantal.getCards().toString());
        assertEquals("[J♣︎, K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♠︎, Q♠︎]", chantal.getCards().toString());
        assertEquals("[7♠︎, Q♥︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(KING, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♠︎, Q♥︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[Q♠︎]", chantal.getCards().toString());
        assertEquals("[K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♠︎, Q♥︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[Q♠︎]", chantal.getCards().toString());
        assertEquals("[K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(SEVEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♠︎]", chantal.getCards().toString());
        assertEquals("[Q♥︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[K♣︎, 8♠︎, J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        chantal.no7();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♠︎, K♣︎, 8♠︎]", chantal.getCards().toString());
        assertEquals("[Q♥︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(EIGHT, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♠︎, K♣︎]", chantal.getCards().toString());
        assertEquals("[Q♥︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(QUEEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♥︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[K♣︎]", chantal.getCards().toString());
        assertEquals("[J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♥︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[K♣︎]", chantal.getCards().toString());
        assertEquals("[J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(QUEEN, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♣︎]", chantal.getCards().toString());
        assertEquals("[J♣︎]", jacqueline.getCards().toString());
        assertEquals("[J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[Q♥︎, Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♣︎]", chantal.getCards().toString());
        assertEquals("[J♣︎]", jacqueline.getCards().toString());
        assertEquals("[J♦︎, 8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[Q♥︎, Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♣︎]", jacqueline.getCards().toString());
        assertEquals("[K♣︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[Q♥︎, Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(JACK, CLUBS));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[]", jacqueline.getCards().toString());
        assertEquals("[K♣︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[J♣︎, Q♥︎, Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseSuit(CLUBS);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♣︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[]", jacqueline.getCards().toString());
        assertEquals("[8♥︎, Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[J♣︎, Q♥︎, Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertEquals(CLUBS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(KING, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♥︎]", jacqueline.getCards().toString());
        assertEquals("[J♦︎]", chantal.getCards().toString());
        assertEquals("[Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[K♣︎, J♣︎, Q♥︎, Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(EIGHT, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♥︎]", jacqueline.getCards().toString());
        assertEquals("[J♦︎]", chantal.getCards().toString());
        assertEquals("[Q♣︎, 9♠︎, 10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[K♣︎, J♣︎, Q♥︎, Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♦︎, 9♠︎]", chantal.getCards().toString());
        assertEquals("[8♥︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[K♣︎, J♣︎, Q♥︎, Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(JACK, DIAMONDS));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♠︎]", chantal.getCards().toString());
        assertEquals("[8♥︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, K♣︎, J♣︎, Q♥︎, Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseSuit(SPADES);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♥︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[9♠︎]", chantal.getCards().toString());
        assertEquals("[10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, K♣︎, J♣︎, Q♥︎, Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertEquals(SPADES, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♥︎, Q♣︎]", jacqueline.getCards().toString());
        assertEquals("[9♠︎]", chantal.getCards().toString());
        assertEquals("[10♣︎, K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, K♣︎, J♣︎, Q♥︎, Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertEquals(SPADES, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♠︎]", chantal.getCards().toString());
        assertEquals("[8♥︎, Q♣︎, 10♣︎]", jacqueline.getCards().toString());
        assertEquals("[K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, K♣︎, J♣︎, Q♥︎, Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertEquals(SPADES, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(NINE, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♥︎, Q♣︎, 10♣︎]", jacqueline.getCards().toString());
        assertEquals("[]", chantal.getCards().toString());
        assertEquals("[K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[9♠︎, J♦︎, K♣︎, J♣︎, Q♥︎, Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.maumau();
        assertEquals(GAME_OVER, game.getGameState());
        assertEquals(List.of(), game.getPlayers());
        assertEquals(List.of(chantal, jacqueline), game.getRanking());
        assertEquals("[K♥︎, 10♠︎, Q♦︎]", game.getDrawPile().toString());
        assertEquals("[9♠︎, J♦︎, K♣︎, J♣︎, Q♥︎, Q♠︎, 8♠︎, 7♠︎, K♠︎, K♦︎, 9♦︎, 8♦︎, 10♦︎, 10♥︎, A♥︎, A♠︎, A♦︎, A♣︎, 9♣︎, 8♣︎, 7♣︎, 7♦︎, 7♥︎, 9♥︎, J♥︎, J♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());
    }
}