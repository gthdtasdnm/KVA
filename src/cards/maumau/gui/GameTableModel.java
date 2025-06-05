package cards.maumau.gui;

import cards.maumau.model.MauMau;

import javax.swing.table.AbstractTableModel;

import static cards.maumau.model.GameState.GAME_CANCELED;
import static cards.maumau.model.GameState.GAME_OVER;

/**
 * Represents the table model for displaying player information in the Mau-Mau game.
 */
class GameTableModel extends AbstractTableModel {
    private final transient MauMau game;

    /**
     * Constructs a GameTableModel object.
     *
     * @param game The Mau-Mau game.
     */
    public GameTableModel(MauMau game) {
        this.game = game;
    }

    /**
     * Updates the table data.
     */
    void update() {
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        if (showRanking())
            return game.getRanking().size();
        return game.getPlayers().size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (showRanking())
            return switch (columnIndex) {
                case 0 -> game.getRanking().get(rowIndex).getName();
                case 1 -> rowIndex + 1;
                default -> null;
            };
        return switch (columnIndex) {
            case 0 -> game.getPlayers().get(rowIndex).getName();
            case 1 -> game.getPlayers().get(rowIndex).getCards().size();
            default -> null;
        };
    }

    /**
     * Checks if the ranking should be shown based on the current game state.
     *
     * @return True if the ranking should be shown, false otherwise.
     */
    private boolean showRanking() {
        return game.getGameState() == GAME_CANCELED || game.getGameState() == GAME_OVER;
    }
}
