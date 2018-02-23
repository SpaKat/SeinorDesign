import java.util.ArrayList;

import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

public class UAVMissionGUI {

	UavMission uavMission;
	private ArrayList<SensorGraphGUI> graphs = new ArrayList<>();
	private Marker marker;
	public UAVMissionGUI(UavMission uavmission) {
		uavMission = uavmission;
		makeMarker();
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
		graphs.add(new SensorGraphGUI(Names.temperture));
		graphs.add(new SensorGraphGUI(Names.pressure));
		graphs.add(new SensorGraphGUI(Names.Humdity));
		graphs.add(new SensorWindGraphGUI(Names.windVector));
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
}