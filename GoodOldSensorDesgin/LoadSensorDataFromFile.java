import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
		vbox.setAlignment(Pos.CENTER);
		missionLog.getMissions().forEach(mission ->{
			TheUAVMissions.getItems().add(mission);
		});
		TheUAVMissions.setOnAction(e ->{
			TheUAVnum.getItems().clear();
			for (int i = 0; i <TheUAVMissions.getValue().getNumberUAVS(); i++) {
				TheUAVnum.getItems().add(Integer.valueOf(i));
			}
		});
		LoadSensorDataFromFileSetTime setTime = new LoadSensorDataFromFileSetTime();
		Button enter = new Button("Enter");
		enter.setOnAction(e-> {
			try {
				if (setTime.isValidDate()) {
					new SensorPackageDataSave(TheUAVMissions.getValue(),selectedFile,TheUAVnum.getValue().intValue(),setTime.getDate());
					stage.close();
				}
			}catch (Exception e1) {
				// TODO: Enter a mission and a uav
			}

		});
		Text selectMission =  new Text("Select Uav Mission");
		selectMission.setFont(new Font(15));
		Text selectUavId = new Text("Select UAV ID");
		selectUavId.setFont(new Font(15));
		vbox.getChildren().addAll(selectMission,TheUAVMissions,selectUavId,TheUAVnum,setTime,enter);
		Scene scene = new Scene(vbox);
		stage = new Stage();
		stage.setTitle("Load UAV Data From File");
		stage.setScene(scene);
		stage.show();



	}

}
