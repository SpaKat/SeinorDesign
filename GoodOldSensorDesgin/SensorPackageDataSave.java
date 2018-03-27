import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SensorPackageDataSave extends Thread{

	private File file;
	private UavMission mission;
	private SensorDataFormat sendThedata;
	public SensorPackageDataSave(UavMission mission, File selectedFile, int UAVNumber) {
		this.mission = mission;
		this.file = selectedFile;
		sendThedata = new SensorDataFormat(UAVNumber);
		this.start();
	}
	@Override
	public void run() {
		MissionStats.missionID = mission.getID();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			sendThedata.enterMessage(br.readLine());
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		sendThedata.endMaps();
	}
}
