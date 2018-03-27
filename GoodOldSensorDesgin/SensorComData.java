/*Pulls data from the sensor */
public class SensorComData extends Thread{
	SensorCom sensorComm;
	SensorDataFormat data;
	private boolean running = true;
	public SensorComData() {
		sensorComm = new SensorCom();
		sensorComm.initialize();
		data = new SensorDataFormat(0);
	}

	public SensorComData(SensorCom sensorCom) {
		this.sensorComm = sensorCom;
		sensorComm.initialize();
		data = new SensorDataFormat(0);
	}

	public SensorComData(SensorCom sensorCom, int intUAVNumber) {
		this.sensorComm = sensorCom;
		sensorComm.initialize();
		data = new SensorDataFormat(intUAVNumber);
	}

	@Override
	public void run() {
		while(running ){
			try {
			data.enterMessage(sensorComm.getMessageString());
		//	System.out.println(sensorComm.getMessageString());
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		sensorComm.close();
	}
	@Override
	public void interrupt() {
		running = false;
		data.endMaps();
		System.out.println("SensorComData Stoped");
	}
	public static void main(String[] args) {
		SensorComData test= new SensorComData();
		test.start();
	}	
}

