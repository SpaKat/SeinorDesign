import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class SensorCommInterface implements EventHandler<ActionEvent> {

	SensorCom sensorCom ;
	SensorComData sensorComData;
	GridPane gridpane;
	Stage stage = new Stage();
	ComboBox<UavMission> TheUAVMissions = new ComboBox<UavMission>();
	ComboBox<String> ComPorts = new ComboBox<String>();
	ComboBox<Integer> UAVID = new ComboBox<Integer>();
	Stage primaryStage;
	@Override
	public void handle(ActionEvent arg0) {
		gridpane = new GridPane();
		TheUAVMissions.getItems().addAll(getAllMissions());
		initGridPane();
		Scene scene = new Scene(gridpane);
		stage.setScene(scene);
		stage.setTitle("Sensor Communication");
		stage.show();
	}
	private ArrayList<UavMission> getAllMissions() {
		MissionSelect selectMision = new MissionSelect(true);
		return selectMision.getSelectThemission();
	}
	private void initGridPane() {
		Text askCOMports = new Text("Enter the COM ports of the Telemetry");
		TextField enterCOMports = new TextField("COM5");
		Button start = new Button("Start");
		Button stop = new Button("Stop");
		Button exit = new Button("Exit");
		Button findcoms = new Button("Find COM Ports");
		Label ErrorText = new Label("");
		Text askUAVmission = new Text("Enter the Uav Mission");
		Text EnterUAVID = new Text("Enter the UAV ID of the select Mission");
		Text askComport = new Text("Use Find COM Ports to quickly select the comport");
		ErrorText.setTextFill(Color.RED);
		stop.setDisable(true);
		
		gridpane.addRow(0, askCOMports);
		gridpane.addRow(1, enterCOMports);
		gridpane.addRow(2, askUAVmission);
		gridpane.addRow(3, TheUAVMissions);
		gridpane.addRow(4, askComport);
		gridpane.addRow(5, ComPorts);
		gridpane.addRow(6, EnterUAVID);
		gridpane.addRow(7, UAVID);
		
		gridpane.addRow(1, start);
		gridpane.addRow(1, stop);
		gridpane.addRow(5, findcoms);
		gridpane.addRow(7, exit);
		
		gridpane.addRow(8, ErrorText);
		sensorCom = new SensorCom();
		ComPorts.minWidthProperty().bind(TheUAVMissions.minWidthProperty());
		
		TheUAVMissions.setOnAction(setUAVnums ->{
			UAVID.getItems().clear();
			for (int i = 0; i <TheUAVMissions.getValue().getNumberUAVS(); i++) {
				UAVID.getItems().add(Integer.valueOf(i));
			}
		});
		findcoms.setOnAction(getPorts ->{
			ComPorts.getItems().clear();
			sensorCom.getAllavaiablePorts().forEach(port ->{
				ComPorts.getItems().add(port);
			});
		});
		ComPorts.setOnAction(selectedPort -> {
			enterCOMports.setText(ComPorts.getValue());
		});
		findcoms.setOnAction(find ->{
			ComPorts.getItems().clear();
			sensorCom.getAllavaiablePorts().forEach(portName ->{
				ComPorts.getItems().add(portName);
			});

		});
		start.setOnAction(en -> {
			try {
				MissionStats.missionID = TheUAVMissions.getValue().getID();
				sensorCom = new SensorCom(enterCOMports.getText().trim());
				sensorComData= new SensorComData(sensorCom,UAVID.getValue().intValue());
				sensorComData.start();
				start.setDisable(true);
				stop.setDisable(false);
				ErrorText.setVisible(false);
			}catch(NullPointerException e) {
				ErrorText.setText("Enter a mission and Uav number");
				ErrorText.setVisible(true);
			}catch (Exception e) {
				ErrorText.setText("Some Error happened?");
				ErrorText.setVisible(true);
			}
		});
		stop.setOnAction(st ->{
			sensorComData.interrupt();
			start.setDisable(false);
			stop.setDisable(true);
		});
		exit.setOnAction(ex ->{
			try {
				sensorComData.interrupt();
			}catch(Exception e) {}
			stage.close();
		});
	}
}
