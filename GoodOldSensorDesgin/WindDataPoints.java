
public class WindDataPoints extends DataPoints {
	private double x;
	private double y;
	private double z;
	
	public WindDataPoints(String missionID, String sensorID) {
		super(missionID, sensorID);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
}
