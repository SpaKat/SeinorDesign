import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SensorCommInterface implements EventHandler<ActionEvent> {
	
	SensorCom sensorCom ;
	SensorComData sensorComData;
	GridPane gridpane;
	Stage stage = new Stage();
	@Override
	public void handle(ActionEvent arg0) {
			
		
		gridpane = new GridPane();
		initGridPane();
		Scene scene = new Scene(gridpane);
		
		stage.setScene(scene);
		stage.show();
	}
	private void initGridPane() {
		Text askCOMports = new Text("Enter the COM ports of the Telemerty");
		TextField enterCOMports = new TextField("COM5");
		Button start = new Button("Start");
		Button stop = new Button("Stop");
		Button exit = new Button("Exit");
		stop.setDisable(true);
		gridpane.addRow(0, askCOMports);
		gridpane.addRow(1, enterCOMports);
		gridpane.addRow(2, start);
		gridpane.addRow(2, stop);
		gridpane.addRow(2, exit);
		start.setOnAction(en -> {
			sensorCom = new SensorCom(enterCOMports.getText().trim());
			sensorComData= new SensorComData(sensorCom);
			sensorComData.start();
			start.setDisable(true);
			stop.setDisable(false);
			});
		stop.setOnAction(st ->{
			sensorComData.interrupt();
			start.setDisable(false);
			stop.setDisable(true);
		});
		exit.setOnAction(ex ->{
			sensorComData.interrupt();
			stage.close();
		});
	}
	
}
