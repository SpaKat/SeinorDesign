/*The CreateNewMission file asks the user for a mission name and ID and creates and saves the user input*/
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateNewMission extends MenuItem {
	DropShadow shadow = new DropShadow();
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
		VBox vbox = new VBox(8);
		vbox.setPrefWidth(300);
		Label askForMissionName = new Label("Enter the Mission Name:");
		askForMissionName.setFont(new Font("Comic Sans MS", 13));
		askForMissionName.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333;");
	
		Label askForMissionID = new Label("Enter the Mission ID:");
		askForMissionID.setFont(new Font("Comic Sans MS", 13));
		askForMissionID.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333;");
		
		Label askForNumUAV = new Label("Enter the Number of UAVs:");
		askForNumUAV.setFont(new Font("Comic Sans MS", 13));
		askForNumUAV.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333;");
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
		enter.addEventHandler(MouseEvent.MOUSE_ENTERED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		            enter.setEffect(shadow);}});
		    	enter.addEventHandler(MouseEvent.MOUSE_EXITED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		            enter.setEffect(null);}});
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
