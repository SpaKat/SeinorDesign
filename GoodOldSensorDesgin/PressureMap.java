import java.util.Random;
import java.util.Vector;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.LatLongBounds;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.shapes.Circle;
import com.lynden.gmapsfx.shapes.CircleOptions;
import com.lynden.gmapsfx.shapes.Rectangle;
import com.lynden.gmapsfx.shapes.RectangleOptions;

public class PressureMap {
	//change
	GoogleMap map;
	PressureMapLoad load;
	Vector<Circle> dataShapes = new Vector<Circle>();
	
	public PressureMap(GoogleMap map) {
		load = new PressureMapLoad();
		this.map =map;
	}

	public void loadingShapes() {
		Random rn = new Random();
		for (PressureDataPoints PdataPoints : load.getReadin().getData()) {
			LatLong centreC = new LatLong(29.2108, -81.0228);
			CircleOptions circleOptions =new CircleOptions()
					.center(centreC) 
					.radius(5) 
					.strokeColor("green") 
					.strokeWeight(2) 
					.fillColor("orange") 					.fillOpacity(0.3)
					.draggable(false)
					.editable(false);

			Circle circle = new Circle(circleOptions);
			dataShapes.add(circle);
			map.addMapShape(circle);


		}

	}

	public void removeShapes() {
		for (Circle circle : dataShapes) {
			map.removeMapShape(circle);
		}
	}

}