import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class uavMissionLog {
	final String fileMissionLogName = "missionsInfo.txt";
	ArrayList<UavMission> missions = new ArrayList<>();
	public uavMissionLog() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileMissionLogName)));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				//String[] str = currentLine.split(",");
			//	missions.add(new UavMission(str[0],str[1]));
				missions.add(new UavMission(currentLine));
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public ArrayList<UavMission> getMissions() {
		return missions;
	}
}
