package cards.maumau.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles players in a MauMau game.
 */
class PlayerHandler {
    private final MauMau game;
    private final List<Player> players = new LinkedList<>();
    private final List<Player> ranking = new ArrayList<>();
    private Player remember;
    private Player penalized;
    private boolean penaltyArmed;
    private PlayerHandlerState state = new DefaultState();

    /** State pattern interface for the handler. */
    private interface PlayerHandlerState {
        void nextTurn(int n);

        void mau(Player p);

        void maumau(Player p);
    }

    /** Normal state when no player has called "Mau". */
    private class DefaultState implements PlayerHandlerState {
        @Override
        public void nextTurn(int n) {
            localNextTurn(n);
            remember = null;
        }

        @Override
        public void mau(Player p) {
            if (p.getCards().size() == 1) {
                remember = p;
                state = new MauState();
            }
        }

        @Override
        public void maumau(Player p) {
            if (p.getCards().isEmpty()) {
                finishPlayer(p);
                if (players.size() <= 1) {
                    ranking.addAll(players);
                    players.clear();
                    game.getActionHandler().finishGame();
                }
            }
        }
    }

    /** State after a player has called "Mau". */
    private class MauState implements PlayerHandlerState {
        @Override
        public void nextTurn(int n) {
            localNextTurn(n);
        }

        @Override
        public void mau(Player p) {
            if (p.getCards().size() == 1) {
                remember = p;
            }
        }

        @Override
        public void maumau(Player p) {
            if (p == remember && p.getCards().isEmpty()) {
                finishPlayer(p);
                remember = null;
                if (players.size() <= 1) {
                    ranking.addAll(players);
                    players.clear();
                    game.getActionHandler().finishGame();
                } else {
                    state = new DefaultState();
                }
            }
        }
    }

    /**
     * Constructs a PlayerHandler for the specified MauMau game.
     *
     * @param game The MauMau game instance.
     */
    PlayerHandler(MauMau game) {
        this.game = game;
    }

    /**
     * Initiates the next turn in the game.
     *
     * @param n The number of turns to proceed.
     */
    void nextTurn(int n) {
        state.nextTurn(n);

        if (penalized != null) {
            if (penalized == getCurrentPlayer()) {
                if (penaltyArmed) {
                    penalized.drawCards(1);
                    penalized = null;
                } else {
                    penaltyArmed = true;
                }
            } else {
                // as soon as another player gets the turn, the penalty is armed
                penaltyArmed = true;
            }
        }
    }

    /**
     * Handles a player calling "Mau".
     *
     * @param p The player calling "Mau".
     */
    void mau(Player p) {
        if (p == getCurrentPlayer()) {
            state.mau(p);
        }
    }

    /**
     * Handles a player calling "Mau-Mau".
     *
     * @param p The player calling "Mau-Mau".
     */
    void maumau(Player p) {
        if (p == getCurrentPlayer()) {
            state.maumau(p);
        }
    }

    /**
     * Returns the list of players participating in the game.
     *
     * @return The list of players.
     */
    List<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the ranking of players based on the order they finished the game.
     *
     * @return The ranking of players.
     */
    List<Player> getRanking() {
        return ranking;
    }

    /**
     * Returns {@code true} if the given player has called "Mau" and is
     * therefore allowed to finish the game with an empty hand.
     */
    boolean didCallMau(Player p) {
        return state instanceof MauState && remember == p;
    }

    /**
     * Marks a player who failed to announce "Mau" so that they draw an
     * additional penalty card at the start of their next turn.
     */
    void flagMissedMau(Player p) {
        this.penalized = p;
        this.penaltyArmed = false;
    }

    /**
     * Adds a player to the game.
     *
     * @param player The player to add.
     * @throws IllegalArgumentException if a player with the same name already exists.
     */
    void addPlayer(Player player) {
        for (Player p : players) {
            if (p.getName().equals(player.getName())) {
                throw new IllegalArgumentException("duplicate player: " + player.getName());
            }
        }
        players.add(player);
    }

    /**
     * Moves to the next player's turn in the game.
     *
     * @param n The number of turns to proceed.
     */
    private void localNextTurn(int n) {
        if (players.isEmpty()) {
            return;
        }

        n = ((n % players.size()) + players.size()) % players.size();
        for (int i = 0; i < n; i++) {
            players.addLast(players.removeFirst());
        }
    }

    /**
     * Finishes a player's participation in the game.
     *
     * @param p The player to finish.
     */
    private void finishPlayer(Player p) {
        players.remove(p);
        ranking.add(p);
        if (penalized == p) {
            penalized = null;
        }
    }

    /**
     * Returns the current player whose turn it is.
     *
     * @return The current player.
     */
    Player getCurrentPlayer() {
        return players.isEmpty() ? null : players.getFirst();
    }
}