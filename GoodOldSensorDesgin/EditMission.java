import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditMission extends MenuItem{

	public EditMission() {
		setText("Edit Mission");
		setOnAction(e ->{
			edit();
		});
	}

	private void edit() {
		VBox vbox = new VBox();
		uavMissionLog missLog = new uavMissionLog();
		ComboBox<UavMission> missions = new ComboBox<>();

		Text SelectMissiong = new Text("Select a mission to edit");
		missLog.getMissions().forEach(mission ->{
			missions.getItems().add(mission);
		});

		Text missionName = new Text("Mission Name");
		TextField editmissionName = new TextField();
		Text missionid = new Text("Mission ID");
		TextField editmissionid = new TextField();
		Text uavNumber = new Text("Number of UAV");
		TextField edituavNumber = new TextField();

		edituavNumber.textProperty().addListener( (observable, oldValue, newValue) ->{
			try {
				int x =Integer.parseInt(newValue);
				if (x <= 0) {
					edituavNumber.setText(oldValue);
				}
			}catch(Exception e) {
				edituavNumber.setText(oldValue);
			}
		});

		Button saveMission = new Button("Save UAV Mission");
		saveMission.setOnAction( saveMiss ->{
			missions.getValue().setName(editmissionName.getText());
			missions.getValue().setID(editmissionid.getText());
			missions.getValue().setNumberUAVS(Integer.parseInt(edituavNumber.getText()));
		});
		Button saveToFile = new Button("Save All Missions to File");
		saveToFile.setOnAction(e ->{
			saveMission.fire();
			ArrayList<UavMission> allmission  = new ArrayList<>();
			missions.getItems().forEach(mission ->{
				allmission.add(mission);
			});
			missLog.writeToFile(allmission);
		});

		missions.setOnAction(select ->{
			editmissionName.setText(missions.getValue().getName());
			editmissionid.setText(missions.getValue().getID());
			edituavNumber.setText(Integer.toString(missions.getValue().getNumberUAVS()));
		});
		Stage stage = new Stage();
		Button exit = new Button("Exit");
		exit.setOnAction(e ->{
			stage.close();
		});
		vbox.getChildren().add(0, SelectMissiong);
		vbox.getChildren().add(1, missions);
		vbox.getChildren().add(2, missionName);
		vbox.getChildren().add(3, editmissionName);
		vbox.getChildren().add(4, missionid);
		vbox.getChildren().add(5, editmissionid);
		vbox.getChildren().add(6, uavNumber);
		vbox.getChildren().add(7, edituavNumber);
		vbox.getChildren().add(8, saveMission);
		vbox.getChildren().add(9, saveToFile);
		vbox.getChildren().add(10, exit);

		
		stage.setTitle("Edit Selected Mission");
		stage.setScene(new Scene(vbox));
		stage.show();
	}


}
