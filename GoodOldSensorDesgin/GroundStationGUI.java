import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GroundStationGUI extends Application {
	private GoogleMapGUI googleMapGUI;
	private BorderPane borderPane;
	private MenuBar menuBar;							// MenuBar
	private Menu menuFile, menuHelp;					// Menus
	private MenuItem miClose, miMavProx;				// Close MenuItem or open window for mavproxy
	/** Menu item for showing/hiding charts */			// Shows/clears all charts
	private MenuItem miAbout;							// Displays info about the program
	private MenuItem miSensorComm;
	private Scene GmapScene;
	private MissionSelect missionCharts;
	private UavMission TheCurrentMission;
	private CreateNewMission createNewMission;
	private MenuItem miUAVtoMissionData;
	private MenuItem miRecoverDatafromSensorsPackage;
	private Stage primaryStage;
	private CheckMenuItem offlineMapGUI;
	
	private boolean offline = true;
	//private OfflineMapGUI offlineMapGUI;
	public GroundStationGUI() {
		makeVariables();		
		// Add menu items to respective menus
		menuFile.getItems().addAll(createNewMission,miSensorComm,miUAVtoMissionData,miRecoverDatafromSensorsPackage,miMavProx, offlineMapGUI, miClose);
		menuHelp.getItems().add(miAbout);
		menuBar.getMenus().addAll(menuFile, missionCharts, menuHelp);		// Add menus to menuBar
		setTheOnAct();
		GmapScene = new Scene(borderPane, 450, 400);						/* PUT EVERYTHING TOGETHER */
		borderPane.setTop(menuBar);											// Add the menubar and shapes to the borderpane
	}
	/*Tells buttons to do things*/
	private void setTheOnAct() {
		miSensorComm.setOnAction(new SensorCommInterface());
		miAbout.setOnAction(e -> new GUIshowAbout());
		miMavProx.setOnAction(e -> new GUIshowMavProx());
		miClose.setOnAction(e -> {
			primaryStage.close();
			Platform.exit();
		});
		offlineMapGUI.setOnAction(e -> {
			offline= !offline;
			offlineMapGUI.setSelected(offline);
		});
		missionCharts.setOnAction(e -> {
			setTheCurrentMission(missionCharts.getUavMission());
			MissionStats.missionID =missionCharts.getUavMission().getID();
			if(!offline) {
			googleMapGUI = new GoogleMapGUI(TheCurrentMission);
			borderPane.setCenter(googleMapGUI.getMapView());
			}else {
				System.out.println(TheCurrentMission);
				borderPane.setCenter(new OfflineMapGUI(TheCurrentMission));
			}
		});
		miUAVtoMissionData.setOnAction(e->{
			((UAVtoMissionData) miUAVtoMissionData).runMissionData();
		});
		miRecoverDatafromSensorsPackage.setOnAction(e->{
			LoadSensorDataFromFile recoverData = new LoadSensorDataFromFile();
		});
	}
	private void makeVariables() {
		borderPane = new BorderPane();
		/* MENU CREATION */
		miClose = new MenuItem("Close");
		miMavProx= new MenuItem("MavProxy");
		miSensorComm = new MenuItem("Sensor Communication");
		miAbout = new MenuItem("About...");
		menuFile = new Menu("File");
		missionCharts = new MissionSelect();
		menuHelp = new Menu("Help");		
		menuBar = new MenuBar();
		offlineMapGUI= new CheckMenuItem("Offline Mode");
		offlineMapGUI.setSelected(offline);
		createNewMission = new CreateNewMission(missionCharts);
		primaryStage = new Stage();
		miUAVtoMissionData = new UAVtoMissionData();
		miRecoverDatafromSensorsPackage = new MenuItem("Load from a Sd card");
	}
	@Override
	// Configure and display stage
	public void start(Stage stage) throws Exception {
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