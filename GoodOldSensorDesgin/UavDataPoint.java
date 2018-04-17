import java.util.Date;

public class UavDataPoint extends DataPoints {

	public UavDataPoint() {
		super();
	}
	public void DateInlong(String inputdate) {
		//2018-02-11T09:35:32.731
		Date date = new Date();

		String[] day_time = inputdate.split("T");
		String[] day = day_time[0].split("-");

		date.setYear(Integer.parseInt(day[0])-1900);
		date.setMonth(Integer.parseInt(day[1]));
		date.setDate(Integer.parseInt(day[2]));

		String[] time = day_time[1].split(":");
		date.setHours(Integer.parseInt(time[0]));
		date.setMinutes(Integer.parseInt(time[1]));
		double secNmil = Double.parseDouble(time[2]);
		int sec = (int) secNmil;
		int mil =(int) ((secNmil-sec)*1000);

		date.setSeconds(sec);
		setTime(date.getTime() + mil);

	}

}
