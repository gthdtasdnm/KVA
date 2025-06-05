package cards;

/**
 * Represents the suits of playing cards.
 */
public enum Suit {
    /**
     * Represents the Hearts suit.
     */
    HEARTS,
    /**
     * Represents the Diamonds suit.
     */
    DIAMONDS,
    /**
     * Represents the Clubs suit.
     */
    CLUBS,
    /**
     * Represents the Spades suit.
     */
    SPADES;

    /**
     * Returns a string representation of the suit.
     *
     * @return A string representing the suit.
     */
    @Override
    public String toString() {
        return switch (this) {
            case HEARTS -> "\u2665\uFE0E";
            case DIAMONDS -> "\u2666\uFE0E";
            case CLUBS -> "\u2663\uFE0E";
            case SPADES -> "\u2660\uFE0E";
        };
    }
}
