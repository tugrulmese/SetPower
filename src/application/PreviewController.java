package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class PreviewController {

	@FXML
	private TextArea hudsonTextArea;

	@FXML
	private Button writeButton;

	@FXML
	private Button cancelButton;

	public String s;

	@FXML
	public void initialize() {
		hudsonTextArea.setText(SetPowerMainController.previewText);
	}

	@FXML
	void onCancel(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void onWrite(ActionEvent event) {

		SetPowerMainController controller = new SetPowerMainController();
		String text = SetPowerMainController.previewText;

		if (text == null) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR.");
			alert.setHeaderText("All values are empty.");
			alert.setContentText("Check the values.");
			alert.showAndWait();
		}

		else {
			controller.writeNewFile(text);
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Writing completed");
		alert.setHeaderText(null);
		alert.setContentText("Hudson Params file is created.");

		alert.showAndWait();
	}

}
