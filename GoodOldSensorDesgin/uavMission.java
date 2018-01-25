

public class uavMission {

	
	String name;
	String ID;
	public uavMission(String name) {
		String[] nameArray = name.split(",");
		setName(nameArray[0]);
		setID(nameArray[1]);
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
	
}
