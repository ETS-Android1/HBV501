package vidmot;

import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;

public class StartupDialog extends Dialog<String> {
	private ButtonType confirm = new ButtonType("Staðfesta", ButtonBar.ButtonData.OK_DONE);
	private ButtonType cancel = new ButtonType("Hætta við", ButtonBar.ButtonData.CANCEL_CLOSE);

	@FXML
	private TextField name;
	public StartupDialog() {
		this.setTitle("Nafn");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StartupDialog.fxml"));
		loader.setRoot(this.getDialogPane());
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		getDialogPane().getButtonTypes().addAll(confirm, cancel);

		this.setResultConverter(b -> {
				if (b.getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) {
					return this.name.getText();
				}
				return null;
			});
	}
}
