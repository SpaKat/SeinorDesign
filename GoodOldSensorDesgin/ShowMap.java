import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;


public class ShowMap implements EventHandler<ActionEvent> {
	//change
	CheckMenuItem miMap;
	GoogleMapGUI googleMapGUI;
	String mapName;
	public ShowMap(CheckMenuItem miMap, GoogleMapGUI googleMapGUI,String mapName) {
		 this.miMap = miMap;
		 this.googleMapGUI = googleMapGUI;
		 this.mapName = mapName;
	}

	@Override
	public void handle(ActionEvent arg0) {
		if(miMap.isSelected()){
			googleMapGUI.showMap(mapName);
		}else{
			googleMapGUI.removeMap(mapName);
		}
		
	}

	
	
}
