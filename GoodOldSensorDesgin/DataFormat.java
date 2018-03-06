/*Formats the data and puts the data onto the hashmap*/
import java.util.Map;
import java.util.TreeMap;

public class DataFormat {
	public static final String SPLIT = ",";
	private SensorData sensorData = new SensorData();
	private Map<String,SensorMap> sensorMap = new TreeMap<String,SensorMap>();
	public DataFormat() {
		setMaps();
	}
	public void enterMessage(String messageString) {
		String[] messageFormat = messageString.split(SPLIT);
		try{
			// finds map that has the same id
			sensorMap.get(messageFormat[0].toUpperCase()).send(messageString);
		}catch(NullPointerException e){
			//catch the starting nothing string 
		}
	}
	private void setMaps() {
		// adds the key of the Map and object of the map to the hashmap 
		sensorMap.put(Names.tempertureMapname, new TempertureSensorMap(sensorData,Names.temperture));
		sensorMap.put(Names.HumdityMapname, new HumiditySensorMap(sensorData, Names.Humdity));
		sensorMap.put(Names.pressureMapname, new PressureSensorMap(sensorData,Names.pressure));
		sensorMap.put(Names.windVectorMapname, new WindVectorSensorMap(sensorData, Names.windVector));
		sensorMap.put(Names.mavlinkGlobalPositionMapName, new MavLinkgpsSensorMap(sensorData, Names.mavlinkGlobalPosition));
		startMapThreads();
	}
	public void endMaps(){
		sensorMap.forEach((string,sensormap) -> sensormap.interrupt());
	}
	private void startMapThreads() {
		sensorMap.forEach((string,sensormap) -> {
			if(!sensormap.isAlive()){
				sensormap.start();
			}
		});
	}
}
