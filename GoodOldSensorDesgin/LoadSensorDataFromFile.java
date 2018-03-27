import java.io.File;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class LoadSensorDataFromFile {
	File selectedFile;
	uavMissionLog missionLog;
	Stage stage;
	ComboBox<UavMission> TheUAVMissions;
	ComboBox<Integer> TheUAVnum;
	public LoadSensorDataFromFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Sensor Package Data");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));

		selectedFile = fileChooser.showOpenDialog(stage);
		TheUAVnum = new ComboBox<Integer>();
		TheUAVMissions = new ComboBox<UavMission>();
		missionLog = new uavMissionLog();
		VBox vbox = new VBox();

		missionLog.getMissions().forEach(mission ->{
			TheUAVMissions.getItems().add(mission);
		});
		TheUAVMissions.setOnAction(e ->{
			TheUAVnum.getItems().clear();
			for (int i = 0; i <TheUAVMissions.getValue().getNumberUAVS(); i++) {
				TheUAVnum.getItems().add(Integer.valueOf(i));
			}
		});
		Button enter = new Button("Enter");
		enter.setOnAction(e-> {
			new SensorPackageDataSave(TheUAVMissions.getValue(),selectedFile,TheUAVnum.getValue().intValue());
			stage.close();
		});

		vbox.getChildren().addAll(TheUAVMissions,TheUAVnum,enter);
		Scene scene = new Scene(vbox);
		stage = new Stage();
		stage.setScene(scene);
		stage.show();

	}

}
