import java.util.ArrayList;
import java.util.Vector;
//TEMP(ID),<Value>,(GPS)<long>,(GPS)<lat>
public class SensorMap extends Thread{
	
	
	private double GPSlat = 29.1;
	private double GPSLong = -81.03;
	private SensorData sensorData;
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

	public void setGPSLong(double gPSLong) {
		GPSLong = gPSLong;
	}

	public void send(String[] messageFormat) {
		System.err.println("SENT");
	}
	public SensorData getSensorData() {
		return sensorData;
	}
}
