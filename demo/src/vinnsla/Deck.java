package vinnsla;

import java.util.Random;

public class Deck {
	private final Card[] cards;
	private final Random random;
	private int nextCard;

	public Deck() {
		this.random = new Random();
		this.nextCard = 0;
		this.cards = new Card[Suit.values().length * Rank.values().length];
		int i = 0;
		for (var suite : Suit.values()) {
			for (var rank : Rank.values()) {
				this.cards[i++] = new Card(suite, rank);
			}
		}

		this.shuffle();
	}

	public Card draw() {
		return this.cards[this.nextCard++];
	}

	public boolean isEmpty() {
		return this.nextCard == this.cards.length;
	}

	public void shuffle() {
		for (int i = 0; i < this.cards.length; i++) {
            int swap = i + this.random.nextInt(this.cards.length - i);     // between i and n-1
            Card temp = this.cards[i];
            this.cards[i] = this.cards[swap];
            this.cards[swap] = temp;
        }
		this.nextCard = 0;
	}

	public static void main(String[] args) {
		var d = new Deck();
		while (!d.isEmpty()) {
			System.out.println(d.draw());
		}
	}
}
