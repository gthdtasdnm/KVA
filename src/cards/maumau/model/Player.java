package cards.maumau.model;

import cards.Card;
import cards.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a player in a Mau-Mau card game.
 */
public class Player {
    private final MauMau game;
    private final String name;
    private final List<Card> cards = new ArrayList<>();

    /**
     * Constructor for Player class.
     *
     * @param name The name of the player.
     * @param game The MauMau game instance the player is part of.
     */
    Player(String name, MauMau game) {
        this.name = name;
        this.game = game;
    }

    /**
     * Returns a string representation of the player, including their name and their cards.
     *
     * @return String representation of the player.
     */
    @Override
    public String toString() {
        return name + cards.stream()
                           .map(Object::toString)
                           .collect(Collectors.joining(" ", " (cards ", ")"));
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the MauMau game instance the player is part of.
     *
     * @return The MauMau game instance.
     */
    public MauMau getGame() {
        return game;
    }

    /**
     * Gets the list of cards held by the player.
     *
     * @return List of cards held by the player.
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Draws a specified number of cards from the draw pile.
     *
     * @param n The number of cards to draw.
     */
    void drawCards(int n) {
        for (int i = 0; i < n; i++) {
            final Card c = game.getCardHandler().drawCard();
            if (c == null) return;
            cards.add(c);
        }
    }

    /**
     * Plays a card from the player's hand onto the discard pile.
     *
     * @param c The card to be played.
     */
    void playCard(Card c) {
        if (!cards.remove(c))
            throw new IllegalArgumentException(this + " doesn't have " + c);
        game.getCardHandler().discard(c);
    }

    /**
     * Checks if the player can legally play a specific card.
     *
     * @param c The card to check.
     * @return True if the player can legally play the card, false otherwise.
     */
    public boolean canPlay(Card c) {
        return game.getCurrentPlayer() == this && game.getActionHandler().canPlay(c);
    }

    /**
     * Selects a card to play from the player's hand.
     *
     * @param c The card to play.
     */
    public void chooseCard(Card c) {
        if (isCurrentPlayer()) {
            game.getActionHandler().chooseCard(c);
            game.notifyObservers();
        }
    }

    /**
     * Chooses a suit after playing a Jack card.
     *
     * @param s The suit chosen by the player.
     */
    public void chooseSuit(Suit s) {
        if (isCurrentPlayer()) {
            game.getActionHandler().chooseSuit(s);
            game.notifyObservers();
        }
    }

    /**
     * Skips the player's turn.
     */
    public void skip() {
        if (isCurrentPlayer()) {
            game.getActionHandler().skip();
            game.notifyObservers();
        }
    }

    /**
     * Executes special action when the player cannot play a seven card.
     */
    public void no7() {
        if (isCurrentPlayer()) {
            game.getActionHandler().no7();
            game.notifyObservers();
        }
    }

    /**
     * Executes special action when the player calls "Mau".
     */
    public void mau() {
        game.getPlayerHandler().mau(this);
        game.notifyObservers();
    }

    /**
     * Executes special action when the player calls "Mau-Mau".
     */
    public void maumau() {
        game.getPlayerHandler().maumau(this);
        game.notifyObservers();
    }

    /**
     * Checks if the current player is this player instance.
     * If not, sends a message indicating it's not their turn.
     *
     * @return True if it's this player's turn, false otherwise.
     */
    private boolean isCurrentPlayer() {
        if (this == game.getCurrentPlayer())
            return true;
        game.sendMessage(String.format("It's %s's turn, not %s's.", game.getCurrentPlayer().getName(), name));
        return false;
    }
}
