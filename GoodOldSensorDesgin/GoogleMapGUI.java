import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;

import netscape.javascript.JSObject;


public class GoogleMapGUI  implements MapComponentInitializedListener{
	//change
	private GoogleMapView mapView;
	private GoogleMap map;
	private ArrayList<UAVMissionGUI> missions = new ArrayList<UAVMissionGUI>();
	UavMission currentMission;
	public GoogleMapGUI(UavMission theCurrentMission) {
		currentMission = theCurrentMission; 
		mapView = new GoogleMapView();
		mapView.addMapInializedListener(this);
	}
	public void reloadMaps() {
		loadMaps();
	}
	public void addMission(UavMission uavmission) {
		missions.add(new UAVMissionGUI(uavmission));
	}

	private void loadMaps() {
		missions.forEach(mission ->{
			map.addUIEventHandler(mission.getMarker(), UIEventType.click, (JSObject obj) ->{
				mission.showGraphs();
			});
			map.addMarker(mission.getMarker());
		});
	}

	public GoogleMapView getMapView() {
		return mapView;
	}
	@Override
	public void mapInitialized() {
		//Set the initial properties of the map.
		MapOptions mapOptions = new MapOptions();
		mapOptions.center(new LatLong(29.2108, -81.0228))
		.mapType(MapTypeIdEnum.ROADMAP)
		//		.overviewMapControl(false)
		//		.panControl(false)
		//		.rotateControl(false)
		//		.scaleControl(false)
		//		.streetViewControl(false)
		//		.zoomControl(true)
		//		.scrollWheel(false)
		.zoom(10);
		map = mapView.createMap(mapOptions);
		addMission(currentMission);
		loadMaps();
	}
}
