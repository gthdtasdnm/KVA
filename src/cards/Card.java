package cards;

/**
 * Represents a playing card with a rank and a suit.
 */
public record Card(Rank rank, Suit suit) implements Comparable<Card> {

    /**
     * Returns a string representation of the card.
     *
     * @return A string representing the card's rank followed by its suit.
     */
    @Override
    public String toString() {
        return rank.toString() + suit;
    }

    /**
     * Compares this card with another card for order.
     *
     * @param other The card to be compared.
     * @return A negative integer, zero, or a positive integer if this card is less than, equal to, or greater than the specified card.
     */
    @Override
    public int compareTo(Card other) {
        // Zuerst nach Rank vergleichen
        int rankCompare = Integer.compare(this.rank.ordinal(), other.rank.ordinal());

        // Wenn Rank gleich, dann Suit vergleichen
        if (rankCompare == 0) {
            return Integer.compare(this.suit.ordinal(), other.suit.ordinal());
        }

        return rankCompare;
    }
}
