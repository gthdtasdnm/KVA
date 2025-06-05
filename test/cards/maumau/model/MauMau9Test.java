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

public class MauMau9Test {
    private static Card c(Rank r, Suit s) {
        return new Card(r, s);
    }

    @Test
    public void game() {
        final List<Card> deck = List.of(c(ACE, HEARTS), c(SEVEN, CLUBS), c(EIGHT, DIAMONDS), c(TEN, CLUBS), c(QUEEN, HEARTS), c(TEN, SPADES), c(SEVEN, DIAMONDS), c(SEVEN, HEARTS), c(KING, DIAMONDS), c(JACK, CLUBS), c(NINE, DIAMONDS), c(TEN, HEARTS), c(SEVEN, SPADES), c(KING, CLUBS), c(KING, HEARTS), c(QUEEN, DIAMONDS), c(NINE, CLUBS), c(ACE, SPADES), c(EIGHT, HEARTS), c(TEN, DIAMONDS), c(JACK, DIAMONDS), c(ACE, CLUBS), c(QUEEN, CLUBS), c(JACK, HEARTS), c(NINE, SPADES), c(EIGHT, SPADES), c(QUEEN, SPADES), c(JACK, SPADES), c(ACE, DIAMONDS), c(NINE, HEARTS), c(KING, SPADES), c(EIGHT, CLUBS));
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
        assertEquals("[A♥︎, 7♣︎, 8♦︎, 10♣︎, Q♥︎, 10♠︎, 7♦︎, 7♥︎, K♦︎, J♣︎, 9♦︎, 10♥︎, 7♠︎, K♣︎, K♥︎, Q♦︎, 9♣︎, A♠︎, 8♥︎, 10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        game.startGame();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal, cheyenne), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♥︎, 10♣︎, 7♦︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[7♣︎, Q♥︎, 7♥︎, 9♦︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, 10♠︎, K♦︎, 10♥︎]", cheyenne.getCards().toString());
        assertEquals("[K♣︎, K♥︎, Q♦︎, 9♣︎, A♠︎, 8♥︎, 10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(SEVEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♣︎, Q♥︎, 7♥︎, 9♦︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, 10♠︎, K♦︎, 10♥︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎, 10♣︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[K♣︎, K♥︎, Q♦︎, 9♣︎, A♠︎, 8♥︎, 10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        chantal.chooseCard(c(SEVEN, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, 10♠︎, K♦︎, 10♥︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎, 10♣︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[7♣︎, Q♥︎, 9♦︎]", chantal.getCards().toString());
        assertEquals("[K♣︎, K♥︎, Q♦︎, 9♣︎, A♠︎, 8♥︎, 10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(2, game.get7Counter());

        cheyenne.no7();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, 10♠︎, K♦︎, 10♥︎, K♣︎, K♥︎, Q♦︎, 9♣︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎, 10♣︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[7♣︎, Q♥︎, 9♦︎]", chantal.getCards().toString());
        assertEquals("[A♠︎, 8♥︎, 10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.chooseCard(c(TEN, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal, cheyenne), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♥︎, 10♣︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[7♣︎, Q♥︎, 9♦︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, 10♠︎, K♦︎, K♣︎, K♥︎, Q♦︎, 9♣︎]", cheyenne.getCards().toString());
        assertEquals("[A♠︎, 8♥︎, 10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(TEN, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[7♣︎, Q♥︎, 9♦︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, 10♠︎, K♦︎, K♣︎, K♥︎, Q♦︎, 9♣︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[A♠︎, 8♥︎, 10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(SEVEN, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, 10♠︎, K♦︎, K♣︎, K♥︎, Q♦︎, 9♣︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[Q♥︎, 9♦︎]", chantal.getCards().toString());
        assertEquals("[A♠︎, 8♥︎, 10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(1, game.get7Counter());

        cheyenne.no7();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, 10♠︎, K♦︎, K♣︎, K♥︎, Q♦︎, 9♣︎, A♠︎, 8♥︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[Q♥︎, 9♦︎]", chantal.getCards().toString());
        assertEquals("[10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.chooseCard(c(KING, CLUBS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal, cheyenne), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♥︎, J♣︎]", jacqueline.getCards().toString());
        assertEquals("[Q♥︎, 9♦︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, 10♠︎, K♦︎, K♥︎, Q♦︎, 9♣︎, A♠︎, 8♥︎]", cheyenne.getCards().toString());
        assertEquals("[10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(JACK, CLUBS));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(jacqueline, chantal, cheyenne), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♥︎]", jacqueline.getCards().toString());
        assertEquals("[Q♥︎, 9♦︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, 10♠︎, K♦︎, K♥︎, Q♦︎, 9♣︎, A♠︎, 8♥︎]", cheyenne.getCards().toString());
        assertEquals("[10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseSuit(HEARTS);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♥︎, 9♦︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, 10♠︎, K♦︎, K♥︎, Q♦︎, 9♣︎, A♠︎, 8♥︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎]", jacqueline.getCards().toString());
        assertEquals("[10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertEquals(HEARTS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[Q♥︎, 9♦︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, 10♠︎, K♦︎, K♥︎, Q♦︎, 9♣︎, A♠︎, 8♥︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎]", jacqueline.getCards().toString());
        assertEquals("[10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertEquals(HEARTS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(QUEEN, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, 10♠︎, K♦︎, K♥︎, Q♦︎, 9♣︎, A♠︎, 8♥︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎]", jacqueline.getCards().toString());
        assertEquals("[9♦︎]", chantal.getCards().toString());
        assertEquals("[10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, 10♠︎, K♦︎, K♥︎, Q♦︎, 9♣︎, A♠︎, 8♥︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎]", jacqueline.getCards().toString());
        assertEquals("[9♦︎]", chantal.getCards().toString());
        assertEquals("[10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.chooseCard(c(EIGHT, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♦︎]", chantal.getCards().toString());
        assertEquals("[8♦︎, 10♠︎, K♦︎, K♥︎, Q♦︎, 9♣︎, A♠︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎]", jacqueline.getCards().toString());
        assertEquals("[10♦︎, J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[8♦︎, 10♠︎, K♦︎, K♥︎, Q♦︎, 9♣︎, A♠︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎]", jacqueline.getCards().toString());
        assertEquals("[9♦︎, 10♦︎]", chantal.getCards().toString());
        assertEquals("[J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.chooseCard(c(EIGHT, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[9♦︎, 10♦︎]", chantal.getCards().toString());
        assertEquals("[10♠︎, K♦︎, K♥︎, Q♦︎, 9♣︎, A♠︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎]", jacqueline.getCards().toString());
        assertEquals("[J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[8♦︎, 8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(NINE, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♠︎, K♦︎, K♥︎, Q♦︎, 9♣︎, A♠︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎]", jacqueline.getCards().toString());
        assertEquals("[10♦︎]", chantal.getCards().toString());
        assertEquals("[J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[9♦︎, 8♦︎, 8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♠︎, K♦︎, K♥︎, Q♦︎, 9♣︎, A♠︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎]", jacqueline.getCards().toString());
        assertEquals("[10♦︎]", chantal.getCards().toString());
        assertEquals("[J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[9♦︎, 8♦︎, 8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.chooseCard(c(KING, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, chantal, cheyenne), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[A♥︎]", jacqueline.getCards().toString());
        assertEquals("[10♦︎]", chantal.getCards().toString());
        assertEquals("[10♠︎, K♥︎, Q♦︎, 9♣︎, A♠︎]", cheyenne.getCards().toString());
        assertEquals("[J♦︎, A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[K♦︎, 9♦︎, 8♦︎, 8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.skip();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(chantal, cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♦︎]", chantal.getCards().toString());
        assertEquals("[10♠︎, K♥︎, Q♦︎, 9♣︎, A♠︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎, J♦︎]", jacqueline.getCards().toString());
        assertEquals("[A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[K♦︎, 9♦︎, 8♦︎, 8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.chooseCard(c(TEN, DIAMONDS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline, chantal), game.getPlayers());
        assertEquals(List.of(), game.getRanking());
        assertEquals("[10♠︎, K♥︎, Q♦︎, 9♣︎, A♠︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎, J♦︎]", jacqueline.getCards().toString());
        assertEquals("[]", chantal.getCards().toString());
        assertEquals("[A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[10♦︎, K♦︎, 9♦︎, 8♦︎, 8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        chantal.maumau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(chantal), game.getRanking());
        assertEquals("[10♠︎, K♥︎, Q♦︎, 9♣︎, A♠︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎, J♦︎]", jacqueline.getCards().toString());
        assertEquals("[A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[10♦︎, K♦︎, 9♦︎, 8♦︎, 8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.chooseCard(c(TEN, SPADES));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, cheyenne), game.getPlayers());
        assertEquals(List.of(chantal), game.getRanking());
        assertEquals("[A♥︎, J♦︎]", jacqueline.getCards().toString());
        assertEquals("[K♥︎, Q♦︎, 9♣︎, A♠︎]", cheyenne.getCards().toString());
        assertEquals("[A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[10♠︎, 10♦︎, K♦︎, 9♦︎, 8♦︎, 8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(JACK, DIAMONDS));
        assertEquals(CHOOSE_SUIT, game.getGameState());
        assertEquals(List.of(jacqueline, cheyenne), game.getPlayers());
        assertEquals(List.of(chantal), game.getRanking());
        assertEquals("[A♥︎]", jacqueline.getCards().toString());
        assertEquals("[K♥︎, Q♦︎, 9♣︎, A♠︎]", cheyenne.getCards().toString());
        assertEquals("[A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, 10♠︎, 10♦︎, K♦︎, 9♦︎, 8♦︎, 8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseSuit(HEARTS);
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(chantal), game.getRanking());
        assertEquals("[K♥︎, Q♦︎, 9♣︎, A♠︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎]", jacqueline.getCards().toString());
        assertEquals("[A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, 10♠︎, 10♦︎, K♦︎, 9♦︎, 8♦︎, 8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertEquals(HEARTS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.mau();
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(chantal), game.getRanking());
        assertEquals("[K♥︎, Q♦︎, 9♣︎, A♠︎]", cheyenne.getCards().toString());
        assertEquals("[A♥︎]", jacqueline.getCards().toString());
        assertEquals("[A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[J♦︎, 10♠︎, 10♦︎, K♦︎, 9♦︎, 8♦︎, 8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertEquals(HEARTS, game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        cheyenne.chooseCard(c(KING, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(jacqueline, cheyenne), game.getPlayers());
        assertEquals(List.of(chantal), game.getRanking());
        assertEquals("[A♥︎]", jacqueline.getCards().toString());
        assertEquals("[Q♦︎, 9♣︎, A♠︎]", cheyenne.getCards().toString());
        assertEquals("[A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[K♥︎, J♦︎, 10♠︎, 10♦︎, K♦︎, 9♦︎, 8♦︎, 8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.chooseCard(c(ACE, HEARTS));
        assertEquals(PLAY, game.getGameState());
        assertEquals(List.of(cheyenne, jacqueline), game.getPlayers());
        assertEquals(List.of(chantal), game.getRanking());
        assertEquals("[Q♦︎, 9♣︎, A♠︎]", cheyenne.getCards().toString());
        assertEquals("[]", jacqueline.getCards().toString());
        assertEquals("[A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[A♥︎, K♥︎, J♦︎, 10♠︎, 10♦︎, K♦︎, 9♦︎, 8♦︎, 8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());

        jacqueline.maumau();
        assertEquals(GAME_OVER, game.getGameState());
        assertEquals(List.of(), game.getPlayers());
        assertEquals(List.of(chantal, jacqueline, cheyenne), game.getRanking());
        assertEquals("[A♣︎, Q♣︎, J♥︎, 9♠︎, 8♠︎, Q♠︎, J♠︎, A♦︎, 9♥︎, K♠︎, 8♣︎]", game.getDrawPile().toString());
        assertEquals("[A♥︎, K♥︎, J♦︎, 10♠︎, 10♦︎, K♦︎, 9♦︎, 8♦︎, 8♥︎, Q♥︎, J♣︎, K♣︎, 7♣︎, 10♣︎, 10♥︎, 7♥︎, 7♦︎, 7♠︎]", game.getDiscardPile().toString());
        assertNull(game.getChosenSuit());
        assertEquals(0, game.get7Counter());
    }
}
