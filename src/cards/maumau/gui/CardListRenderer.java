package cards.maumau.gui;

import cards.Card;
import cards.maumau.model.Player;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;

import static java.awt.Color.GREEN;
import static java.awt.Color.RED;

/**
 * Custom renderer for rendering cards in a JList representing the cards on a player's hand.
 */
class CardListRenderer extends DefaultListCellRenderer {
    private final transient Player player;

    /**
     * Constructs a CardListRenderer object.
     *
     * @param player The player associated with the renderer.
     */
    CardListRenderer(Player player) {
        this.player = player;
    }

    /**
     * Custom rendering of list cell components.
     *
     * @param list         The JList being rendered.
     * @param value        The value to be rendered.
     * @param index        The cell index.
     * @param isSelected   True if the cell is selected.
     * @param cellHasFocus True if the cell has focus.
     * @return The rendered component.
     */
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        final Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Card card) {
            setText(card.toString());
            setBackground(player.canPlay(card) ? GREEN : RED);
            if (isSelected)
                setBackground(getBackground().darker());
        }
        return c;
    }
}
