/*Creates data variables and returns them*/
public class DataPoints{
	private String sensorID;
	private String missionID;
	private double sensordata;
	private long time;
	
	public DataPoints(String missionID,String sensorID,double sensordata) {
		this.missionID = missionID;
		this.sensorID = sensorID;
		this.sensordata = sensordata;
	}
	public DataPoints(String missionID,String sensorID) {
		this.missionID = missionID;
		this.sensorID = sensorID;
	}
	public DataPoints() {
		
	}
	public String getSensorID() {
		return sensorID;
	}
	public String getMissionID() {
		return missionID;
	}
	public double getSensordata() {
		return sensordata;
	}

	public long getTime() {
		return time;
	}
	public void setSensorID(String sensorID) {
		this.sensorID = sensorID;
	}
	public void setMissionID(String missionID) {
		this.missionID = missionID;
	}
	public void setSensordata(double sensordata) {
		this.sensordata = sensordata;
	}

	public void setTime(long time) {
		this.time = time;
	}


}
