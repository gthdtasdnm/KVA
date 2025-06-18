package cards.maumau.model;

import cards.Card;
import cards.Suit;
import cards.Rank;

/**
 * Manages the actions and state transitions within a MauMau game.
 */
class ActionHandler {
    private final MauMau game;
    private Suit chosenSuit;
    private int ctr7 = 0;
    private GameState state = GameState.GAME_INITIALIZED;

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
        if (state != GameState.GAME_INITIALIZED)
            throw new IllegalStateException("cannot add players after game start");
        game.getPlayerHandler().addPlayer(player);
    }

    /**
     * Starts the game.
     */
    void startGame() {
        if (state != GameState.GAME_INITIALIZED)
            return;
        game.getCardHandler().dealCards();
        state = GameState.PLAY;
    }

    /**
     * Transitions the game state to GAME_OVER.
     */
    void finishGame() {
        state = GameState.GAME_OVER;
    }

    /**
     * Transitions the game state to GAME_CANCELED.
     */
    void cancelGame() {
        state = GameState.GAME_CANCELED;
    }

    /**
     * Handles the player's choice of a card in the current state.
     *
     * @param c The card chosen by the player.
     */
    void chooseCard(Card c) {
        if (state != GameState.PLAY)
            return;
        final Player current = game.getCurrentPlayer();
        if (current == null || !current.getCards().contains(c))
            return;
        if (!canPlay(c))
            return;

        current.playCard(c);

        // remember to penalize players who forget to announce "Mau" when they
        // are left with only one card in hand
        if (current.getCards().size() == 1 && !game.getPlayerHandler().didCallMau(current)) {
            game.getPlayerHandler().flagMissedMau(current);
        }



        if (chosenSuit != null && c.suit() == chosenSuit)
            chosenSuit = null;

        switch (c.rank()) {
            case SEVEN -> {
                increment7Counter();
                game.getPlayerHandler().nextTurn(1);
            }
            case EIGHT -> {
                reset7Counter();
                game.getPlayerHandler().nextTurn(2);
            }
            case JACK -> {
                chosenSuit = null;
                state = GameState.CHOOSE_SUIT;
            }
            default -> {
                reset7Counter();
                game.getPlayerHandler().nextTurn(1);
            }
        }
    }

    /**
     * Handles the player's choice of a suit in the current state.
     *
     * @param suit The suit chosen by the player.
     */
    void chooseSuit(Suit suit) {
        if (state != GameState.CHOOSE_SUIT)
            return;
        chosenSuit = suit;
        state = GameState.PLAY;
        game.getPlayerHandler().nextTurn(1);
    }

    /**
     * Lets the player skip a round.
     **/
    void skip() {
        if (state != GameState.PLAY)
            return;
        if (ctr7 > 0)
            return;
        final Player current = game.getCurrentPlayer();
        if (current == null)
            return;
        current.drawCards(1);
        game.getPlayerHandler().nextTurn(1);
    }

    /**
     * Handles the player saying "no 7" in the current state.
     */
    void no7() {
        if (state != GameState.PLAY || ctr7 == 0)
            return;
        final Player current = game.getCurrentPlayer();
        if (current == null)
            return;
        current.drawCards(ctr7 * 2);
        reset7Counter();
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
        return state;
    }

    /**
     * Checks if a card can be played by the current player in the current state.
     *
     * @param c The card being played.
     * @return True if the card can be played, false otherwise.
     */
    boolean canPlay(Card c) {
        if (state != GameState.PLAY || c == null)
            return false;

        if (ctr7 > 0)
            return c.rank() == Rank.SEVEN;

        if (chosenSuit != null)
            return c.suit() == chosenSuit;

        if (state == GameState.PLAY) {
            if (c.rank() == Rank.JACK)
                return true;
            final Card top = game.getCardHandler().top();
            return c.suit() == top.suit() || c.rank() == top.rank();
        }
        return false;
    }
}
