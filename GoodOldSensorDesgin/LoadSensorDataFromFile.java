import java.io.File;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoadSensorDataFromFile {
	File selectedFile;
	uavMissionLog missionLog;
	Stage stage;
	ComboBox<UavMission> TheUAVMissions;

	public LoadSensorDataFromFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Sensor Package Data");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));
				
		selectedFile = fileChooser.showOpenDialog(stage);

		TheUAVMissions = new ComboBox<UavMission>();
		VBox vbox = new VBox();
		missionLog.getMissions().forEach(mission ->{
			TheUAVMissions.getItems().add(mission);
		});
		Button enter = new Button("Enter");
		enter.setOnAction(e-> {
			SensorPackageDataSave saveMavlinkUav = new SensorPackageDataSave(TheUAVMissions.getValue(),selectedFile);
			stage.close();
		});
		vbox.getChildren().addAll(TheUAVMissions,enter);
		Scene scene = new Scene(vbox);
		stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
		
}
