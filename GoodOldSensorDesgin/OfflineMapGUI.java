import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//DISPLAY TEXTBOX for gps location
public class OfflineMapGUI extends BorderPane{
	GridPane gridPane = new GridPane();
	public static Text DataLog = new Text("");
	DropShadow shadow = new DropShadow();

	public OfflineMapGUI(UavMission theCurrentMission) {
		UAVMissionGUI missionGUI = new UAVMissionGUI(theCurrentMission,true);

		gridPane.setStyle("-fx-background-color: #202020");
		Label label = new Label("You are in offline mode, click below to spawn maps!");
		label.setFont(new Font("Comic Sans MS", 15));
		label.setStyle("-fx-font-weight: bold; -fx-text-fill: #FFFFFF");
		label.setWrapText(true);
		GridPane.setConstraints(label, 1, 0);

		DataLog.setFont(new Font("Times New Roman", 16));
		DataLog.setStyle("-fx-fill: #FFFFFF; -fx-font-weight: bold");
		GridPane.setConstraints(DataLog, 1, 9);

		Button tempMap = new Button("Temperature");
		tempMap.setStyle("-fx-border-color:black; -fx-background-color: #202020; -fx-text-fill: #FF69B4");
		tempMap.setFont(new Font("Arial", 14));
		GridPane.setConstraints(tempMap,1,4);
		Button presMap = new Button("Pressure");
		presMap.setStyle("-fx-border-color:black;-fx-text-fill:#DC143C ;-fx-background-color: #202020");
		presMap.setFont(new Font("Arial", 14));
		GridPane.setConstraints(presMap, 1, 2);
		Button windMap= new Button("Wind");
		windMap.setStyle("-fx-border-color:black;-fx-text-fill: #87CEFA;-fx-background-color: #202020");
		windMap.setFont(new Font("Arial", 14));
		GridPane.setConstraints(windMap, 1, 3);
		Button humidMap = new Button("Humidity");
		humidMap.setStyle("-fx-border-color: black;-fx-background-color: #202020; -fx-text-fill: #B0C4DE");
		humidMap.setFont(new Font("Arial", 14));
		GridPane.setConstraints(humidMap, 1, 5);
		Button systemStat = new Button("System Status");
		systemStat.setStyle("-fx-border-color: black;-fx-background-color: #202020; -fx-text-fill:#9370DB ");
		systemStat.setFont(new Font("Arial", 14));
		GridPane.setConstraints(systemStat, 1, 6);
		Button globPos = new Button("Global Position");
		globPos.setStyle("-fx-border-color: black;-fx-background-color: #202020; -fx-text-fill:#98FB98");
		globPos.setFont(new Font("Arial", 14));
		GridPane.setConstraints(globPos, 1, 7);
		
		globPos.setText("Global Position");systemStat.setText("System Status");tempMap.setText("Temperature"); presMap.setText("Pressure"); windMap.setText("Wind"); humidMap.setText("Humidity");

		humidMap.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				humidMap.setEffect(shadow);}});
		humidMap.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				humidMap.setEffect(null);}});
		tempMap.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				tempMap.setEffect(shadow);}});
		tempMap.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				tempMap.setEffect(null);}});
		windMap.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				windMap.setEffect(shadow);}});

		windMap.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				windMap.setEffect(null);}});
		presMap.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				presMap.setEffect(shadow);}});

		presMap.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				presMap.setEffect(null);}});
		systemStat.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				systemStat.setEffect(shadow);}});
		systemStat.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				systemStat.setEffect(null);}});
		globPos.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				globPos.setEffect(shadow);}});
		globPos.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				globPos.setEffect(null);}});

		tempMap.setOnAction(e ->{
			missionGUI.showGraph(Names.temperture);});
		humidMap.setOnAction(e ->{
			missionGUI.showGraph(Names.Humdity);});
		windMap.setOnAction(e ->{
			missionGUI.showGraph(Names.windVector);});
		presMap.setOnAction(e ->{
			missionGUI.showGraph(Names.pressure);});
		globPos.setOnAction(e ->{
			missionGUI.showGraph(Names.mavlinkGlobalPosition);
		});
		systemStat.setOnAction(e ->{
			missionGUI.showGraph(Names.mavlinkSystemStatus);
		});

		gridPane.getChildren().addAll(globPos, tempMap, presMap, windMap, humidMap, label, DataLog, systemStat);
		setCenter(gridPane);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(12);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setHalignment(HPos.RIGHT);
		gridPane.getColumnConstraints().add(column1); 
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setHalignment(HPos.LEFT);
		gridPane.getColumnConstraints().add(column2); 

		humidMap.setMaxWidth(Double.MAX_VALUE);
		tempMap.setMaxWidth(Double.MAX_VALUE);
		presMap.setMaxWidth(Double.MAX_VALUE);
		windMap.setMaxWidth(Double.MAX_VALUE);
		systemStat.setMaxWidth(Double.MAX_VALUE);
		globPos.setMaxWidth(Double.MAX_VALUE);
		gridPane.setPadding(new Insets(20, 20, 20, 20));

		theCurrentMission.loadNewGPSLocation();
		OfflineMapGUI.DataLog.setText(String.format("GPS Latitude: %f\n GPS Longitude: %f\n",theCurrentMission.getGpsLocation().getLatitude(),theCurrentMission.getGpsLocation().getLonitude()));;
	}
}