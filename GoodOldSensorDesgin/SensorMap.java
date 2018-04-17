import java.util.ArrayList;

//TEMP(ID),<Value>,(GPS)<long>,(GPS)<lat>
public class SensorMap extends Thread{

	private String mapName;
	private double GPSlat = 29.2108;
	private double GPSLong = -81.0228;
	private int UAVNumber = 0;
	private SensorData sensorData;
	private SensorDataTime time;
	private boolean running = true;
	private ArrayList<Long> offSet = new ArrayList<>();
	public SensorMap(SensorData sensorData, String mapName, int intUAVNumber,SensorDataTime time) {
		this.sensorData = sensorData;
		this.mapName = mapName; 
		this.UAVNumber = intUAVNumber;
		this.time = time;
		//this.time = StartTime;
		//System.out.println("HELLO From : " +mapName );
	}
	public long getTime() {
		return time.getTime()+getOffSet();
	}
	public void addOffSet(long offSet) {
		this.offSet.add(Long.valueOf(offSet));
	}
	public long getOffSet() {
		try {
			return offSet.remove(0);
		}catch (Exception e) {
			return 0;
		}
	}
	public String getMapName() {
		return mapName;
	}

	public void setMapName(String name) {
		this.mapName = name;
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


	public int getUAVNumber() {
		return UAVNumber;
	}

	public void setUAVNumber(int uAVNumber) {
		UAVNumber = uAVNumber;
	}
	public void setGPSLong(double gPSLong) {
		GPSLong = gPSLong;
	}

	public void send(String messageString) {
		getSensorData().addDataLine(messageString);
	}
	public synchronized SensorData getSensorData() {
		return sensorData;
	}
	
	@Override
	public void interrupt() {
		setRunning(false);
	}
}
