import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DeleteMission extends MenuItem{

	public DeleteMission() {
		setText("Delete Mission");
		setOnAction(del ->{
			delete();
		});
	}

	private void delete() {
		VBox vbox = new VBox();
		uavMissionLog missLog = new uavMissionLog();
		ComboBox<UavMission> missions = new ComboBox<>();
		Text SelectMissiong = new Text("Select a mission to Delete");
		missLog.getMissions().forEach(mission ->{
			missions.getItems().add(mission);
		});
		Stage stage = new Stage();
		Button exit = new Button("Exit");
		exit.setOnAction(e ->{
			stage.close();
		});
		Button Delete = new Button("Delete");
		Delete.setOnAction(del ->{
			missions.getItems().remove(missions.getValue());
		});
		Button saveToFile = new Button("Save All Missions to File");
		saveToFile.setOnAction(e ->{
			ArrayList<UavMission> allmission  = new ArrayList<>();
			missions.getItems().forEach(mission ->{
				allmission.add(mission);
			});
			missLog.writeToFile(allmission);
		});
		vbox.getChildren().add(0, SelectMissiong);
		vbox.getChildren().add(1, missions);
		vbox.getChildren().add(2, Delete);
		vbox.getChildren().add(3,saveToFile);
		vbox.getChildren().add(4, exit);
		stage.setTitle("Edit Selected Mission");
		stage.setScene(new Scene(vbox));
		stage.show();
	}
}
