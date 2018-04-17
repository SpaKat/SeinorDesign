/*Calls humidity data and prints it onto the humidity map*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class HumiditySensorMap extends SensorMap {

	public HumiditySensorMap(SensorData sensorData, String name, int intUAVNumber,SensorDataTime time) {
		super(sensorData,name,intUAVNumber, time);
		this.start();
	}
	@Override
	public void test() {
		System.out.println("HUM Hello");
	}
	@Override
	public void run() {
		File fileData = new File(MissionStats.missionID  +"_"+getUAVNumber()+"_" +getMapName() +"_DATA.csv");
		System.out.println(getMapName() + "=" + fileData.getName());
		BufferedWriter bw = null;
		while(isRunning()){	
			try{
				if (getSensorData().getData().size()>0) {
					bw = new BufferedWriter(new FileWriter(fileData, true));
					String string = getSensorData().remove();					
					String[] strArray = string.split(SensorDataFormat.SPLIT);
					if(strArray[0].toUpperCase().equals(Names.HumdityMapname)){
						String str = strArray[0];
						for (int i = 1; i < strArray.length; str += SensorDataFormat.SPLIT + strArray[i++]);
						try {
							bw.write(getUAVNumber() + SensorDataFormat.SPLIT+ getTime() + SensorDataFormat.SPLIT + str);
							System.out.println(getUAVNumber() + SensorDataFormat.SPLIT+ getTime() + SensorDataFormat.SPLIT + str);
							bw.newLine();
							bw.close();
						} catch (Exception e) {
							//e.printStackTrace();
						}
					}else{
					getSensorData().addDataLine(string);
				}
				}
			} catch (Exception e) {
			//	e.printStackTrace();
			}
		}
	}
	@Override
	public void interrupt() {
		System.out.println("Humidity writing thread stopped");
		super.interrupt();
	}
}



