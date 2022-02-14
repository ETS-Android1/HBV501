package vidmot;

import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class Player {
	private final vinnsla.Player data;
	private final HBox container;
	private final Label display;

	public Player(String name, HBox container, Label display) {
		this.data = new vinnsla.Player(name);
		this.container = container;
		this.display = display;
	}

	private void updateScore() {
		this.display.setText(String.format("%s (%d)", this.data.name(), this.data.value()));
	}

	public String name() {
		return this.data.name();
	}

	public short value() {
		return this.data.value();
	}

	public short giveCard(vinnsla.Card c) {
		return this.giveCard(c, true);
	}

	public short giveCard(vinnsla.Card c, boolean reveal) {
		this.data.giveCard(c);
		this.updateScore();
		var card = new Card(c);
		this.container.getChildren().add(card);
		if (reveal) {
			card.reveal();
		}


		return this.value();
	}
}
