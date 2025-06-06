package cards.maumau.model;

import cards.Card;
import cards.Suit;

/**
 * Manages the actions and state transitions within a MauMau game.
 */
class ActionHandler {
    private final MauMau game;
    private Suit chosenSuit;
    private int ctr7 = 0;

    /**
     * Constructs an ActionHandler for the specified MauMau game.
     *
     * @param game The MauMau game instance.
     */
    ActionHandler(MauMau game) {
        this.game = game;
    }

    /**
     * Adds the specified player to the game.
     *
     * @param player The player to be added to the game.
     */
    void addPlayer(Player player) {
        //TODO implement
        game.getPlayerHandler().addPlayer(player);
    }

    /**
     * Starts the game.
     */
    void startGame() {
        //TODO implement
    }

    /**
     * Transitions the game state to GAME_OVER.
     */
    void finishGame() {
        //TODO implement
    }

    /**
     * Transitions the game state to GAME_CANCELED.
     */
    void cancelGame() {
        //TODO implement
    }

    /**
     * Handles the player's choice of a card in the current state.
     *
     * @param c The card chosen by the player.
     */
    void chooseCard(Card c) {
        //TODO implement
    }

    /**
     * Handles the player's choice of a suit in the current state.
     *
     * @param suit The suit chosen by the player.
     */
    void chooseSuit(Suit suit) {
        //TODO implement
    }

    /**
     * Lets the player skip a round.
     **/
    void skip() {
        //TODO implement
    }

    /**
     * Handles the player saying "no 7" in the current state.
     */
    void no7() {
        //TODO implement
    }

    /**
     * Returns the MauMau game instance associated with this action handler.
     *
     * @return The MauMau game instance.
     */
    MauMau getGame() {
        return game;
    }

    /**
     * Returns the suit chosen by a player after playing a Jack card.
     *
     * @return The chosen suit.
     */
    Suit getChosenSuit() {
        return chosenSuit;
    }

    /**
     * Sets the suit chosen by a player after playing a Jack card.
     *
     * @param chosenSuit The suit chosen by the player.
     */
    void setChosenSuit(Suit chosenSuit) {
        this.chosenSuit = chosenSuit;
    }

    /**
     * Returns the number of number 7 cards played recently.
     *
     * @return The number of number 7 cards played recently.
     */
    int get7Counter() {
        return ctr7;
    }

    /**
     * Resets the counter of number 7 cards played to zero.
     */
    void reset7Counter() {
        ctr7 = 0;
    }

    /**
     * Increments the counter of number 7 cards played by one.
     */
    void increment7Counter() {
        ctr7++;
    }

    /**
     * Returns the current state of the game.
     */
    GameState getGameState() {
        //TODO implement
        return null;
    }

    /**
     * Checks if a card can be played by the current player in the current state.
     *
     * @param c The card being played.
     * @return True if the card can be played, false otherwise.
     */
    boolean canPlay(Card c) {
        //TODO implement
        return false;
    }
}
