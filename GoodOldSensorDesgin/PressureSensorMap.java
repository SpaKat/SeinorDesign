/*The Pressure Sensor Map, it gets the correct data from a specific file and outputs the data on a map*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
// in millibars
public class PressureSensorMap extends SensorMap {

	public PressureSensorMap(SensorData sensorData, String name) {
		super(sensorData,name);
		this.start();
	}
	@Override
	public void test() {
		System.out.println("PRES Hello");
	}
	@Override
	public void run() {
		File fileData = new File(MissionStats.missionID  + "_" +getMapName() +"_DATA.csv");
		System.out.println(getMapName() + "=" + fileData.getName());
		BufferedWriter bw = null;
		while(isRunning()){	
			try{
				if (getSensorData().getData().size()>0) {
					bw = new BufferedWriter(new FileWriter(fileData, true));
					String string = getSensorData().remove();
					String[] strArray = string.split(DataFormat.SPLIT);
					if(strArray[0].toUpperCase().equals("PRES")){
						String str = strArray[0];
						for (int i = 1; i < strArray.length; str += DataFormat.SPLIT + strArray[i++]);
						try {
							bw.write(MissionStats.missionID + DataFormat.SPLIT+ System.currentTimeMillis() + DataFormat.SPLIT + str);
							System.out.println(MissionStats.missionID + DataFormat.SPLIT+ System.currentTimeMillis() + DataFormat.SPLIT + str);
							bw.newLine();
							bw.close();
						} catch (Exception e) {
						//	e.printStackTrace();
						}
					}else{
					getSensorData().addDataLine(string);
				}
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}

		}
	}
	@Override
	public void interrupt() {
		System.out.println("Pressure writing thread stopped");
		super.interrupt();
	}
}



