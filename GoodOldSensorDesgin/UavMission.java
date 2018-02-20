

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
}
