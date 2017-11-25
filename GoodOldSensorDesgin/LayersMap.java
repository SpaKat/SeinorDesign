
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class LayersMap extends BorderPane  implements MapComponentInitializedListener{
	//change
	private GoogleMapView mapView;
	private GoogleMap map;
	private SensorMapGUI tempertureMap;
	private SensorMapGUI humidityMap;
	private SensorMapGUI pressureMap;
	private SensorMapGUI windVectorMap;
	
	public LayersMap() {
		mapView = new GoogleMapView();
		mapView.addMapInializedListener(this);
		this.setCenter(mapView);
	}

	public void showTempertureMap() {
		tempertureMap.loadingShapes();
	}

	public void removeTempertureMap() {
		tempertureMap.removeShapes();
	}

	public void showHumidityMap() {
		
	}

	public void showPressureMap() {
		pressureMap.loadingShapes();
	}
	public void removePressureMap() {
		pressureMap.removeShapes();
	}
	public void showWindVectorMap() {
		
	}

	public SensorMapGUI getTempertureMap() {
		return tempertureMap;
	}

	public SensorMapGUI getHumidityMap() {
		return humidityMap;
	}

	public SensorMapGUI getPressureMap() {
		return pressureMap;
	}

	public SensorMapGUI getWindVectorMap() {
		return windVectorMap;
	}

	public void setTempertureMap(SensorMapGUI tempertureMap) {
		this.tempertureMap = tempertureMap;
	}

	public void setHumidityMap(SensorMapGUI humidityMap) {
		this.humidityMap = humidityMap;
	}

	public void setPressureMap(SensorMapGUI pressureMap) {
		this.pressureMap = pressureMap;
	}

	public void setWindVectorMap(SensorMapGUI windVectorMap) {
		this.windVectorMap = windVectorMap;
	}
	
	@Override
	public void mapInitialized() {
		//Set the initial properties of the map.
		MapOptions mapOptions = new MapOptions();
		mapOptions.center(new LatLong(29.2108, -81.0228))
		.mapType(MapTypeIdEnum.ROADMAP)
		.overviewMapControl(false)
		.panControl(false)
		.rotateControl(false)
		.scaleControl(false)
		.streetViewControl(false)
		.zoomControl(true)
		.scrollWheel(false)
		.zoom(10);
		map = mapView.createMap(mapOptions);
		
		tempertureMap = new SensorMapGUI(map, "TEMPERATURE");
		humidityMap = new SensorMapGUI(map, "HUMIDITY");
		pressureMap = new SensorMapGUI(map, "PRESSURE");
		windVectorMap = new SensorMapGUI(map, "WIND VECTOR");
	}

	
}
