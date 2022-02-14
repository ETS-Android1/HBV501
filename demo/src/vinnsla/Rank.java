package vinnsla;

public enum Rank {
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10),
	QUENN(10),
	KING(10),
	ACE(11);

	private final int value;

	private Rank(int rank) {
		this.value = rank;
	}

	public int value() {
		return this.value;
	}

	public String toString() {
		return ordinal() < 9 ? String.valueOf(this.value) : name().substring(0,1);
	}
}
