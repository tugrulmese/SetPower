package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

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

	}

	@FXML
	void onWrite(ActionEvent event) {

		SetPowerMainController controller = new SetPowerMainController();

		controller.writeNewFile();

	}



}
