package cards.maumau.gui;

import cards.Card;
import cards.Suit;
import cards.maumau.model.IObserver;
import cards.maumau.model.MauMau;
import cards.maumau.model.Player;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static cards.maumau.model.GameState.CHOOSE_SUIT;
import static cards.maumau.model.GameState.GAME_CANCELED;
import static cards.maumau.model.GameState.GAME_OVER;
import static java.awt.BorderLayout.CENTER;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;

/**
 * Represents the graphical user interface for a player in the Mau-Mau card game.
 */
public class PlayerFrame extends JFrame implements IObserver {
    private final transient Player player;
    private final GameTableModel playerTableModel;
    private final DefaultListModel<Card> cardListModel = new DefaultListModel<>();
    private final JList<Card> cardList = new JList<>(cardListModel);
    private final JLabel topCardLabel = new JLabel("top card:");
    private final JLabel stateLabel = new JLabel("game starts");
    private final JPanel pane = new JPanel(new GridBagLayout());

    /**
     * Constructs a new PlayerFrame object.
     *
     * @param player The player associated with this frame.
     */
    public PlayerFrame(Player player) {
        super(player.getName());
        this.player = player;
        final MauMau game = player.getGame();
        playerTableModel = new GameTableModel(game);
        update();
        initializeUI();
        game.addObserver(this);
    }

    /**
     * Initializes the user interface components.
     */
    private void initializeUI() {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pane, CENTER);

        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 9;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.ipadx = 15;
        constraints.ipady = 15;
        constraints.insets = new Insets(5, 5, 5, 5);

        cardList.setBorder(BorderFactory.createLoweredBevelBorder());
        cardList.setCellRenderer(new CardListRenderer(player));
        pane.add(cardList, constraints);

        constraints.gridx = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 4;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        final JTable table = new JTable(playerTableModel);
        table.setBorder(BorderFactory.createLoweredBevelBorder());
        pane.add(table, constraints);

        constraints.gridy++;
        constraints.ipadx = 0;
        constraints.ipady = 5;
        constraints.insets = new Insets(0, 0, 0, 0);
        pane.add(topCardLabel, constraints);

        constraints.gridy++;
        pane.add(stateLabel, constraints);

        constraints.gridy++;
        pane.add(makeButton("choose card", this::chooseCard), constraints);

        constraints.gridy++;
        pane.add(makeButton("skip", e -> player.skip()), constraints);

        constraints.gridy++;
        constraints.gridwidth = 1;
        for (Suit suit : Suit.values()) {
            pane.add(makeButton(suit.toString(), e -> player.chooseSuit(suit)), constraints);
            constraints.gridx++;
        }

        constraints.gridy++;
        constraints.gridx = 1;
        constraints.gridwidth = 4;
        pane.add(makeButton("have no 7", e -> player.no7()), constraints);

        constraints.gridy++;
        constraints.gridwidth = 2;
        pane.add(makeButton("\"Mau\"", e -> player.mau()), constraints);

        constraints.gridx = 3;
        pane.add(makeButton("\"Mau-Mau\"", e -> player.maumau()), constraints);

        pack();
    }

    /**
     * Creates a JButton with the specified text and action listener.
     *
     * @param text     The text displayed on the button.
     * @param listener The action listener for the button.
     * @return The created JButton.
     */
    private JButton makeButton(String text, ActionListener listener) {
        final JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    /**
     * Action performed when the "choose card" button is clicked.
     *
     * @param e The ActionEvent object.
     */
    private void chooseCard(ActionEvent e) {
        final List<Card> selected = cardList.getSelectedValuesList();
        if (selected.size() == 1)
            player.chooseCard(selected.getFirst());
        else if (selected.isEmpty())
            error("You must select a card first!");
        else
            error("You must not select more than a single card!");
    }

    /**
     * Updates the user interface.
     */
    @Override
    public void update() {
        playerTableModel.update();
        updateCardList();
        updateTopCardLabel();
        updateStateLabel();
        updateBorderColor();
    }

    /**
     * Updates the card list displayed in the UI.
     */
    private void updateCardList() {
        cardListModel.clear();
        player.getCards().stream().sorted().forEach(cardListModel::addElement);
    }

    /**
     * Updates the label displaying the top card on the discard pile.
     */
    private void updateTopCardLabel() {
        final List<Card> pile = player.getGame().getDiscardPile();
        topCardLabel.setText(pile.isEmpty() ? "no top card" : "top card: " + pile.getFirst());
    }

    /**
     * Updates the label displaying the current game state.
     */
    private void updateStateLabel() {
        final MauMau game = player.getGame();
        if (game.getGameState() == GAME_CANCELED)
            stateLabel.setText("Game canceled");
        else if (game.getGameState() == GAME_OVER)
            stateLabel.setText("Game over");
        else if (game.get7Counter() > 0)
            stateLabel.setText(game.get7Counter() + " seven on discard pile");
        else if (game.getChosenSuit() != null)
            stateLabel.setText(game.getChosenSuit() + " chosen");
        else if (game.getGameState() == CHOOSE_SUIT && game.getCurrentPlayer() == player)
            stateLabel.setText("Choose a suit");
        else
            stateLabel.setText(" ");
    }

    /**
     * Updates the border color of the frame based on the current player's turn.
     */
    private void updateBorderColor() {
        final Color borderColor = player.getGame().getCurrentPlayer() == player ? GREEN : RED;
        pane.setBorder(BorderFactory.createLineBorder(borderColor, 5));
    }

    /**
     * Displays an error message dialog.
     *
     * @param msg The error message to display.
     */
    private void error(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a message dialog.
     *
     * @param msg The message to display.
     */
    @Override
    public void message(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}
