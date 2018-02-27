import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OfflineMapGUI extends BorderPane{
	GridPane gridPane = new GridPane();
	
	//UavMission theCurrentMission;
	//public OfflineMapGUI(UavMission theCurrentMission) {
		//this.theCurrentMission = theCurrentMission;
	//}
	public OfflineMapGUI(UavMission theCurrentMission) {
		
		Label label = new Label("You are in Offline mode, click below to view your desire");
		GridPane.setConstraints(label, 1, 0);
		
		//map buttons
		Button tempMap = new Button("Temperature");
		GridPane.setConstraints(tempMap, 3, 0);
		Button presMap = new Button("Pressure");
		Button windMap= new Button("Wind");
		Button humidMap = new Button("Humidity");
		tempMap.setText("Temperature");
		presMap.setText("Pressure");
		windMap.setText("Wind");
		humidMap.setText("Humidity");
		
		gridPane.getChildren().addAll(tempMap, presMap, windMap, humidMap, label);
	}
	
	/*public void setOnAct() {
		//tempMap.setOnAction(new EventHandler<actionevent>() {
        @Override public void handle(ActionEvent arg0) {
            
        }
    });
	}*/
	/*public void start (Stage stage) throws Exception{
		
		Scene scene = new Scene(borderPane, 500, 500);
		stage.setScene(scene);
		stage.setTitle("Offline Mode");
		stage.show();
		gridPane.setPadding(new Insets(20, 20, 20, 20));
		gridPane.setVgap(5);
		gridPane.setHgap(5);
		borderPane.setCenter(gridPane);	
		
	}*/
}

