
public class UavdataPointPosition extends UavDataPoint {

	GPSLocation Gps = new GPSLocation();
	double Altitude; // meters
	double relative_alt; 
	public UavdataPointPosition(String str,String split) {
		String[] strArray = str.split(split);
		DateInlong(strArray[0]);
		Gps.setLatitude(	Double.parseDouble(	strArray[13])	/	(Math.pow(10, 7)	)	);
		Gps.setLonitude(	Double.parseDouble(	strArray[15])	/	(Math.pow(10, 7)	)	);
		Altitude = Double.parseDouble(strArray[17]) / 1000;
		relative_alt = Double.parseDouble(strArray[19]) / 1000;
	}
	public UavdataPointPosition(String currentLine) {
		String[] strArray = currentLine.split(",");
		setTime(Long.parseLong(strArray[0]));
		Gps.setLatitude(	Double.parseDouble(	strArray[1]));
		Gps.setLonitude(	Double.parseDouble(	strArray[2]));
		Altitude = Double.parseDouble(strArray[3]);
		relative_alt = Double.parseDouble(strArray[4]);
	}
	@Override
	public String toString() {
		return getTime() +","+Gps + ","+Altitude+ "," + relative_alt;
	}
	public double getAltitude() {
		return Altitude;
	}
	public GPSLocation getGps() {
		return Gps;
	}
	public double getRelative_alt() {
		return relative_alt;
	}
}
