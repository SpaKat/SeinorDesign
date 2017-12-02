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
	SensorComData sensorComData= new SensorComData(sensorCom);
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
		TextField enterCOMports = new TextField("COM5,");
		Button enter = new Button("Enter");
		Button exit = new Button("Exit");
		
		
		gridpane.addRow(0, askCOMports);
		gridpane.addRow(1, enterCOMports);
		gridpane.addRow(2, enter);
		gridpane.addRow(2, exit);
		
		enter.setOnAction(en -> {
			sensorCom.enterPORT_NAMES(enterCOMports.getText().split(","));
			sensorComData.start();

			}
				);
		exit.setOnAction(ex ->{
			sensorComData.interrupt();
		});
	}
	
}
