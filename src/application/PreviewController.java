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
		if(file!=null)
		{
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

	// @SuppressWarnings("unused")
	// @FXML
	// void onWrite(ActionEvent event) {
	//
	// SetPowerMainController controller = new SetPowerMainController();
	// String text = SetPowerMainController.previewText;
	// controller.writeNewFile(text);
	// if (text == null) {
	//
	 
	// }
	//
	// else {
	//
	// File f = new File(hudsonPramAdress);
	// if (f.exists() && !f.isDirectory()) {
	//
	// try (BufferedReader reader = new BufferedReader(new FileReader(new
	// File("hudson.params")))) {
	//
	// String line;
	// while ((line = reader.readLine()) == null)
	// ;
	//
	// if (line != null) {
	// Alert alert = new Alert(AlertType.CONFIRMATION);
	// alert.setTitle("File is available");
	// alert.setHeaderText("There is a file with the same name");
	// alert.setContentText("Do you write on it?");
	//
	// Optional<ButtonType> result = alert.showAndWait();
	// if (result.get() == ButtonType.OK) {
	//
	// controller.writeNewFile(text);
	// alert.close();
	// succesWrite();
	// }
	// if (result.get() == ButtonType.CANCEL) {
	// alert.close();
	// }
	//
	// } else {
	//
	// controller.writeNewFile(text);
	// succesWrite();
	// }
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// } else {
	//
	// controller.writeNewFile(text);
	// succesWrite();
	// }
	//
	// }
	//
	// }
	//
	public void succesWrite() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Writing completed");
		alert.setHeaderText(null);
		alert.setContentText("File is Created.");

		alert.showAndWait();

	}

}
