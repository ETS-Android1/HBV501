package vidmot;

import java.lang.Thread;
import java.lang.InterruptedException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BorderWidths;
import javafx.scene.control.Button;
import vinnsla.Deck;

public class BlackjackController {
	private  Deck deck;
	@FXML
	private Label dealerInfo;
	@FXML
	private Label playerInfo;
	@FXML
	private AnchorPane table;
	@FXML
	private HBox dealerCards;
	@FXML
	private HBox playerCards;
	@FXML
	private Button newGame;
	private String name;
	private Player player;
	private Player dealer;
	private NewCardDialog prompt;

	@FXML
	private void initialize() {
		var res = new StartupDialog().showAndWait();
		if (!res.isPresent() || res.get() == null) {
			System.exit(0);
		}
		this.name = res.get();
		this.deck = new Deck();
		this.prompt = new NewCardDialog();
		this.clearBoard();
		this.table.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

		var border = new Border(new BorderStroke(Color.BROWN,
												 BorderStrokeStyle.SOLID,
												 new CornerRadii(5.0),
												 new BorderWidths(1.0))
								);

		playerCards.setSpacing(5);
		playerCards.setBorder(border);

		dealerCards.setSpacing(5);
		dealerCards.setBorder(border);
	}

	public void newGame(ActionEvent event) {
		this.hideNewGameButton();
		this.deck.shuffle();
		this.player = new Player(this.name,
								 this.playerCards,
								 this.playerInfo
								 );

		this.dealer = new Player("Dealer",
								 this.dealerCards,
								 this.dealerInfo
								 );

		this.player.giveCard(this.deck.draw());
		this.dealer.giveCard(this.deck.draw());
		this.player.giveCard(this.deck.draw());
		this.dealer.giveCard(this.deck.draw(), false);

		if (this.player.value() == 21) {
			System.out.println("Blackjack");
			this.clearBoard();
			return;
		}

		while (this.prompt.ask() && this.player.giveCard(this.deck.draw()) <= 21) {}

		if (this.player.value() > 21) {
			System.out.println("Bust");
			this.clearBoard();
			return;
		}

		while (this.dealer.giveCard(this.deck.draw()) <= 17) {}

		if (this.dealer.value() > 21) {
			System.out.println("Dealer busts");
			this.clearBoard();
			return;
		}

		if (this.dealer.value() > this.player.value()) {
			System.out.println("Dealer wins");
			this.clearBoard();
			return;
		}

		System.out.println("Player wins");
		this.clearBoard();
		return;
	}

	private void clearBoard() {
		this.dealerInfo.setText("Dealer");
		this.playerInfo.setText(this.name);
		this.playerCards.getChildren().setAll();
		this.dealerCards.getChildren().setAll();
		this.showNewGameButton();
	}

	private void hideNewGameButton() {
		this.newGame.setVisible(false);
	}

	private void showNewGameButton() {
		this.newGame.setVisible(true);
	}
}
