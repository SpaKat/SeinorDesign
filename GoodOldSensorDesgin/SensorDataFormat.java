/*Formats the data and puts the data onto the hashmap*/
import java.util.Map;
import java.util.TreeMap;

public class SensorDataFormat {
	public static final String SPLIT = ",";
	private SensorData sensorData = new SensorData();
	private Map<String,SensorMap> sensorMap = new TreeMap<String,SensorMap>();
	
	public SensorDataFormat(int intUAVNumber) {
		setMaps(intUAVNumber);
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
	private void setMaps(int intUAVNumber) {
		// adds the key of the Map and object of the map to the hashmap 
		sensorMap.put(Names.tempertureMapname, new TempertureSensorMap(sensorData,Names.temperture,intUAVNumber));
		sensorMap.put(Names.HumdityMapname, new HumiditySensorMap(sensorData, Names.Humdity,intUAVNumber));
		sensorMap.put(Names.pressureMapname, new PressureSensorMap(sensorData,Names.pressure,intUAVNumber));
		sensorMap.put(Names.windVectorMapname, new WindVectorSensorMap(sensorData, Names.windVector,intUAVNumber));
		sensorMap.put(Names.mavlinkGlobalPositionMapName, new MavLinkgpsSensorMap(sensorData, Names.mavlinkGlobalPosition,intUAVNumber));
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
