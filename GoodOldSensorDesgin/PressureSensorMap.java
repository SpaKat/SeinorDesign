import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class PressureSensorMap extends SensorMap {

	public PressureSensorMap(SensorData sensorData) {
		super(sensorData);
		this.start();
	}

	@Override
	public void test() {
		System.out.println("PRES Hello");
	}

	@Override
	public void send(String[] messageFormat) {
		//testign the gps
		String[] message = new String[messageFormat.length+2];
		for (int i = 0; i < messageFormat.length; i++) {
			message[i] = messageFormat[i];
		}
		// default 
		message[messageFormat.length] = getGPSLong()+"";
		message[messageFormat.length+1] = getGPSlat()+"";
		//
		getSensorData().addDataLine(message);
	}

	@Override
	public void run() {

		File pressureData = new File("PRESSURE_DATA.csv");
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(pressureData, true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(isRunning()){
			try{
			System.out.println(getSensorData().getData().size() + "\t" + getSensorData().getData().get(0)[0] +"\t" + getSensorData().getData().get(0)[1]);
		//	getSensorData().getData().clear();
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			try{
				if (!getSensorData().getData().isEmpty()) {
					String[] strArray = getSensorData().removeDataLine();
					String str = strArray[0];
					for (int i = 1; i < strArray.length; str += DataFormat.SPLIT + strArray[i++]);
					bw.write((startTime() - System.currentTimeMillis())+DataFormat.SPLIT);
					bw.write(str);
					bw.newLine();
					getSensorData().getData().clear();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	
@Override
public void interrupt() {
	System.out.println("Pressure writing thread stopped");
	super.interrupt();
}
}



