import java.util.Vector;

public class SensorData {

	private Vector<String[]> data = new Vector<String[]>();
	
	// to be 2 more 
	public synchronized String[] removeDataLine(){
		return data.remove(0);
	}
	public synchronized void addDataLine(String[] add){
		data.add(add);
	}
	public synchronized Vector<String[]> getData() {
		return data;
	}
	
}
