package cards.maumau;

import cards.Card;
import cards.maumau.gui.PlayerFrame;
import cards.maumau.model.MauMau;
import cards.maumau.model.Player;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Represents the main class for running the Mau-Mau card game.
 */
public class MauMauGame {

    /**
     * Prints usage information for running the game.
     */
    private static void usage() {
        System.err.println("usage: <#cards per player> <#decks> <name1> <name2> ...");
        System.err.println(" where <#cards per player> >= 2 and <#decks> >= 1");
        System.exit(1);
    }

    /**
     * Main method for running the Mau-Mau game.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        if (args.length == 0)
            makeSimpleGame();
        else
            makeGeneralGame(args);
    }

    /**
     * Runs a simple game with predefined settings.
     */
    private static void makeSimpleGame() {
        final List<Card> deck = MauMauDeck.makeDeck(1);
        final MauMau game = new MauMau(5, deck);
        game.addPlayer("Jacqueline");
        game.addPlayer("Chantal");
        start(game);
    }

    /**
     * Runs a game with customizable settings based on command line arguments.
     *
     * @param args Command line arguments specifying game parameters.
     */
    private static void makeGeneralGame(String[] args) {
        if (args.length < 4) usage();
        final Iterator<String> it = Arrays.asList(args).iterator();
        try {
            final int numCardsPerPlayer = Integer.parseInt(it.next());
            final int numDecks = Integer.parseInt(it.next());
            if (numCardsPerPlayer < 2 || numDecks < 1) usage();
            final List<Card> deck = MauMauDeck.makeDeck(numDecks);
            final MauMau game = new MauMau(numCardsPerPlayer, deck);
            while (it.hasNext())
                game.addPlayer(it.next());
            start(game);
        }
        catch (NumberFormatException ex) {
            usage();
        }
    }

    /**
     * Starts the Mau-Mau game.
     *
     * @param game The Mau-Mau game to start.
     */
    private static void start(MauMau game) {
        game.startGame();
        for (Player player : game.getPlayers())
            new PlayerFrame(player).setVisible(true);
    }
}
