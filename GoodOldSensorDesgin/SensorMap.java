//TEMP(ID),<Value>,(GPS)<long>,(GPS)<lat>
public class SensorMap extends Thread{


	private double GPSlat = 29.2108;
	private double GPSLong = -81.0228;
	private SensorData sensorData;
	private long time = System.currentTimeMillis();
	private boolean running = true;

	public SensorMap(SensorData sensorData) {
		this.sensorData = sensorData;
	}


	@Override
	public void run() {
		System.out.println("RUNNING");
	}

	public void test() {
		System.out.println("HELLO");
	}


	public double getGPSlat() {
		return GPSlat;
	}

	public void setGPSlat(double gPSlat) {
		GPSlat = gPSlat;
	}

	public double getGPSLong() {
		return GPSLong;
	}

	public boolean isRunning() {
		return running;
	}


	public void setRunning(boolean running) {
		this.running = running;
	}


	public void setGPSLong(double gPSLong) {
		GPSLong = gPSLong;
	}

	public void send(String[] messageFormat) {
		System.err.println("SENT");
	}
	public SensorData getSensorData() {
		return sensorData;
	}
	public long startTime() {
		return time;
	}
	@Override
	public void interrupt() {
		setRunning(false);
	}
}
