import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SensorCommInterface implements EventHandler<ActionEvent> {
	
	SensorCom sensorCom = new SensorCom();
	GridPane gridpane;
	@Override
	public void handle(ActionEvent arg0) {
			
		Stage stage = new Stage();
		gridpane = new GridPane();
		initGridPane();
		Scene scene = new Scene(gridpane);
		
		stage.setScene(scene);
		stage.show();
	}
	private void initGridPane() {
		Text askCOMports = new Text("Enter the COM ports of the Telemerty");
		TextField enterCOMports = new TextField("COM4,COM5");
		Button enter = new Button("Enter");
		
		gridpane.addRow(0, askCOMports);
		gridpane.addRow(1, enterCOMports);
		gridpane.addRow(2, enter);
	//	enter.setOnAction();
		
	}
	
}
