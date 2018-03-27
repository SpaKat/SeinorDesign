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
		Text askForNumUAV = new Text("Enter the Number of UAVs");

		TextField enterMissionName = new TextField();
		TextField enterMissionID = new TextField();
		TextField enterNumUAV = new TextField();

		enterNumUAV.textProperty().addListener(wholeNumbers->{
			try {
				if(!enterNumUAV.getText().equals("")) {
					Integer.parseInt(enterNumUAV.getText());
					if (Integer.parseInt(enterNumUAV.getText()) == 0) {
						enterNumUAV.setText("");
					}
				}
			}catch (Exception e) {
				enterNumUAV.setText("");
			}
		});


		Button enter = new Button("Enter");
		vbox.getChildren().addAll(askForMissionName,enterMissionName,askForMissionID,enterMissionID,askForNumUAV,enterNumUAV,enter);

		enter.setOnAction(ent ->{
			UavMission uavMission = new UavMission(enterMissionName.getText(), enterMissionID.getText(),Integer.parseInt(enterNumUAV.getText()));
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
