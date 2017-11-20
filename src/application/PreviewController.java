package application;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PreviewController {

	@FXML
	private TextArea hudsonTextArea;

	@FXML
	private Button createFileSave;

	@FXML
	private Button cancelButton;

	public static File filePath;

	@FXML
	public void onSave() {

		FileChooser fileChooser = new FileChooser();
		Stage stage = new Stage();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("Params Files (*.params)", "*.params");
		fileChooser.getExtensionFilters().addAll(extFilter2, extFilter);
		fileChooser.setInitialFileName("hudson");
		File file = fileChooser.showSaveDialog(stage);
		if (file != null) {
			System.out.println("file adress" + file);
			System.out.println(file.getName());
			filePath = file;
			SetPowerMainController controller = new SetPowerMainController();
			String text = SetPowerMainController.previewText;
			controller.writeNewFile(text);
			Stage stage3 = (Stage) createFileSave.getScene().getWindow();
			stage3.close();
			succesWrite();

		}

	}

	@FXML
	public void initialize() {
		hudsonTextArea.setText(SetPowerMainController.previewText);
	}

	@FXML
	void onCancel(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	public void succesWrite() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Writing completed");
		alert.setHeaderText(null);
		alert.setContentText("File is Created.");

		alert.showAndWait();

	}

}
