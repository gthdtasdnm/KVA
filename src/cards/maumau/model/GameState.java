package cards.maumau.model;

/**
 * Represents the state of the Mau-Mau game.
 */
public enum GameState {
    /**
     * The game has been initialized, but has not yet started.
     */
    GAME_INITIALIZED,
    /**
     * The game is over. The final ranking of players can be
     * obtained using {@link MauMau#getRanking()}.
     */
    GAME_OVER,
    /**
     * The game has been canceled due to insufficient cards.
     */
    GAME_CANCELED,
    /**
     * The game is currently in progress with players taking turns.
     */
    PLAY,
    /**
     * The game is in progress and the current player has played
     * a Jack, and is required to choose a suit.
     */
    CHOOSE_SUIT
}
