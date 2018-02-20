import java.util.ArrayList;

public class MavLinkCommad extends Thread{
	private String spilt = ",";
	private String CommandName;
	private ArrayList<UavDataPoint> uavData = new ArrayList();
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
	
	
}
