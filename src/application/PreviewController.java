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

//	public String hudsonPramAdress = "C:\\SetPower\\hudson.params";
	@FXML
	private TextArea hudsonTextArea;

	@FXML
	private Button kayitYeri;

	@FXML
	private Button writeButton;

	@FXML
	private Button cancelButton;

	public String s;
	public static File kayityeri;

	@FXML
	public void kayitYeri() {

		FileChooser fileChooser = new FileChooser();
		Stage stage = new Stage();
		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("Params Files (*.params)", "*.params");
		fileChooser.getExtensionFilters().addAll(extFilter, extFilter2);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(stage);
		System.out.println("file adress" + file);
		System.out.println(file.getName());
		kayityeri=file;
		SetPowerMainController controller = new SetPowerMainController();
		String text = SetPowerMainController.previewText;
		controller.writeNewFile(text);
		
		Stage stage3 = (Stage) kayitYeri.getScene().getWindow();
		stage3.close();
		succesWrite();
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

//	@SuppressWarnings("unused")
//	@FXML
//	void onWrite(ActionEvent event) {
//
//		SetPowerMainController controller = new SetPowerMainController();
//		String text = SetPowerMainController.previewText;
//		controller.writeNewFile(text);
//		if (text == null) {
//
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("ERROR.");
//			alert.setHeaderText("All values are empty.");
//			alert.setContentText("Check the values.");
//			alert.showAndWait();
//		}
//
//		else {
//
//			File f = new File(hudsonPramAdress);
//			if (f.exists() && !f.isDirectory()) {
//
//				try (BufferedReader reader = new BufferedReader(new FileReader(new File("hudson.params")))) {
//
//					String line;
//					while ((line = reader.readLine()) == null)
//						;
//
//					if (line != null) {
//						Alert alert = new Alert(AlertType.CONFIRMATION);
//						alert.setTitle("File is available");
//						alert.setHeaderText("There is a file with the same name");
//						alert.setContentText("Do you write on it?");
//
//						Optional<ButtonType> result = alert.showAndWait();
//						if (result.get() == ButtonType.OK) {
//
//							controller.writeNewFile(text);
//							alert.close();
//							succesWrite();
//						}
//						if (result.get() == ButtonType.CANCEL) {
//							alert.close();
//						}
//
//					} else {
//
//						controller.writeNewFile(text);
//						succesWrite();
//					}
//
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			} else {
//
//				controller.writeNewFile(text);
//				succesWrite();
//			}
//
//		}
//
//	}
//
	public void succesWrite() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Writing completed");
		alert.setHeaderText(null);
		alert.setContentText("File is Created.");

		alert.showAndWait();

	}

}
