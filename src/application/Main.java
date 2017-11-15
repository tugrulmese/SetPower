package application;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("SetPowerMain.fxml"));
			Scene scene = new Scene(root, 950, 580);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.load(getClass().getResource("SetPowerMain.fxml").openStream());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler() {

				@Override
				public void handle(Event event) {
					SetPowerMainController fooController = (SetPowerMainController) fxmlLoader.getController();
					fooController.closeApp(null);
					
				}
				
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
