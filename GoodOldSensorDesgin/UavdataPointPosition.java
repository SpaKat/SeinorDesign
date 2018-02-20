
public class UavdataPointPosition extends UavDataPoint {

	GPSLocation Gps = new GPSLocation();
	double Altitude; // meters
	double relative_alt;
	public UavdataPointPosition(String str,String split) {
		String[] strArray = str.split(split);
		Gps.setLatitude(	Double.parseDouble(	strArray[13])	/	(Math.pow(10, 7)	)	);
		Gps.setLonitude(	Double.parseDouble(	strArray[15])	/	(Math.pow(10, 7)	)	);
		Altitude = Double.parseDouble(strArray[17]) / 1000;
		relative_alt = Double.parseDouble(strArray[19]) / 1000;
	}
	@Override
	public String toString() {
		return Gps + ","+Altitude+ "," + relative_alt;
	}
}
