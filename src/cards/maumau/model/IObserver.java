package cards.maumau.model;

/**
 * Interface for observing changes in the Mau-Mau game.
 */
public interface IObserver {
    /**
     * Method called to notify the observer of a general update in the game.
     */
    void update();

    /**
     * Method called to send a message to the observer.
     *
     * @param msg The message to be sent.
     */
    void message(String msg);
}
