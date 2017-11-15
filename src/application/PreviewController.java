package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class PreviewController {

	public String hudsonPramAdress = "C:\\SetPower\\hudson.params";
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

	@SuppressWarnings("unused")
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

			File f = new File(hudsonPramAdress);
			if (f.exists() && !f.isDirectory()) {

				try (BufferedReader reader = new BufferedReader(new FileReader(new File("hudson.params")))) {

					String line;
					while ((line = reader.readLine()) == null)
						


					if (line != null) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("File is available");
						alert.setHeaderText("There is a file with the same name");
						alert.setContentText("Do you write on it?");

						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK) {

							controller.writeNewFile(text);
						}
					
						alert.close();
						
					} else {

						controller.writeNewFile(text);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {

				controller.writeNewFile(text);
			}

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Writing completed");
			alert.setHeaderText(null);
			alert.setContentText("Hudson Params file is created.");

			alert.showAndWait();

		}

	}
}
