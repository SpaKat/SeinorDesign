import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;


public class ShowMap implements EventHandler<ActionEvent> {
	//change
	CheckMenuItem miMap;
	LayersMap layersMap;
	
	public ShowMap(CheckMenuItem miMap, LayersMap layersMap) {
		 this.miMap = miMap;
		 this.layersMap = layersMap;
	}

	@Override
	public void handle(ActionEvent arg0) {
		if(miMap.isSelected()){
			layersMap.showPressureMap();
		}else{
			layersMap.removePressureMap();
		}
		
	}

	
	
}
