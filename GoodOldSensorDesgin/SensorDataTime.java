import java.util.Date;

public class SensorDataTime {
	private Date date = new Date();
	boolean CurrentSystemTime;
	public SensorDataTime() {
		this.CurrentSystemTime = true;
	}
	public SensorDataTime(long time) {
		this.CurrentSystemTime = false;
		date.setTime(time);
	}
	public long getTime() {
		long time;
		if (CurrentSystemTime) {
			time = System.currentTimeMillis();
		} else {
			time = date.getTime(); 
		}
		
		return time;
	}

}
