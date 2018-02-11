import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MissionSelect extends Menu {
	private File missionsFile;
	private String missionFileName = "missionsInfo.txt" ;
	private ArrayList<UavMission> selectThemission = new ArrayList<>();
	private Menu pastMissions = new Menu("Past Missions"); 
	private UavMission uavMission;
	
	public MissionSelect() {
		String selectMissionsText = "Select Mission";
		setText(selectMissionsText);
		missionsFile = new File(missionFileName);
		loadMissionFile();
		setUpMissionSelectWindow();
	}
	public MissionSelect(Boolean load) {
		missionsFile = new File(missionFileName);
		loadMissionFile();
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
	private void SaveMissionFile() {
		try {
			BufferedWriter wr = new BufferedWriter(new FileWriter(missionsFile));
			selectThemission.forEach(mission ->{
				try {
					wr.write(mission.getName() +","+mission.getID());
					wr.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void setUpMissionSelectWindow() {
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
	public ArrayList<UavMission> getSelectThemission() {
		return selectThemission;
	}
	public void refresh() {
		getItems().clear();
		pastMissions.getItems().clear();
		setUpMissionSelectWindow();
		SaveMissionFile();
	}
	public void setUavMission(UavMission uavMission) {
		this.uavMission = uavMission;
	}
}
