/*The CreateNewMission file asks the user for a mission name and ID and creates and saves the user input*/
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateNewMission extends MenuItem {
	
	MissionSelect missionCharts;

	public CreateNewMission(MissionSelect missionCharts) {
		this.missionCharts = missionCharts;
		setText("Create Mission");
		setOnAction(newMission ->{
			createMission(missionCharts.getSelectThemission());
		});	}

	private void createMission(ArrayList<UavMission> selectThemission) {
		Stage stage = new Stage();
		stage.setTitle("Create Mission");
		VBox vbox = new VBox();
		
		Text askForMissionName = new Text("Enter the Mission Name");
		Text askForMissionID = new Text("Enter the Mission ID");
		
		TextField enterMissionName = new TextField();
		TextField enterMissionID = new TextField();
		
		Button enter = new Button("Enter");
		vbox.getChildren().addAll(askForMissionName,enterMissionName,askForMissionID,enterMissionID,enter);

		enter.setOnAction(ent ->{
			UavMission uavMission = new UavMission(enterMissionName.getText(), enterMissionID.getText());
			selectThemission.add(uavMission);
			missionCharts.setUavMission(uavMission);
			missionCharts.refresh();
			stage.close();
		});
		
		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.show();
	}
	
}
