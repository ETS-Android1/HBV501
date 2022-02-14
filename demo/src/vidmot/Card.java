package vidmot;

import java.lang.RuntimeException;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import vinnsla.Rank;

public class Card extends AnchorPane {
	@FXML
	private AnchorPane background;
	@FXML
	private Label topLeft;
	@FXML
	private Label bottomRight;
	@FXML
	private ImageView topRight;
	@FXML
	private ImageView bottomLeft;
	private vinnsla.Card data;
	private Image img;
	private boolean revealed;

	public Card(vinnsla.Card card) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("card.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		this.data = card;

		String imgFile = "";
		switch (card.suit()) {
		case DIAMOND:
			imgFile += "diamond.png";
			break;
		case CLUB:
			imgFile += "club.png";
			break;
		case HEART:
			imgFile += "heart.png";
			break;
		case SPADE:
			imgFile += "spade.png";
			break;
		}

		this.img = new Image("/images/" + imgFile);
		this.background.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
	}

	public void reveal() {
		this.revealed = true;
		this.topRight.setImage(this.img);
		this.bottomLeft.setImage(this.img);
		this.background.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		this.topLeft.setText(this.data.rank().toString());
		this.bottomRight.setText(this.data.rank().toString());

		if (this.data.rank().equals(Rank.SIX) || this.data.rank().equals(Rank.NINE)) {
			this.topLeft.setUnderline(true);
			this.bottomRight.setUnderline(true);
		}

	}
}
