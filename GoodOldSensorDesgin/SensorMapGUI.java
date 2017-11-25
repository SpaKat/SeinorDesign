import java.util.Random;
import java.util.Vector;

import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import netscape.javascript.JSObject;

public class SensorMapGUI {
	//change
	String name;
	GoogleMap map;
	SensorMapGUILoad load;
	Vector<Marker> dataMarker = new Vector<Marker>();

	public SensorMapGUI(GoogleMap map, String name) {
		this.map =map;
		this.name = name;
		load = new SensorMapGUILoad(name);
	}

	public void loadingShapes() {
		Random rn = new Random();
		for (DataPoints PdataPoints : load.getReadin().getDataMarkers()) {
			LatLong markerPositon = new LatLong(PdataPoints.getGPSLAT() + rn.nextDouble()/10000, PdataPoints.getGPSLONG()+ rn.nextDouble()/10000);
			MarkerOptions markerOptions = new MarkerOptions();

			markerOptions.position( markerPositon )
			.visible(Boolean.TRUE)
			.title(name);

			Marker marker = new Marker( markerOptions );
			map.addUIEventHandler(marker, UIEventType.click, (JSObject obj) -> 
			{
				new ShowMarkerMap(load.getReadin().getData(), name);
			});
			map.addMarker(marker);
			dataMarker.add(marker);


		}

	}

	public void removeShapes() {
		for (Marker marker : dataMarker) {
			map.removeMarker(marker);
		}
	}

}