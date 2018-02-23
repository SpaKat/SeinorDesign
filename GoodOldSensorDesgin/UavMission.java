import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class UavMission {
	String name;
	String ID;
	GPSLocation GpsLocation = new GPSLocation();
	public UavMission(String name) {
		String[] nameArray = name.split(",");
		setName(nameArray[0]);
		setID(nameArray[1]);
	}
	public UavMission(String missionName, String ID) {
		this.name = missionName;
		this.ID = ID;
	}

	@Override
	public String toString() {
		return getName() + " (ID:" +getID()+ ")";
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getID() {
		return ID;
	}
	public GPSLocation getGpsLocation() {
		return GpsLocation;
	}
	public void loadNewGPSLocation() {
			double avgLat =0;
			double avgLong =0;
			try {
			BufferedReader br = new BufferedReader(new FileReader(new File(ID+"_"+Names.mavlinkcommadposition+".csv")));
			String currentLine;
			
			int count = 0;
			while((currentLine = br.readLine())!= null) {
				String[] splitline = currentLine.split(",");
				if(Double.parseDouble(splitline[0]) != 0 || Double.parseDouble(splitline[1]) != 0) {
					avgLat +=Double.parseDouble(splitline[0]);
					avgLong +=Double.parseDouble(splitline[1]);
					count++;
				}
			}
			avgLat/=count;
			avgLong/=count;
			GpsLocation.setLatitude(avgLong);
			GpsLocation.setLonitude(avgLat);
			System.out.println(avgLat +"_______" + avgLong);
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
			GpsLocation = new GPSLocation();
		}
		
	}
}
