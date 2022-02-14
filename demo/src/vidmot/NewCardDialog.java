package vidmot;

import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import java.util.Optional;

public class NewCardDialog extends Dialog {
	private ButtonType newCard = new ButtonType("Nýtt Spil", ButtonBar.ButtonData.OK_DONE);
	private ButtonType stop = new ButtonType("Hætta", ButtonBar.ButtonData.CANCEL_CLOSE);

	public NewCardDialog() {
		this.setTitle("Nýtt Spil?");
		getDialogPane().getButtonTypes().addAll(newCard, stop);
	}

	public boolean ask() {
		Optional<ButtonType> res = this.showAndWait();
		if (res.isPresent()) {
			return res.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE);
		}
		return false;
	}
}
