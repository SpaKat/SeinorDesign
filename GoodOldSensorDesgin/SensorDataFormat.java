/*Formats the data and puts the data onto the hashmap*/
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class SensorDataFormat {
	private SensorDataTime TIME = new SensorDataTime();
	public static final String SPLIT = ",";
	private ArrayList<SensorData> sensorData = new ArrayList<SensorData>();
	private Map<String,SensorMap> sensorMap = new TreeMap<String,SensorMap>();
	

	public SensorDataFormat(int intUAVNumber) {
		setMaps(intUAVNumber);
	}
	public SensorDataFormat(int uAVNumber, Date date) {
		setMaps(uAVNumber);
		TIME = new SensorDataTime(date.getTime());
		System.out.println(TIME.getTime() +" VS "+ date.getTime());
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
	public void enterMessageWithTimeoffSet(String messageString,long offSet) {
		String[] messageFormat = messageString.split(SPLIT);
		try{
			// finds map that has the same id
			sensorMap.get(messageFormat[0].toUpperCase()).addOffSet(offSet);
			sensorMap.get(messageFormat[0].toUpperCase()).send(messageString);
			
		}catch(NullPointerException e){
			//catch the starting nothing string 
		}
	}
	private void setMaps(int intUAVNumber) {
		// adds the key of the Map and object of the map to the hashmap 
		sensorMap.put(Names.tempertureMapname, new TempertureSensorMap(new SensorData(),Names.temperture,intUAVNumber, TIME));
		sensorMap.put(Names.HumdityMapname, new HumiditySensorMap(new SensorData(), Names.Humdity,intUAVNumber, TIME));
		sensorMap.put(Names.pressureMapname, new PressureSensorMap(new SensorData(),Names.pressure,intUAVNumber, TIME));
		sensorMap.put(Names.windVectorMapname, new WindVectorSensorMap(new SensorData(), Names.windVector,intUAVNumber, TIME));
		sensorMap.put(Names.mavlinkGlobalPositionMapName, new MavLinkgpsSensorMap(new SensorData(), Names.mavlinkGlobalPosition,intUAVNumber, TIME));
		sensorMap.put(Names.mavlinkSystemStatusMapName, new MavLinkSystemStatusSensorMap(new SensorData(), Names.mavlinkSystemStatus,intUAVNumber, TIME));
		sensorMap.forEach( (key,map) ->{
			sensorData.add(map.getSensorData());
		});
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
	int count;
	public boolean isDataDone() {	
		 count= sensorData.size();
		boolean b = true;
		sensorData.forEach(data ->{
			if (data.getData().size() <= 0) {
				count --;
			}
		});
		if (count <=0) {
			b = false;
		}
		return b ;
	}
}
