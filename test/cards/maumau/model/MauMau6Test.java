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
import static cards.maumau.model.GameState.PLAY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MauMau6Test {
    private static Card c(Rank r, Suit s) {
        return new Card(r, s);
    }

    @Test
    public void game() {
        final List<Card> deck = List.of(c(QUEEN, SPADES), c(TEN, SPADES), c(SEVEN, DIAMONDS), c(EIGHT, DIAMONDS), c(ACE, SPADES), c(QUEEN, CLUBS), c(EIGHT, SPADES), c(NINE, CLUBS), c(SEVEN, SPADES), c(ACE, CLUBS), c(EIGHT, HEARTS), c(QUEEN, HEARTS), c(JACK, DIAMONDS), c(KING, CLUBS), c(NINE, HEARTS), c(EIGHT, CLUBS), c(ACE, DIAMONDS), c(QUEEN, DIAMONDS), c(JACK, HEARTS), c(TEN, HEARTS), c(KING, HEARTS), c(KING, DIAMONDS), c(JACK, SPADES), c(KING, SPADES), c(TEN, CLUBS), c(NINE, SPADES), c(JACK, CLUBS), c(SEVEN, CLUBS), c(ACE, HEARTS), c(NINE, DIAMONDS), c(TEN, DIAMONDS), c(SEVEN, HEARTS));
        final MauMau game = new MauMau(5, deck);
        final Player jacqueline = game.addPlayer("Jacqueline");
        final Player chantal = game.addPlayer("Chantal");

        assertEquals(GAME_INITIALIZED, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[]", jacqueline.getCards().toString());
        assertEquals("[]", chantal.getCards().toString());
        assertEquals("[Q♠︎, 10♠︎, 7♦︎, 8♦︎, A♠︎, Q♣︎, 8♠︎, 9♣︎, 7♠︎, A♣︎, 8♥︎, Q♥︎, J♦︎, K♣︎, 9♥︎, 8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        game.startGame();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♠︎, 7♦︎, A♠︎, 8♠︎, 7♠︎]", jacqueline.getCards().toString());
        assertEquals("[10♠︎, 8♦︎, Q♣︎, 9♣︎, A♣︎]", chantal.getCards().toString());
        assertEquals("[Q♥︎, J♦︎, K♣︎, 9♥︎, 8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(EIGHT, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♠︎, 7♦︎, A♠︎, 7♠︎]", jacqueline.getCards().toString());
        assertEquals("[10♠︎, 8♦︎, Q♣︎, 9♣︎, A♣︎]", chantal.getCards().toString());
        assertEquals("[Q♥︎, J♦︎, K♣︎, 9♥︎, 8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(SEVEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♠︎, 8♦︎, Q♣︎, 9♣︎, A♣︎]", chantal.getCards().toString());
        assertEquals("[Q♠︎, 7♦︎, A♠︎]", jacqueline.getCards().toString());
        assertEquals("[Q♥︎, J♦︎, K♣︎, 9♥︎, 8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        chantal.no7();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♠︎, 8♦︎, Q♣︎, 9♣︎, A♣︎, Q♥︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[Q♠︎, 7♦︎, A♠︎]", jacqueline.getCards().toString());
        assertEquals("[K♣︎, 9♥︎, 8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(TEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♠︎, 7♦︎, A♠︎]", jacqueline.getCards().toString());
        assertEquals("[8♦︎, Q♣︎, 9♣︎, A♣︎, Q♥︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[K♣︎, 9♥︎, 8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(QUEEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, Q♣︎, 9♣︎, A♣︎, Q♥︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[7♦︎, A♠︎]", jacqueline.getCards().toString());
        assertEquals("[K♣︎, 9♥︎, 8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(QUEEN, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♦︎, A♠︎]", jacqueline.getCards().toString());
        assertEquals("[8♦︎, Q♣︎, 9♣︎, A♣︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[K♣︎, 9♥︎, 8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, Q♣︎, 9♣︎, A♣︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[7♦︎, A♠︎, K♣︎]", jacqueline.getCards().toString());
        assertEquals("[9♥︎, 8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(QUEEN, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♦︎, A♠︎, K♣︎]", jacqueline.getCards().toString());
        assertEquals("[8♦︎, 9♣︎, A♣︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, 8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(KING, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, 9♣︎, A♣︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[7♦︎, A♠︎]", jacqueline.getCards().toString());
        assertEquals("[9♥︎, 8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(NINE, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♦︎, A♠︎]", jacqueline.getCards().toString());
        assertEquals("[8♦︎, A♣︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, 8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, A♣︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[7♦︎, A♠︎, 9♥︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(ACE, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♦︎, A♠︎, 9♥︎]", jacqueline.getCards().toString());
        assertEquals("[8♦︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(ACE, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[7♦︎, 9♥︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(JACK, DIAMONDS));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎]", chantal.getCards().toString());
        assertEquals("[7♦︎, 9♥︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseSuit(DIAMONDS);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♦︎, 9♥︎]", jacqueline.getCards().toString());
        assertEquals("[8♦︎]", chantal.getCards().toString());
        assertEquals("[8♣︎, A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertEquals(DIAMONDS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(SEVEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, 8♣︎]", chantal.getCards().toString());
        assertEquals("[9♥︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎, Q♦︎, J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        chantal.no7();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, 8♣︎, A♦︎, Q♦︎]", chantal.getCards().toString());
        assertEquals("[9♥︎]", jacqueline.getCards().toString());
        assertEquals("[J♥︎, 10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(EIGHT, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♣︎, A♦︎, Q♦︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, J♥︎]", jacqueline.getCards().toString());
        assertEquals("[10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(ACE, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎, J♥︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, Q♦︎]", chantal.getCards().toString());
        assertEquals("[10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(JACK, HEARTS));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, Q♦︎]", chantal.getCards().toString());
        assertEquals("[10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseSuit(DIAMONDS);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♣︎, Q♦︎]", chantal.getCards().toString());
        assertEquals("[9♥︎]", jacqueline.getCards().toString());
        assertEquals("[10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertEquals(DIAMONDS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♣︎, Q♦︎]", chantal.getCards().toString());
        assertEquals("[9♥︎]", jacqueline.getCards().toString());
        assertEquals("[10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertEquals(DIAMONDS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(QUEEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎]", chantal.getCards().toString());
        assertEquals("[10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎]", chantal.getCards().toString());
        assertEquals("[10♥︎, K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♣︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, 10♥︎]", jacqueline.getCards().toString());
        assertEquals("[K♥︎, K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎, 10♥︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, K♥︎]", chantal.getCards().toString());
        assertEquals("[K♦︎, J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♣︎, K♥︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, 10♥︎, K♦︎]", jacqueline.getCards().toString());
        assertEquals("[J♠︎, K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎, 10♥︎, K♦︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, K♥︎, J♠︎]", chantal.getCards().toString());
        assertEquals("[K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(KING, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♣︎, K♥︎, J♠︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, 10♥︎]", jacqueline.getCards().toString());
        assertEquals("[K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(KING, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎, 10♥︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, J♠︎]", chantal.getCards().toString());
        assertEquals("[K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(TEN, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♣︎, J♠︎]", chantal.getCards().toString());
        assertEquals("[9♥︎]", jacqueline.getCards().toString());
        assertEquals("[K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(JACK, SPADES));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♣︎]", chantal.getCards().toString());
        assertEquals("[9♥︎]", jacqueline.getCards().toString());
        assertEquals("[K♠︎, 10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseSuit(CLUBS);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎, K♠︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎]", chantal.getCards().toString());
        assertEquals("[10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertEquals(CLUBS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎, K♠︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎]", chantal.getCards().toString());
        assertEquals("[10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertEquals(CLUBS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(EIGHT, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎, K♠︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎]", chantal.getCards().toString());
        assertEquals("[10♣︎, 9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertEquals(CLUBS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♣︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, K♠︎, 10♣︎]", jacqueline.getCards().toString());
        assertEquals("[9♠︎, J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertEquals(CLUBS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎, K♠︎, 10♣︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, 9♠︎]", chantal.getCards().toString());
        assertEquals("[J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertEquals(CLUBS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(TEN, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♣︎, 9♠︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, K♠︎]", jacqueline.getCards().toString());
        assertEquals("[J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[10♣︎, J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(EIGHT, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♠︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, K♠︎]", jacqueline.getCards().toString());
        assertEquals("[J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[8♣︎, 10♣︎, J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(NINE, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♠︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, K♠︎]", jacqueline.getCards().toString());
        assertEquals("[J♣︎, 7♣︎, A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[8♣︎, 10♣︎, J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎, K♠︎]", jacqueline.getCards().toString());
        assertEquals("[9♠︎, J♣︎, 7♣︎]", chantal.getCards().toString());
        assertEquals("[A♥︎, 9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[8♣︎, 10♣︎, J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♠︎, J♣︎, 7♣︎]", chantal.getCards().toString());
        assertEquals("[9♥︎, K♠︎, A♥︎]", jacqueline.getCards().toString());
        assertEquals("[9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[8♣︎, 10♣︎, J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(SEVEN, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎, K♠︎, A♥︎]", jacqueline.getCards().toString());
        assertEquals("[9♠︎, J♣︎]", chantal.getCards().toString());
        assertEquals("[9♦︎, 10♦︎, 7♥︎]", game.getDrawPile().toString());
        assertEquals("[7♣︎, 8♣︎, 10♣︎, J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        jacqueline.no7();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎, K♠︎, A♥︎, 9♦︎, 10♦︎]", jacqueline.getCards().toString());
        assertEquals("[9♠︎, J♣︎]", chantal.getCards().toString());
        assertEquals("[7♥︎]", game.getDrawPile().toString());
        assertEquals("[7♣︎, 8♣︎, 10♣︎, J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(JACK, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎, K♠︎, A♥︎, 9♦︎, 10♦︎]", jacqueline.getCards().toString());
        assertEquals("[9♠︎, J♣︎]", chantal.getCards().toString());
        assertEquals("[7♥︎]", game.getDrawPile().toString());
        assertEquals("[7♣︎, 8♣︎, 10♣︎, J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseSuit(SPADES);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎, K♠︎, A♥︎, 9♦︎, 10♦︎]", jacqueline.getCards().toString());
        assertEquals("[9♠︎, J♣︎]", chantal.getCards().toString());
        assertEquals("[7♥︎]", game.getDrawPile().toString());
        assertEquals("[7♣︎, 8♣︎, 10♣︎, J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(KING, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♥︎, K♠︎, A♥︎, 9♦︎, 10♦︎]", jacqueline.getCards().toString());
        assertEquals("[9♠︎, J♣︎]", chantal.getCards().toString());
        assertEquals("[7♥︎]", game.getDrawPile().toString());
        assertEquals("[7♣︎, 8♣︎, 10♣︎, J♠︎, 10♥︎, K♥︎, K♦︎, Q♦︎, J♥︎, A♦︎, 8♦︎, 7♦︎, J♦︎, A♠︎, A♣︎, 9♣︎, K♣︎, Q♣︎, Q♥︎, Q♠︎, 10♠︎, 7♠︎, 8♠︎, 8♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());
    }
}