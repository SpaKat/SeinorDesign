import java.io.File;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class UAVtoMissionData extends MenuItem {
	File selectedFile;
	uavMissionLog missionLog;
	Stage stage; 
	MavLinkComLog comLog;
	ComboBox<UavMission> TheUAVMissions;
	public UAVtoMissionData() {
		missionLog = new uavMissionLog();
		setText("UAV data to Mission");
	}
	public void runMissionData() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("CSV", "*.csv"),
				new ExtensionFilter("TLog", "*.tlog"));
				
		selectedFile = fileChooser.showOpenDialog(stage);
		try {
			comLog = new MavLinkComLog(selectedFile);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		
		TheUAVMissions = new ComboBox<UavMission>();
		VBox vbox = new VBox();
		missionLog.getMissions().forEach(mission ->{
			TheUAVMissions.getItems().add(mission);
		});
		Button enter = new Button("Enter");
		enter.setOnAction(e-> {
			SaveMavLinkToUavMission saveMavlinkUav = new SaveMavLinkToUavMission(TheUAVMissions.getValue(),comLog);
			stage.close();
		});
		vbox.getChildren().addAll(TheUAVMissions,enter);
		Scene scene = new Scene(vbox);
		stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
}
