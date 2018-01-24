
public class DataPoints{
	private String ID;
	private double sensordata;
	private double GPSLONG;
	private double GPSLAT;
	private double elevation; 
	private long time;
	public DataPoints(String ID,double sensordata,double GPSLONG,double GPSLAT) {
		this.ID = ID;
		this.sensordata = sensordata;
		this.GPSLONG = GPSLONG;
		this.GPSLAT = GPSLAT;
	}


	public String getID() {
		return ID;
	}


	public double getSensordata() {
		return sensordata;
	}


	public double getGPSLONG() {
		return GPSLONG;
	}


	public double getGPSLAT() {
		return GPSLAT;
	}

	public double getElevation() {
		return elevation;
	}

	public long getTime() {
		return time;
	}

	public void setID(String iD) {
		ID = iD;
	}


	public void setSensordata(double sensordata) {
		this.sensordata = sensordata;
	}


	public void setGPSLONG(double gPSLONG) {
		GPSLONG = gPSLONG;
	}


	public void setGPSLAT(double gPSLAT) {
		GPSLAT = gPSLAT;
	}


	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	public void setTime(long time) {
		this.time = time;
	}


}
