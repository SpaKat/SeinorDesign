import java.util.Map;
import java.util.TreeMap;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;


public class LayersMap  implements MapComponentInitializedListener{
	//change
	private GoogleMapView mapView;
	private GoogleMap map;
	private Map<String,SensorMapGUI> maps = new TreeMap<String,SensorMapGUI>();
	public LayersMap() {
		mapView = new GoogleMapView();
		mapView.addMapInializedListener(this);
		
	}
	public void reloadMaps() {
		loadMaps();
	}

	private void loadMaps() {
		maps.put("TEMP", new SensorMapGUI(map, "TEMPERATURE"));
		maps.put("PRES", new SensorMapGUI(map, "PRESSURE"));
		maps.put("HUM", new SensorMapGUI(map, "HUMIDITY"));
		maps.put("WIND", new SensorMapGUI(map, "WIND VECTOR"));
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
		loadMaps();
	}

	public void showMap(String mapName) {
		maps.forEach((name,map) -> {
			if (name.toUpperCase().equals(mapName.toUpperCase())) {
				map.loadingShapes();
			}
		});
	}

	public void removeMap(String mapName) {
		maps.forEach((name,map) -> {
			if (name.toUpperCase().equals(mapName.toUpperCase())) {
				map.removeShapes();
			}
		});
	}

	
	
}
