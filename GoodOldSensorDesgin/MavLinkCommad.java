import java.util.ArrayList;

public class MavLinkCommad extends Thread{
	private String spilt = ",";
	private String CommandName;
	private ArrayList<UavDataPoint> uavData = new ArrayList();
	private String saveId;
	
	public MavLinkCommad(String SaveID) {
		saveId = SaveID;
	}
	public String getSpilt() {
		return spilt;
	}
	public String getCommandName() {
		return CommandName;
	}
	public ArrayList<UavDataPoint> getUavData() {
		return uavData;
	}

	public void add(String str) {
		String[] strArray = str.split(",");
		CommandName =strArray[9];
	}
	public String getSaveId() {
		return saveId;
	}
}
