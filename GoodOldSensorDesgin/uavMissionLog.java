import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class uavMissionLog {
	final String fileMissionLogName = "uavMissions.txt";
	
	ArrayList<UavMission> missions = new ArrayList<>();
	
	
	public uavMissionLog() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileMissionLogName)));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				missions.add(new UavMission(currentLine));
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	
	
	
	
}
