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
	ComboBox<Integer> TheUAVnum;
	public UAVtoMissionData() {
		missionLog = new uavMissionLog();
		setText("UAV data to Mission");
	}
	public void runMissionData() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open UAV Telemetry Log");
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
		TheUAVnum = new ComboBox<Integer>();
		VBox vbox = new VBox();
		TheUAVMissions.setOnAction(e ->{
			TheUAVnum.getItems().clear();
			for (int i = 0; i <TheUAVMissions.getValue().getNumberUAVS(); i++) {
				TheUAVnum.getItems().add(Integer.valueOf(i));
			}
		});
		missionLog.getMissions().forEach(mission ->{
			TheUAVMissions.getItems().add(mission);
		});
		Button enter = new Button("Enter");
		enter.setOnAction(e-> {
			new SaveMavLinkToUavMission(TheUAVMissions.getValue(),comLog,TheUAVnum.getValue().intValue());
			stage.close();
		});
		vbox.getChildren().addAll(TheUAVMissions,TheUAVnum,enter);
		Scene scene = new Scene(vbox);
		stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
}
