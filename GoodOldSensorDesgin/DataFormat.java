import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DataFormat {
	public static final String SPLIT = ",";
	private SensorData sensorData = new SensorData();
	private Map<String,SensorMap> sensorMap = new HashMap<String,SensorMap>();
	
	public DataFormat() {
		setMaps();
	}
	public void enterMessage(String messageString) {
		String[] messageFormat = messageString.split(SPLIT);
		try{
		// find the map that has the same id
		sensorMap.get(messageFormat[0].toUpperCase()).send(messageString);
		}catch(NullPointerException e){
			//catch the starting nothing string 
		}
	}
	
	private void setMaps() {
		// adds the key of the Map and object of the map to the hashmap 
		sensorMap.put("TEMP", new TempertureSensorMap(sensorData));
		sensorMap.put("HUM", new HumiditySensorMap(sensorData));
		sensorMap.put("PRES", new PressureSensorMap(sensorData));
		sensorMap.put("WIND", new WindVectorSensorMap(sensorData));
	}
	public void endMaps(){
		sensorMap.forEach((string,sensormap) -> sensormap.interrupt());
	}
}
