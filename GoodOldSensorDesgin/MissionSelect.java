import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class MissionSelect extends VBox {

	

	File missionsFile;
	String missionFileName = "missionsInfo.txt" ;
	ComboBox<uavMission> selectThemission = new ComboBox<>();
	
	public MissionSelect() {
		
		missionsFile = new File(missionFileName);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(missionsFile));
			String missionName;
			while ((missionName = br.readLine()) != null) {
				selectThemission.getItems().add(new uavMission(missionName));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		setUpMissionSelectWindow();
	}

	private void setUpMissionSelectWindow() {
		getChildren().add(selectThemission);
	}

	
	
	
	
	
	
}
