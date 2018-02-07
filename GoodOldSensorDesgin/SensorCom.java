import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.fazecast.jSerialComm.SerialPort;

public class SensorCom{
	SerialPort comPort = SerialPort.getCommPort("COM5");
	private boolean running = true;
	String messageString = "nothing";
	private static final char START_MESSAGE = '[';
	private static final char END_MESSAGE = ']';

	public SensorCom() {
	}
	public SensorCom(String port) {
		comPort = SerialPort.getCommPort(port);	
	}
	public void enterPORT_NAMES(String comport) {
		comPort = SerialPort.getCommPort(comport);		
	}
	public String getMessageString() throws IOException {
				BufferedInputStream input = new BufferedInputStream(comPort.getInputStream());
				String build = "";
				char startChar = ((char) input.read());
				if( startChar == START_MESSAGE){
					char nextChar = (char) input.read();
					while(nextChar != END_MESSAGE){
						build += nextChar;
						nextChar = (char) input.read();
					}
					messageString = build;
					System.out.println(messageString);
				}
				input.close();
			
		return messageString;
	}

	public static void main(String[] args) {
		SensorCom s = new SensorCom();

	}
	public void open() {
		comPort.openPort();
		comPort.setBaudRate(56700);
		
	}
	public void close() {
		comPort.closePort();
	}
}
