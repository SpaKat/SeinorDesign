
public class GPSLocation {

	private double Lonitude = 29.196065;
	private double Latitude = -81.0545977;
	public GPSLocation() {
		
	}
	public double getLonitude() {
		return Lonitude;
	}
	public double getLatitude() {
		return Latitude;
	}
	public void setLonitude(double lonitude) {
		Lonitude = lonitude;
	}
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
	@Override
	public String toString() {
		return  Latitude +","+ Lonitude;
	}
}
