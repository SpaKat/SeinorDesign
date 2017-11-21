import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Stream;

public class SensorData {
	private Set<String> dataCheck = new TreeSet<String>();
	private Set<String> data = new TreeSet<String>();


	public synchronized void addDataLine(String messageFormat){
		if (!dataCheck.contains(messageFormat)) {
			dataCheck.add(messageFormat);
			data.add(messageFormat);
		}
	}
	public synchronized Set<String> getData() {
		return data;
	}
	public synchronized void remove(String string) {
		data.remove(string);
	}
	public synchronized String remove() throws Exception{
		String s = ((TreeSet<String>) data).first();
		remove(s);
		return s;
	}
}
