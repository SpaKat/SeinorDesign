import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GroundStationGUI extends Application {
	//change
	private GoogleMapGUI googleMapGUI;
	// Pane
	private BorderPane borderPane;
	// Menu stuff
	private MenuBar menuBar;							// MenuBar
	private Menu menuFile, menuHelp;		// Menus
	private MenuItem miClose, miMavProx;				// Close MenuItem or open window for mavproxy
	/** Menu item for showing/hiding charts */			// Shows/clears all charts
	private MenuItem miAbout;							// Displays info about the program
	private MenuItem miSensorComm;
	private Scene GmapScene;
	private MissionSelect missionCharts;
	private UavMission TheCurrentMission;
	private CreateNewMission createNewMission;
	private MenuItem miUAVtoMissionData;
	private Stage primaryStage;
	public GroundStationGUI() {
		makeVariables();		
		// Add menu items to respective menus
		menuFile.getItems().addAll(createNewMission,miSensorComm,miUAVtoMissionData,miMavProx, miClose);
		menuHelp.getItems().add(miAbout);
		// Add menus to menuBar
		menuBar.getMenus().addAll(menuFile, missionCharts, menuHelp);
		setTheOnAct();
		/* PUT EVERYTHING TOGETHER */
		GmapScene = new Scene(borderPane, 450, 400);
		// Add the menubar and shapes to the borderpane
		borderPane.setTop(menuBar);
	}
	private void setTheOnAct() {
		miSensorComm.setOnAction(new SensorCommInterface());
		miAbout.setOnAction(e -> new GUIshowAbout());
		miMavProx.setOnAction(e -> new GUIshowMavProx());
		miClose.setOnAction(e -> Platform.exit());
		missionCharts.setOnAction(e -> {
			setTheCurrentMission(missionCharts.getUavMission());
			MissionStats.missionID =missionCharts.getUavMission().getID();
			googleMapGUI = new GoogleMapGUI(TheCurrentMission);
			borderPane.setCenter(googleMapGUI.getMapView());
		});
		miUAVtoMissionData.setOnAction(e->{
			((UAVtoMissionData) miUAVtoMissionData).runMissionData();
		});
	}
	private void makeVariables() {
		// Create the BorderPane
		borderPane = new BorderPane();
		/* MENU CREATION */
		// Create MenuItems
		miClose = new MenuItem("Close");
		miMavProx= new MenuItem("MavProxy");
		miSensorComm = new MenuItem("Sensor Communication");
		miAbout = new MenuItem("About...");	
		// Create Menus
		menuFile = new Menu("File");
		missionCharts = new MissionSelect();
		menuHelp = new Menu("Help");		
		// Create MenuBar
		menuBar = new MenuBar();
		createNewMission = new CreateNewMission(missionCharts);
		primaryStage = new Stage();
		miUAVtoMissionData = new UAVtoMissionData();
	}
	@Override
	public void start(Stage stage) throws Exception {
		// Configure and display the stage
		primaryStage.setScene(GmapScene);
		primaryStage.setTitle("UAV Weather Tool");
		primaryStage.show();
	}
	public static void main(String[] args) {
		GroundStationGUI.launch(args);
	}
	public void setTheCurrentMission(UavMission theCurrentMission) {
		TheCurrentMission = theCurrentMission;
	}
}