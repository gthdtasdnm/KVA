package cards.maumau.model;

import cards.Card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Manages the draw pile and discard pile in a MauMau game.
 */
class CardHandler {
    private final MauMau game;
    private final int numCardsPerPlayer;
    private final List<Card> drawPile = new LinkedList<>();
    private final List<Card> discardPile = new LinkedList<>();

    /**
     * Constructs a CardHandler for the specified MauMau game, deck, and number of cards per player.
     *
     * @param game              The MauMau game instance.
     * @param deck              The deck of cards.
     * @param numCardsPerPlayer The number of cards per player.
     */
    CardHandler(MauMau game, List<Card> deck, int numCardsPerPlayer) {
        this.game = game;
        this.numCardsPerPlayer = numCardsPerPlayer;
        drawPile.addAll(deck);
    }

    /**
     * Returns the draw pile containing remaining cards.
     *
     * @return The draw pile.
     */
    List<Card> getDrawPile() {
        return drawPile;
    }

    /**
     * Returns the discard pile containing played cards.
     *
     * @return The discard pile.
     */
    List<Card> getDiscardPile() {
        return discardPile;
    }

    /**
     * Draws a card from the draw pile.
     *
     * @return The drawn card, or null if the draw pile is empty.
     */
    Card drawCard() {
        if (drawPile.isEmpty())
            reuseDiscardedCards();
        if (!drawPile.isEmpty())
            return drawPile.removeFirst();
        game.getActionHandler().cancelGame();
        return null;
    }

    private void reuseDiscardedCards() {
        if (discardPile.isEmpty()) return;
        final Card top = discardPile.removeFirst();
        Collections.shuffle(discardPile);
        drawPile.addAll(discardPile);
        discardPile.clear();
        discardPile.addFirst(top);
    }

    /**
     * Deals cards to all players.
     */
    void dealCards() {
        //TODO implement
    }

    /**
     * Discards a card onto the discard pile.
     *
     * @param c The card to discard.
     */
    void discard(Card c) {
        discardPile.addFirst(c);
    }

    /**
     * Returns the top card of the discard pile.
     *
     * @return The top card of the discard pile.
     */
    Card top() {
        return discardPile.getFirst();
    }
}
