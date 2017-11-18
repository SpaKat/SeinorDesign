
public class PressureDataPoints {
	private String ID;
	private double sensordata;
	private double GPSLONG;
	private double GPSLAT;
	
	
	public PressureDataPoints(String ID,double sensordata,double GPSLONG,double GPSLAT) {
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
	
	
}
