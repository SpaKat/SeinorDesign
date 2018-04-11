import java.util.ArrayList;

import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import javafx.application.Application;
import javafx.stage.Stage;

public class UAVMissionGUI{

	UavMission uavMission;
	private ArrayList<SensorGraphGUI> graphs = new ArrayList<>();
	public Marker marker;
	public UAVMissionGUI(UavMission uavmission, boolean offline) {
		uavMission = uavmission;
		if(!offline) {
			makeMarker();
		}
		loadGraphs();
		readyGraphs();	
	}
	private void makeMarker() {
		uavMission.loadNewGPSLocation();
		LatLong markerPositon = new LatLong(
				uavMission.getGpsLocation().getLonitude(),
				uavMission.getGpsLocation().getLatitude() 
				);
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position( markerPositon )
		.visible(Boolean.TRUE)
		.label(uavMission.getName())
		.title(uavMission.getName());
		System.out.println("\t" + uavMission.getName() );
		marker = new Marker( markerOptions );
	}

	private void loadGraphs() {
		graphs.add(new SensorGraphGUI(Names.temperture,uavMission));
		graphs.add(new SensorGraphGUI(Names.pressure,uavMission));
		graphs.add(new SensorGraphGUI(Names.Humdity,uavMission));
		graphs.add(new SensorWindGraphGUI(Names.windVector,uavMission));
		graphs.add(new UavSystemGraphGUI(Names.mavlinkSystemStatus,uavMission));
		graphs.add(new UavPostionGraphGUI(Names.mavlinkGlobalPosition,uavMission));
	}
	private void readyGraphs() {
		graphs.forEach(graph ->{
			graph.ready();
		});
	}
	public ArrayList<SensorGraphGUI> getGraphs() {
		return graphs;
	}
	public Marker getMarker() {
		return marker;
	}
	public void showGraphs() {
		graphs.forEach(graph ->{
			graph.show();
		});
	} 
	public void showGraph(String graphName) {
		graphs.forEach(graph ->{
			if(graph.getSensorFileName() == graphName) {
				graph.show();
			}
		});
	}
}