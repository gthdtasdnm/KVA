package cards.maumau.model;

import cards.Card;
import cards.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents a Mau-Mau card game.
 */
public class MauMau {
    private final ActionHandler actionHandler = new ActionHandler(this);
    private final PlayerHandler playerHandler = new PlayerHandler(this);
    private final CardHandler cardHandler;
    private final List<IObserver> observers = new ArrayList<>();

    /**
     * Constructs a MauMau game with the specified parameters.
     *
     * @param numCardsPerPlayer The number of cards each player should have initially.
     * @param deck              The deck of cards to be used in the game.
     */
    public MauMau(int numCardsPerPlayer, List<Card> deck) {
        cardHandler = new CardHandler(this, deck, numCardsPerPlayer);
    }

    /**
     * Adds an observer to the game.
     *
     * @param observer The observer to add.
     */
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all observers of the game.
     */
    void notifyObservers() {
        for (IObserver o : observers)
            o.update();
    }

    /**
     * Sends a message to all observers.
     *
     * @param msg The message to send.
     */
    void sendMessage(String msg) {
        for (IObserver o : observers)
            o.message(msg);
    }

    /**
     * Gets the player handler for the game.
     *
     * @return The player handler.
     */
    PlayerHandler getPlayerHandler() {
        return playerHandler;
    }

    /**
     * Gets the card handler for the game.
     *
     * @return The card handler.
     */
    CardHandler getCardHandler() {
        return cardHandler;
    }

    /**
     * Gets the action handler for the game.
     *
     * @return The action handler.
     */
    ActionHandler getActionHandler() {
        return actionHandler;
    }

    /**
     * Returns the suit chosen by a player after playing a Jack.
     *
     * @return The chosen suit.
     */
    public Suit getChosenSuit() {
        return actionHandler.getChosenSuit();
    }

    /**
     * Returns the number of number 7 cards played recently.
     *
     * @return The number of number 7 cards played recently.
     */
    public int get7Counter() {
        return actionHandler.get7Counter();
    }

    /**
     * Starts the Mau-Mau game.
     */
    public void startGame() {
        actionHandler.startGame();
    }

    /**
     * Returns a string representation of the game.
     *
     * @return String representation of the game.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Game state: ").append(getGameState()).append("\n");
        sb.append("State: ").append(actionHandler).append("\n");
        sb.append("Players:\n");
        sb.append(mkString(getPlayers(), "  ", "\n  "));
        sb.append(mkString(getDrawPile(), "draw pile:    ", " "));
        sb.append(mkString(getDiscardPile(), "discard pile: ", " "));
        sb.append(mkString(getRanking(), Player::getName, "ranking:      ", ", ", "\n"));
        sb.append("chosen suit: ").append(getChosenSuit()).append("\n");
        sb.append("7 count: ").append(get7Counter()).append("\n");
        return sb.toString();
    }

    /**
     * Joins the elements of the list into a single string with the specified prefix and delimiter.
     *
     * @param list      The list to join.
     * @param func      Function to convert list elements to strings.
     * @param prefix    Prefix for the resulting string.
     * @param delimiter Delimiter to separate list elements.
     * @param suffix    Suffix for the resulting string.
     * @param <A>       Type of elements in the list.
     * @return Joined string representation of the list.
     */
    static <A> String mkString(List<A> list, Function<? super A, String> func, String prefix, String delimiter, String suffix) {
        return list.stream()
                   .map(func)
                   .collect(Collectors.joining(delimiter, prefix, suffix));
    }

    /**
     * Joins the elements of the list into a single string with the specified prefix and delimiter.
     *
     * @param list      The list to join.
     * @param prefix    Prefix for the resulting string.
     * @param delimiter Delimiter to separate list elements.
     * @param <A>       Type of elements in the list.
     * @return Joined string representation of the list.
     */
    static <A> String mkString(List<A> list, String prefix, String delimiter) {
        return mkString(list, Objects::toString, prefix, delimiter, "\n");
    }

    /**
     * Gets the list of players still participating in the game.
     * The first player in the list is the current player.
     *
     * @return The list of players.
     */
    public List<Player> getPlayers() {
        return playerHandler.getPlayers();
    }

    /**
     * Adds a player to the game.
     *
     * @param name The name of the player.
     * @return The player added to the game.
     */
    public Player addPlayer(String name) {
        final Player player = new Player(name, this);
        actionHandler.addPlayer(player);
        return player;
    }

    /**
     * Returns the ranking of players that have already discarded all of their cards
     * and, when the game is over, also the loser.
     *
     * @return The ranking of players.
     */
    public List<Player> getRanking() {
        return playerHandler.getRanking();
    }

    /**
     * Returns the current player whose turn it is.
     *
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return playerHandler.getCurrentPlayer();
    }

    /**
     * Returns the draw pile containing remaining cards.
     *
     * @return The draw pile.
     */
    public List<Card> getDrawPile() {
        return cardHandler.getDrawPile();
    }

    /**
     * Returns the discard pile containing played cards.
     *
     * @return The discard pile.
     */
    public List<Card> getDiscardPile() {
        return cardHandler.getDiscardPile();
    }

    /**
     * Returns the current state of the game.
     *
     * @return The state of the game.
     */
    public GameState getGameState() {
        return actionHandler.getGameState();
    }
}
