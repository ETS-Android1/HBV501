package vinnsla;

import java.util.ArrayList;

public class Player {
	private final ArrayList<Card> hand;
	private short value;
	private final String name;

	public Player(String name) {
		this.hand = new ArrayList<Card>();
		this.value = 0;
		this.name = name;
	}

	public boolean giveCard(Card c) {
		this.hand.add(c);
		this.value += c.rank().value();
		return this.value <= 21;
	}

	public String name() {
		return this.name;
	}

	public short value() {
		return this.value;
	}
}
