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

public class MauMau8Test {
    private static Card c(Rank r, Suit s) {
        return new Card(r, s);
    }

    @Test
    public void game() {
        final List<Card> deck = List.of(c(NINE, DIAMONDS), c(QUEEN, SPADES), c(EIGHT, SPADES), c(TEN, CLUBS), c(ACE, DIAMONDS), c(JACK, CLUBS), c(NINE, SPADES), c(QUEEN, DIAMONDS), c(EIGHT, HEARTS), c(TEN, SPADES), c(TEN, DIAMONDS), c(SEVEN, HEARTS), c(KING, HEARTS), c(SEVEN, CLUBS), c(NINE, HEARTS), c(SEVEN, SPADES), c(NINE, CLUBS), c(ACE, HEARTS), c(JACK, DIAMONDS), c(KING, DIAMONDS), c(EIGHT, DIAMONDS), c(JACK, SPADES), c(QUEEN, CLUBS), c(SEVEN, DIAMONDS), c(EIGHT, CLUBS), c(ACE, CLUBS), c(QUEEN, HEARTS), c(KING, CLUBS), c(TEN, HEARTS), c(ACE, SPADES), c(KING, SPADES), c(JACK, HEARTS));
        final MauMau game = new MauMau(4, deck);
        final Player jacqueline = game.addPlayer("Jacqueline");
        final Player chantal = game.addPlayer("Chantal");
        final Player cheyenne = game.addPlayer("Cheyenne");

        assertEquals(GAME_INITIALIZED, game.getGameState());
        assertEquals(List.of(jacqueline, chantal, cheyenne), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[]", jacqueline.getCards().toString());
        assertEquals("[]", chantal.getCards().toString());
        assertEquals("[]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, Q♠︎, 8♠︎, 10♣︎, A♦︎, J♣︎, 9♠︎, Q♦︎, 8♥︎, 10♠︎, 10♦︎, 7♥︎, K♥︎, 7♣︎, 9♥︎, 7♠︎, 9♣︎, A♥︎, J♦︎, K♦︎, 8♦︎, J♠︎, Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        game.startGame();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal, cheyenne), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[Q♠︎, A♦︎, Q♦︎, 10♦︎]", chantal.getCards().toString());
        assertEquals("[8♠︎, J♣︎, 8♥︎, 7♥︎]", cheyenne.getCards().toString());
        assertEquals("[7♣︎, 9♥︎, 7♠︎, 9♣︎, A♥︎, J♦︎, K♦︎, 8♦︎, J♠︎, Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♠︎, A♦︎, Q♦︎, 10♦︎]", chantal.getCards().toString());
        assertEquals("[8♠︎, J♣︎, 8♥︎, 7♥︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎, 7♣︎]", jacqueline.getCards().toString());
        assertEquals("[9♥︎, 7♠︎, 9♣︎, A♥︎, J♦︎, K♦︎, 8♦︎, J♠︎, Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♠︎, J♣︎, 8♥︎, 7♥︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎, 7♣︎]", jacqueline.getCards().toString());
        assertEquals("[Q♠︎, A♦︎, Q♦︎, 10♦︎, 9♥︎]", chantal.getCards().toString());
        assertEquals("[7♠︎, 9♣︎, A♥︎, J♦︎, K♦︎, 8♦︎, J♠︎, Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.chooseCard(c(SEVEN, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal, cheyenne), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎, 7♣︎]", jacqueline.getCards().toString());
        assertEquals("[Q♠︎, A♦︎, Q♦︎, 10♦︎, 9♥︎]", chantal.getCards().toString());
        assertEquals("[8♠︎, J♣︎, 8♥︎]", cheyenne.getCards().toString());
        assertEquals("[7♠︎, 9♣︎, A♥︎, J♦︎, K♦︎, 8♦︎, J♠︎, Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        jacqueline.chooseCard(c(SEVEN, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♠︎, A♦︎, Q♦︎, 10♦︎, 9♥︎]", chantal.getCards().toString());
        assertEquals("[8♠︎, J♣︎, 8♥︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[7♠︎, 9♣︎, A♥︎, J♦︎, K♦︎, 8♦︎, J♠︎, Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(2, game.get7Counter());

        chantal.no7();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♠︎, A♦︎, Q♦︎, 10♦︎, 9♥︎, 7♠︎, 9♣︎, A♥︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[8♠︎, J♣︎, 8♥︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[K♦︎, 8♦︎, J♠︎, Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(SEVEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♠︎, J♣︎, 8♥︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[Q♠︎, A♦︎, Q♦︎, 10♦︎, 9♥︎, 9♣︎, A♥︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[K♦︎, 8♦︎, J♠︎, Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        cheyenne.no7();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♠︎, J♣︎, 8♥︎, K♦︎, 8♦︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[Q♠︎, A♦︎, Q♦︎, 10♦︎, 9♥︎, 9♣︎, A♥︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[J♠︎, Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.chooseCard(c(EIGHT, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♠︎, A♦︎, Q♦︎, 10♦︎, 9♥︎, 9♣︎, A♥︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[J♣︎, 8♥︎, K♦︎, 8♦︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[J♠︎, Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(QUEEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[J♣︎, 8♥︎, K♦︎, 8♦︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎, Q♦︎, 10♦︎, 9♥︎, 9♣︎, A♥︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[J♠︎, Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.chooseCard(c(JACK, CLUBS));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♥︎, K♦︎, 8♦︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎, Q♦︎, 10♦︎, 9♥︎, 9♣︎, A♥︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[J♠︎, Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.chooseSuit(HEARTS);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal, cheyenne), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎, Q♦︎, 10♦︎, 9♥︎, 9♣︎, A♥︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[8♥︎, K♦︎, 8♦︎]", cheyenne.getCards().toString());
        assertEquals("[J♠︎, Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertEquals(HEARTS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♦︎, Q♦︎, 10♦︎, 9♥︎, 9♣︎, A♥︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[8♥︎, K♦︎, 8♦︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎, J♠︎]", jacqueline.getCards().toString());
        assertEquals("[Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertEquals(HEARTS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(NINE, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♥︎, K♦︎, 8♦︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎, J♠︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎, Q♦︎, 10♦︎, 9♣︎, A♥︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.chooseCard(c(EIGHT, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♦︎, Q♦︎, 10♦︎, 9♣︎, A♥︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[K♦︎, 8♦︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎, J♠︎]", jacqueline.getCards().toString());
        assertEquals("[Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(ACE, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♦︎, 8♦︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎, J♠︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎, Q♦︎, 10♦︎, 9♣︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[Q♣︎, 7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal, cheyenne), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎, J♠︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎, Q♦︎, 10♦︎, 9♣︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[K♦︎, 8♦︎, Q♣︎]", cheyenne.getCards().toString());
        assertEquals("[7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(JACK, SPADES));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(jacqueline, chantal, cheyenne), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎, Q♦︎, 10♦︎, 9♣︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[K♦︎, 8♦︎, Q♣︎]", cheyenne.getCards().toString());
        assertEquals("[7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseSuit(CLUBS);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♦︎, Q♦︎, 10♦︎, 9♣︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[K♦︎, 8♦︎, Q♣︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertEquals(CLUBS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(NINE, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♦︎, 8♦︎, Q♣︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎, Q♦︎, 10♦︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.chooseCard(c(QUEEN, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal, cheyenne), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♦︎, 10♣︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎, Q♦︎, 10♦︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[K♦︎, 8♦︎]", cheyenne.getCards().toString());
        assertEquals("[7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[Q♣︎, 9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(TEN, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♦︎, Q♦︎, 10♦︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[K♦︎, 8♦︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[10♣︎, Q♣︎, 9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(TEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♦︎, 8♦︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎, Q♦︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[10♦︎, 10♣︎, Q♣︎, 9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.chooseCard(c(EIGHT, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♦︎, Q♦︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[K♦︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[8♦︎, 10♦︎, 10♣︎, Q♣︎, 9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♦︎, Q♦︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[K♦︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[8♦︎, 10♦︎, 10♣︎, Q♣︎, 9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(QUEEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[K♦︎]", cheyenne.getCards().toString());
        assertEquals("[9♦︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[Q♦︎, 8♦︎, 10♦︎, 10♣︎, Q♣︎, 9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.chooseCard(c(KING, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal, cheyenne), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♦︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[]", cheyenne.getCards().toString());
        assertEquals("[7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[K♦︎, Q♦︎, 8♦︎, 10♦︎, 10♣︎, Q♣︎, 9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.maumau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(cheyenne), game.getRanking());
        assertEquals("[9♦︎, 9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[A♦︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[K♦︎, Q♦︎, 8♦︎, 10♦︎, 10♣︎, Q♣︎, 9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(NINE, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(cheyenne), game.getRanking());
        assertEquals("[A♦︎, J♦︎]", chantal.getCards().toString());
        assertEquals("[9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[9♦︎, K♦︎, Q♦︎, 8♦︎, 10♦︎, 10♣︎, Q♣︎, 9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(ACE, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(cheyenne), game.getRanking());
        assertEquals("[9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[J♦︎]", chantal.getCards().toString());
        assertEquals("[7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[A♦︎, 9♦︎, K♦︎, Q♦︎, 8♦︎, 10♦︎, 10♣︎, Q♣︎, 9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(cheyenne), game.getRanking());
        assertEquals("[9♠︎, 10♠︎]", jacqueline.getCards().toString());
        assertEquals("[J♦︎]", chantal.getCards().toString());
        assertEquals("[7♦︎, 8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[A♦︎, 9♦︎, K♦︎, Q♦︎, 8♦︎, 10♦︎, 10♣︎, Q♣︎, 9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(cheyenne), game.getRanking());
        assertEquals("[J♦︎]", chantal.getCards().toString());
        assertEquals("[9♠︎, 10♠︎, 7♦︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[A♦︎, 9♦︎, K♦︎, Q♦︎, 8♦︎, 10♦︎, 10♣︎, Q♣︎, 9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(JACK, DIAMONDS));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(chantal, jacqueline), game.getPlayers());
        assertEquals(List.of(cheyenne), game.getRanking());
        assertEquals("[]", chantal.getCards().toString());
        assertEquals("[9♠︎, 10♠︎, 7♦︎]", jacqueline.getCards().toString());
        assertEquals("[8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, A♦︎, 9♦︎, K♦︎, Q♦︎, 8♦︎, 10♦︎, 10♣︎, Q♣︎, 9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseSuit(DIAMONDS);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(cheyenne), game.getRanking());
        assertEquals("[9♠︎, 10♠︎, 7♦︎]", jacqueline.getCards().toString());
        assertEquals("[]", chantal.getCards().toString());
        assertEquals("[8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, A♦︎, 9♦︎, K♦︎, Q♦︎, 8♦︎, 10♦︎, 10♣︎, Q♣︎, 9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertEquals(DIAMONDS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.maumau();
        assertEquals(GAME_OVER, game.getGameState());
        assertEquals(List.of(), game.getPlayers());
        assertEquals(List.of(cheyenne, chantal, jacqueline), game.getRanking());
        assertEquals("[8♣︎, A♣︎, Q♥︎, K♣︎, 10♥︎, A♠︎, K♠︎, J♥︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, A♦︎, 9♦︎, K♦︎, Q♦︎, 8♦︎, 10♦︎, 10♣︎, Q♣︎, 9♣︎, J♠︎, A♥︎, 8♥︎, 9♥︎, J♣︎, Q♠︎, 8♠︎, 7♠︎, 7♣︎, 7♥︎, K♥︎]", game.getDiscardPile().toString());
        assertEquals(DIAMONDS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());
    }
}