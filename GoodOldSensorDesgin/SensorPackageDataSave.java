import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

public class SensorPackageDataSave extends Thread{

	private File file;
	private UavMission mission;
	private SensorDataFormat sendThedata;
	public SensorPackageDataSave(UavMission mission, File selectedFile, int UAVNumber, Date date) {
		this.mission = mission;
		this.file = selectedFile;
		MissionStats.missionID = mission.getID();
		sendThedata = new SensorDataFormat(UAVNumber,date);
		this.start();
	}
	@Override
	public void run() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while( (line = br.readLine()) != null) {
				line = line.replace("[", "");
				line = line.replace("]", "");
				String[] strLine = line.split(",");
				for (int i = 0; i < strLine.length; i++) {
					strLine[i] = strLine[i].trim();
				}
				String newLine = "";
				for (int i = 1; i < strLine.length; i++) {
					newLine +=strLine[i];
					if(i<strLine.length-1) {
					newLine +=",";
					}
				}
			//	System.out.print(newLine);
				newLine = newLine.trim();
				//System.out.println(strLine[0] +"-------"+newLine);
				sendThedata.enterMessageWithTimeoffSet(newLine,Long.parseLong(strLine[0]));
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		while( sendThedata.isDataDone()) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				
			}
		}
		System.out.println("DONE!");
		sendThedata.endMaps();
	}
}
