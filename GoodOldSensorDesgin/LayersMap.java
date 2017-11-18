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
	private TempertureMap tempertureMap;// = new TempertureMap();
	private HumidityMap humidityMap;// = new HumidityMap();
	private PressureMap pressureMap;// = new PressureMap(map);
	private WindVectorMap windVectorMap;// = new WindVectorMap();
	
	public LayersMap() {
		mapView = new GoogleMapView();
		mapView.addMapInializedListener(this);
		this.setCenter(mapView);
	}

	public void showTempertureMap() {
		
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

	public TempertureMap getTempertureMap() {
		return tempertureMap;
	}

	public HumidityMap getHumidityMap() {
		return humidityMap;
	}

	public PressureMap getPressureMap() {
		return pressureMap;
	}

	public WindVectorMap getWindVectorMap() {
		return windVectorMap;
	}

	public void setTempertureMap(TempertureMap tempertureMap) {
		this.tempertureMap = tempertureMap;
	}

	public void setHumidityMap(HumidityMap humidityMap) {
		this.humidityMap = humidityMap;
	}

	public void setPressureMap(PressureMap pressureMap) {
		this.pressureMap = pressureMap;
	}

	public void setWindVectorMap(WindVectorMap windVectorMap) {
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
		//  Rectangle testrc = new Rectangle(new RectangleOptions().bounds(new LatLongBounds(new LatLong(29.2108,-81.0228), new LatLong(29.2109, -81.0229))));
		map = mapView.createMap(mapOptions);
		// map.addMapShape(testrc);
		//Add a marker to the map
		MarkerOptions markerOptions = new MarkerOptions();

		markerOptions.position( new LatLong(29.2108, -81.0228) )
		.visible(Boolean.TRUE)
		.title("My Marker");

		Marker marker = new Marker( markerOptions );
		map.addMarker(marker);
		tempertureMap = new TempertureMap(map);
		humidityMap = new HumidityMap(map);
		pressureMap = new PressureMap(map);
		windVectorMap = new WindVectorMap(map);
	}

	
}
