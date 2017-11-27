import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;


public class ShowMap implements EventHandler<ActionEvent> {
	//change
	CheckMenuItem miMap;
	LayersMap layersMap;
	String mapName;
	public ShowMap(CheckMenuItem miMap, LayersMap layersMap,String mapName) {
		 this.miMap = miMap;
		 this.layersMap = layersMap;
		 this.mapName = mapName;
	}

	@Override
	public void handle(ActionEvent arg0) {
		if(miMap.isSelected()){
			layersMap.showMap(mapName);
		}else{
			layersMap.removeMap(mapName);
		}
		
	}

	
	
}
