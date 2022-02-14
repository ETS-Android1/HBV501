package vinnsla;

public class Card {
	private final Suit suit;
	private final Rank rank;

	public Card(Suit suit, Rank rank) {
		this.rank = rank;
		this.suit = suit;
	}

	public Suit suit() {
		return this.suit;
	}

	public Rank rank() {
		return this.rank;
	}

	@Override
	public String toString() {
		return String.format("Card{%s, %s}", this.suit, this.rank);
	}
}
