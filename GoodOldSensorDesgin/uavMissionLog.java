import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class uavMissionLog {
	final String fileMissionLogName = "missionsInfo.txt";
	private ArrayList<UavMission> missions = new ArrayList<>();
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
	public void writeToFile(ArrayList<UavMission> missions) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileMissionLogName)));
			missions.forEach(mission ->{
				try {
					bw.write(mission.getName()+","+mission.getID()+","+mission.getNumberUAVS());
					bw.newLine();
				} catch (IOException e) {e.printStackTrace();}
			});
			bw.close();
		} catch (IOException e) {e.printStackTrace();}
	}
	public ArrayList<UavMission> getMissions() {
		return missions;
	}
}
