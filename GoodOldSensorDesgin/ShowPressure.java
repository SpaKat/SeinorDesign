import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;


public class ShowPressure implements EventHandler<ActionEvent> {
	//change
	CheckMenuItem miPressure;
	LayersMap layersMap;
	
	public ShowPressure(CheckMenuItem miPressure, LayersMap layersMap) {
		 this.miPressure = miPressure;
		 this.layersMap = layersMap;
	}

	@Override
	public void handle(ActionEvent arg0) {
		if(miPressure.isSelected()){
			layersMap.showPressureMap();
		}else{
			layersMap.removePressureMap();
		}
		
	}

	
	
}
