import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;
import java.util.TreeSet;

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
	public void send(String messageFormat) {
		//testign the gps
		String[] Format = messageFormat.split(DataFormat.SPLIT);
		String[] message = new String[Format.length+2];
		for (int i = 0; i < Format.length; i++) {
			message[i] = Format[i];
		}
		// default 
		message[Format.length] = getGPSLong()+"";
		message[Format.length+1] = getGPSlat()+"";
		//
		String str = message[0];
		for (int i = 1; i < message.length; str += DataFormat.SPLIT + message[i++]);

		getSensorData().addDataLine(str);

	}

	@Override
	public void run() {

		File pressureData = new File("PRESSURE_DATA.csv");
		BufferedWriter bw = null;
		while(isRunning()){	
			try{
				if (getSensorData().getData().size()>0) {
					bw = new BufferedWriter(new FileWriter(pressureData, true));
					String string = getSensorData().remove();
					String[] strArray = string.split(DataFormat.SPLIT);

					String str = strArray[0];
					for (int i = 1; i < strArray.length; str += DataFormat.SPLIT + strArray[i++]);
					try {
						bw.write(Math.abs((startTime() - System.currentTimeMillis())) + DataFormat.SPLIT + str);
						bw.newLine();
					//	System.out.println(Math.abs((startTime() - System.currentTimeMillis())) + DataFormat.SPLIT + str);
						bw.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
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



