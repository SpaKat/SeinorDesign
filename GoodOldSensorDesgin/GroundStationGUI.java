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
	//change
	private LayersMap layersMap;
	// Pane
	private BorderPane borderPane;

	// Menu stuff
	private MenuBar menuBar;							// MenuBar
	private Menu menuFile, menuCharts, menuHelp;		// Menus
	private MenuItem miClose, miMavProx;				// Close MenuItem or open window for mavproxy
	/** Menu item for showing/hiding charts */
	private CheckMenuItem miTemp, miHumidity, miPressure, miWindVelocity;	// CheckMenuItems for each chart
	private MenuItem miShowAll, miClearAll;				// Shows/clears all charts
	private MenuItem miAbout;							// Displays info about the program
	private MenuItem miSensorComm;
	private MenuItem miRefresh;
	private MenuItem miSelectMision;
	private Scene GmapScene;
	private MissionSelect MissionSelect;
	public GroundStationGUI() {
		layersMap = new LayersMap();
		// Create the BorderPane
		borderPane = new BorderPane();
		/* MENU CREATION */
		// Create MenuItems
		miClose = new MenuItem("Close");
		miRefresh = new MenuItem("Refresh");
		miMavProx= new MenuItem("MavProxy");
		miSensorComm = new MenuItem("Sensor Communication");
		miTemp = new CheckMenuItem("Temperature");
		miHumidity = new CheckMenuItem("Humidity");
		miPressure = new CheckMenuItem("Pressure");
		miWindVelocity = new CheckMenuItem("Wind Vector");
		miShowAll = new MenuItem("Show all");
		miClearAll = new MenuItem("Clear all");
		miAbout = new MenuItem("About...");	
		miSelectMision = new MenuItem("Mission Select");
		// Create Menus
		menuFile = new Menu("File");
		menuCharts = new Menu("Charts/Graphs");
		menuHelp = new Menu("Help");		
		// Create MenuBar
		menuBar = new MenuBar();		
		// Add menu items to respective menus
		menuFile.getItems().addAll(miSelectMision,miSensorComm,miMavProx, miClose);
		menuCharts.getItems().addAll(miRefresh,miTemp,miHumidity,miPressure,miWindVelocity);
		menuHelp.getItems().add(miAbout);
		// Add menus to menuBar
		menuBar.getMenus().addAll(menuFile, menuCharts, menuHelp);
		
		
		
		miTemp.setOnAction(new ShowMap(miTemp,layersMap,"Temp"));
		miHumidity.setOnAction(new ShowMap(miHumidity,layersMap,"Hum"));
		miPressure.setOnAction(new ShowMap(miPressure,layersMap,"Pres"));
		miWindVelocity.setOnAction(new ShowMap(miWindVelocity,layersMap,"Wind"));
		miSensorComm.setOnAction(new SensorCommInterface());
		miAbout.setOnAction(e -> new GUIshowAbout());
		miMavProx.setOnAction(e -> new GUIshowMavProx());
		miClose.setOnAction(e -> Platform.exit());
		miRefresh.setOnAction(e -> {
			layersMap.reloadMaps();
		});
		/* PUT EVERYTHING TOGETHER */
		 GmapScene = new Scene(borderPane, 450, 400);
		MissionSelect =new MissionSelect();
		
		// Add the menubar and shapes to the borderpane
		borderPane.setTop(menuBar);
		borderPane.setCenter(layersMap.getMapView());

	}
	@Override
	public void start(Stage primaryStage) throws Exception {

		
		// Configure and display the stage
		primaryStage.setScene(GmapScene);
		primaryStage.setTitle("UAV Weather Tool");
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		GroundStationGUI.launch(args);
	}


}
