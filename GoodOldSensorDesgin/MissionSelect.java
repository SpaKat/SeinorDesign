import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MissionSelect extends Menu {
	private File missionsFile;
	private String missionFileName = "missionsInfo.txt" ;
	private ArrayList<UavMission> selectThemission = new ArrayList<>();
	private Menu pastMissions = new Menu("Past Missions"); 
	private UavMission uavMission;
	
	public MissionSelect() {
		missionsFile = new File(missionFileName);
		loadMissionFile();
		setUpMissionSelectWindow();
	}
	private void loadMissionFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(missionsFile));
			String missionName;
			while ((missionName = br.readLine()) != null) {
				selectThemission.add(new UavMission(missionName));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void setUpMissionSelectWindow() {
		String selectMissionsText = "Select Mission";
		setText(selectMissionsText);
		selectThemission.forEach(mission ->{
			MenuItem menuItem = new MenuItem(mission.getName());
			menuItem.setOnAction(e ->{
				uavMission = mission;
				fire();
			});
			pastMissions.getItems().add(menuItem);
		});
		getItems().add(pastMissions);
	}
	public UavMission getUavMission() {
		return uavMission;
	}
}
