package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.sql.Statement;
import java.util.Optional;

public class SetPowerMainController {

	public String dbAddress = "C:\\SetPower\\src\\db\\powerdb.sqlite";

	public Connection conn = null;
	private ObservableList<SetInfo> setData = FXCollections.observableArrayList();

	@FXML
	private CheckBox buildCheck;

	@FXML
	private TextArea reasonArea;

	@FXML
	private Pane paneControl;

	@FXML
	private ListView<String> listViewCheck;

	@FXML
	private TextArea notesArea;

	@FXML
	private MenuItem exitProgram;

	@FXML
	private MenuItem menuItemNew;

	@FXML
	private MenuItem menuItemSave;

	@FXML
	private MenuItem menuItemRevert;

	@FXML
	private TableView<SetInfo> allDataTable;

	@FXML
	private Button previewScene;

	@FXML
	private CheckBox uploadCheck;

	@FXML
	private CheckBox jeetahCheck;
	@FXML
	private MenuItem helpAbout;

	@FXML
	private TextField jafBranchText;

	@FXML
	private TextField productVersion;

	@FXML
	private ComboBox<String> langCombo;

	@FXML
	private CheckBox versionCheck;

	@FXML
	private CheckBox earWithCheck;

	@FXML
	private Label saveStatusLabel;

	@FXML
	private TextField prodBranchText;

	@FXML
	private TextField testBranchText;

	@FXML
	private TextField dreamBrancText;

	@FXML
	private TextField antweetyBranchText;

	@FXML
	private TextField hudsonEarText;

	@FXML
	private TextField earBuildText;

	@FXML
	private TextField CodeTextBox;

	@FXML
	private CheckBox runCheck;

	@FXML
	private Button SaveButton1;

	@FXML
	private Button cancelBtn;

	@FXML
	private Pane paneRight;

	@FXML
	private Label connectionStatusLabel;

	@FXML
	private Button connectButton;

	@FXML
	private ChoiceBox<String> selectData;

	@FXML
	private TableColumn<SetInfo, String> codeColumn;

	@FXML
	private TableColumn<SetInfo, String> jafColumn;

	@FXML
	private TableColumn<SetInfo, String> prodColumn;

	public static String previewText = "";

	public void tableAddData(SetInfo obj) {

		if (obj == null)
			return;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from HUDSONFILES where Code = '" + obj.code() + "'");

			while (rs.next()) {

				CodeTextBox.setText(rs.getString("Code"));
				jafBranchText.setText(rs.getString("JafBranchName"));
				prodBranchText.setText(rs.getString("ProdBranchName"));
				testBranchText.setText(rs.getString("TestBranchName"));
				dreamBrancText.setText(rs.getString("DreamHrBranchName"));
				antweetyBranchText.setText(rs.getString("AntweetyBranchName"));
				hudsonEarText.setText(rs.getString("HudsonEarFolder"));
				earBuildText.setText(rs.getString("EarBuildFile"));

				runCheck.setText(rs.getString("RunJacoco"));
				if (runCheck.getText().compareTo("true") == 0)
					runCheck.setSelected(true);
				else
					runCheck.setSelected(false);

				buildCheck.setText(rs.getString("BuildTools"));
				if (buildCheck.getText().compareTo("true") == 0)
					buildCheck.setSelected(true);
				else
					buildCheck.setSelected(false);

				uploadCheck.setText(rs.getString("UpgradeLang"));
				if (uploadCheck.getText().compareTo("true") == 0)
					uploadCheck.setSelected(true);
				else
					uploadCheck.setSelected(false);
				jeetahCheck.setText(rs.getString("BuildJeetah"));
				if (jeetahCheck.getText().compareTo("true") == 0)
					jeetahCheck.setSelected(true);
				else
					jeetahCheck.setSelected(false);
				langCombo.setValue(rs.getString("EarLang"));
				versionCheck.setText(rs.getString("VersionUpdate"));
				if (versionCheck.getText().compareTo("true") == 0)
					versionCheck.setSelected(true);
				else
					versionCheck.setSelected(false);
				productVersion.setText(rs.getString("ProductVersion"));
				earWithCheck.setText(rs.getString("EarWithAntweety"));
				if (earWithCheck.getText().compareTo("true") == 0)
					earWithCheck.setSelected(true);
				else
					earWithCheck.setSelected(false);

				reasonArea.setText(rs.getString("Reason"));
				notesArea.setText(rs.getString("Notes"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@FXML
	public void initialize() {
		comboboxItemAdd();
		if (codeColumn == null)
			codeColumn = new TableColumn<SetInfo, String>();
		codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
		if (jafColumn == null)
			jafColumn = new TableColumn<SetInfo, String>();
		jafColumn.setCellValueFactory(cellData -> cellData.getValue().JaffBranchNameProperty());
		if (prodColumn == null)
			prodColumn = new TableColumn<SetInfo, String>();
		prodColumn.setCellValueFactory(cellData -> cellData.getValue().ProdBrancNameProperty());
		if (paneControl == null)
			paneControl = new Pane();
		paneControl.setDisable(true);
	}

	private void deleteMenu() {
		allDataTable.setRowFactory(new Callback<TableView<SetInfo>, TableRow<SetInfo>>() {
			@Override
			public TableRow<SetInfo> call(TableView<SetInfo> tableView) {
				final TableRow<SetInfo> row = new TableRow<>();
				final ContextMenu contextMenu = new ContextMenu();
				final MenuItem removeMenuItem = new MenuItem("Remove");

				removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Removition Warning");
						alert.setHeaderText("Will be deleted from the registration database.");
						alert.setContentText("Are you sure about that?");

						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK) {
							allDataTable.getItems().remove(row.getItem());
							removeParam(row.getItem().getRef());
						} else {
							alert.close();
						}
					}

				});
				contextMenu.getItems().add(removeMenuItem);

				row.contextMenuProperty()
						.bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));
				return row;
			}
		});
	}

	private void removeParam(int ref) {

		connOpen();

		String deleteStatement = "DELETE FROM HUDSONFILES WHERE LogicalRef = " + ref;

		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery(deleteStatement);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void saveAction() {
		if (conn == null) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Connection Error!");
			alert.setHeaderText("Database Connection Error");
			alert.setContentText("Try connecting again");

			alert.showAndWait();
		} else {

			if (!CodeTextBox.getText().isEmpty()) {
				verifyCode(CodeTextBox.getText());

			} else {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Empty Field Warning");
				alert.setHeaderText("Code field can not be empty");
				alert.setContentText("Ooops, you should enter a code!");
				alert.showAndWait();
			}
		}
		allDataTable.getItems().clear();
		createData();
	}

	@FXML
	public void onSave1(ActionEvent event) {
		System.out.println("Save button clicked!");

		saveAction();

	}

	@FXML
	void onCancel(ActionEvent event) {

		System.out.println("Cancel button clicked!");
		paneControl.setDisable(true);
		connectButton.setDisable(false);
		allDataTable.getItems().clear();
		clearControl();
		connectionStatusLabel.setText("Bağlantı kesildi.");
		previewScene.setDisable(true);
		connClose();
	}

	@FXML
	void onSelect(ActionEvent event) {

		CheckBox chkBox = (CheckBox) event.getSource();
		if (chkBox.isSelected()) {

			chkBox.setText("true");
		} else {
			chkBox.setText("false");
		}
	}

	@FXML
	void closeApp(ActionEvent event) {
		connClose();
		System.out.println("Database connection is closed");
		Platform.exit();
	}

	@FXML
	void aboutAlert(ActionEvent event) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About SetPower");
		alert.setHeaderText("Parameter file generator for Hudson" + "\n" + "Created By Tugrul.Mese@logo.com.tr");
		alert.showAndWait();

	}

	@FXML
	void itemNew(ActionEvent event) {

		clearControl();

	}

	@FXML
	void itemSave(ActionEvent event) {

		if (conn != null)
			saveAction();
	}

	@FXML
	void itemRevert(ActionEvent event) {

		tableAddData((SetInfo) ((TableViewSelectionModel<?>) allDataTable.getSelectionModel()).getSelectedItem());

	}

	@FXML
	void openPreview(ActionEvent event) {

		try {
			previewText = createPreviewText();
			Stage stage = new Stage();
			Parent root1 = FXMLLoader.load(getClass().getResource("Preview.fxml"));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Preview Hudson Files");
			stage.setScene(new Scene(root1, 495, 480));
			stage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@FXML
	void onConnect(ActionEvent event) {

		paneControl.setDisable(false);
		createData();
		connectButton.setDisable(true);
		allDataTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SetInfo>() {

			@Override
			public void changed(ObservableValue<? extends SetInfo> observable, SetInfo oldValue, SetInfo newValue) {
				tableAddData(newValue);

			}
		});
		deleteMenu();

	}

	public int verifyCode(String code) {

		String sqlStationForCode = "SELECT LogicalRef FROM HUDSONFILES WHERE Code LIKE '" + code + "'";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlStationForCode);

			int ref = 0;
			while (rs.next()) {
				ref = rs.getInt("LogicalRef");
			}

			if (ref == 0)
				insertNewParam();

			else
				updateParam(ref);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 0;

	}

	public void comboboxItemAdd() {
		if (langCombo == null || langCombo.getItems() == null)
			return;
		langCombo.getItems().addAll("standart", "full");

	}

	private void updateParam(int ref) {
		if (ref == 0)
			return;
		String code = CodeTextBox.getText();
		String jafBranch = jafBranchText.getText();
		String prodBranch = prodBranchText.getText();
		String testBranch = testBranchText.getText();
		String dreamBranch = dreamBrancText.getText();
		String antweetyBranch = antweetyBranchText.getText();
		String hudsonEarFolder = hudsonEarText.getText();
		String earBuild = earBuildText.getText();
		String runJacoco = runCheck.getText();
		String buildTools = buildCheck.getText();
		String uploadLang = uploadCheck.getText();
		String buildJeetah = jeetahCheck.getText();
		String earLang = langCombo.getValue();
		String versionLang = versionCheck.getText();
		String productVers = productVersion.getText();
		String earWithAnt = earWithCheck.getText();
		String reasonText = reasonArea.getText();
		String notesText = notesArea.getText();

		String updateStatement = "UPDATE HUDSONFILES SET Code = '" + code + "'," + "JafBranchName = '" + jafBranch
				+ "' , " + " ProdBranchName= '" + prodBranch + "'," + "  TestBranchName ='" + testBranch + "', "
				+ " DreamHrBranchName = '" + dreamBranch + "'," + " AntweetyBranchName = '" + antweetyBranch + "',"
				+ " HudsonEarFolder = '" + hudsonEarFolder + "'," + " EarBuildFile = '" + earBuild + "',"
				+ " RunJacoco='" + runJacoco + "'," + "BuildTools='" + buildTools + "'," + "UpgradeLang='" + uploadLang
				+ "'," + "BuildJeetah='" + buildJeetah + "'," + " EarLang='" + earLang + "'," + "VersionUpdate='"
				+ versionLang + "'," + "ProductVersion='" + productVers + "'," + "EarWithAntweety='" + earWithAnt + "',"
				+ "Reason='" + reasonText + "'," + "Notes='" + notesText + "'  WHERE LogicalRef= " + ref;

		System.out.println(updateStatement);
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery(updateStatement);
			conn.commit();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		previewScene.setDisable(false);
		saveStatusLabel.setText("Update Succeed");

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> saveStatusLabel.setText("")),
				new KeyFrame(Duration.seconds(0)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	public void clearControl() {
		CodeTextBox.clear();
		jafBranchText.clear();
		prodBranchText.clear();
		testBranchText.clear();
		dreamBrancText.clear();
		antweetyBranchText.clear();
		hudsonEarText.clear();
		earBuildText.clear();
		runCheck.setText("false");
		runCheck.setSelected(false);
		buildCheck.setText("false");
		buildCheck.setSelected(false);
		uploadCheck.setText("false");
		uploadCheck.setSelected(false);
		jeetahCheck.setText("false");
		jeetahCheck.setSelected(false);
		versionCheck.setText("false");
		versionCheck.setSelected(false);
		earWithCheck.setText("false");
		earWithCheck.setSelected(false);
		reasonArea.clear();
		notesArea.clear();
		langCombo.setValue(null);
		productVersion.clear();

	}

	private void insertNewParam() {
		String code = CodeTextBox.getText();
		String jafBranch = jafBranchText.getText();
		String prodBranch = prodBranchText.getText();
		String testBranch = testBranchText.getText();
		String dreamBranch = dreamBrancText.getText();
		String antweetyBranch = antweetyBranchText.getText();
		String hudsonEarFolder = hudsonEarText.getText();
		String earBuild = earBuildText.getText();
		String runJacoco = runCheck.getText();
		String buildTools = buildCheck.getText();
		String uploadLang = uploadCheck.getText();
		String buildJeetah = jeetahCheck.getText();
		String earLang = langCombo.getValue();
		String versionLang = versionCheck.getText();
		String earWithAnt = earWithCheck.getText();
		String reasonText = reasonArea.getText();
		String notesText = notesArea.getText();
		String productVers = productVersion.getText();

		String insertStatement = "INSERT INTO HUDSONFILES (Code,JafBranchName,ProdBranchName,TestBranchName,DreamHrBranchName,AntweetyBranchName,HudsonEarFolder,EarBuildFile,RunJacoco,BuildTools,UpgradeLang,BuildJeetah,VersionUpdate,ProductVersion,EarWithAntweety,EarLang,Reason,Notes) VALUES ('"
				+ code + "','" + jafBranch + "','" + prodBranch + "','" + testBranch + "','" + dreamBranch + "','"
				+ antweetyBranch + "','" + hudsonEarFolder + "','" + earBuild + "' , '" + runJacoco + "','" + buildTools
				+ "','" + uploadLang + "','" + buildJeetah + "','" + versionLang + "','" + productVers + "','"
				+ earWithAnt + "','" + earLang + "','" + reasonText + "','" + notesText + "' )";
		System.out.println(insertStatement);

		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery(insertStatement);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		previewScene.setDisable(false);
		saveStatusLabel.setText("Insert Succeed");
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> saveStatusLabel.setText("")),
				new KeyFrame(Duration.seconds(0)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	public void createData() {
		connOpen();
		try {
			String sqlAllTable = "SELECT * FROM HUDSONFILES";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlAllTable);

			while (rs.next()) {

				setData.add(new SetInfo(rs.getInt("LogicalRef"), rs.getString("Code"), rs.getString("JafBranchName"),
						rs.getString("ProdBranchName")));

			}
			allDataTable.setItems(getSetData());
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public ObservableList<SetInfo> getSetData() {

		return setData;
	}

	private String createPreviewText() {
		String result = "";
		result = "jaf_branch_name=" + jafBranchText.getText() + "\n";
		result = result + "prod_branch_name=" + prodBranchText.getText() + "\n";
		result = result + "tests_branch_name=" + testBranchText.getText() + "\n";
		result = result + "dreamhr_branch_name=" + dreamBrancText.getText() + "\n";
		result = result + "antweety_branch_name=" + antweetyBranchText.getText() + "\n";
		result = result + "hudson_ear_folder=" + hudsonEarText.getText() + "\n";
		result = result + "ear_build_file=" + earBuildText.getText() + "\n";
		result = result + "run_jacoco=" + runCheck.getText() + "\n";
		result = result + "build_tools=" + buildCheck.getText() + "\n";
		result = result + "upload_languages=" + uploadCheck.getText() + "\n";
		result = result + "reason=" + reasonArea.getText() + "\n";
		result = result + "notes=" + notesArea.getText() + "\n";
		result = result + "build_jeetah=" + jeetahCheck.getText() + "\n";
		result = result + "ear_lang=" + langCombo.getValue() + "\n";
		result = result + "version_update=" + versionCheck.getText() + "\n";
		result = result + "ear.with.antweety=" + earWithCheck.getText() + "\n";

		return result;
	}

	public void writeNewFile(String text) {

		@SuppressWarnings("unused")
		PreviewController controller = new PreviewController();
		File location = PreviewController.filePath;

		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(location, false)))) {

			out.write(text);

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		System.out.println("File is Created");

	}

	private void connClose() {
		if (conn != null) {
			try {
				conn.close(); //
			} catch (SQLException e) {

				System.out.println(e.getMessage());
			}

		}

	}

	private void connOpen() {

		String url = "jdbc:sqlite:" + dbAddress;
		try {
			conn = DriverManager.getConnection(url);
			if (conn != null) {
				conn.getSchema();
				connectionStatusLabel.setText("Bağlantı başarılı");
				Timeline timeline = new Timeline(
						new KeyFrame(Duration.seconds(2), event -> connectionStatusLabel.setText("")),
						new KeyFrame(Duration.seconds(0)));

				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connectionStatusLabel.setText("Baglanti basarisiz");
			Timeline timeline = new Timeline(
					new KeyFrame(Duration.seconds(2), event -> connectionStatusLabel.setText("")),
					new KeyFrame(Duration.seconds(0)));

			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
		}

	}
}
