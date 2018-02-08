
public class SensorComData extends Thread{
	SensorCom sensorComm;
	DataFormat data;
	private boolean running = true;
	public SensorComData() {
		sensorComm = new SensorCom();
		sensorComm.initialize();
		data = new DataFormat();
	}

	public SensorComData(SensorCom sensorCom) {
		this.sensorComm = sensorCom;
		sensorComm.initialize();
		data = new DataFormat();
	}

	@Override
	public void run() {
		while(running ){
			try {
			data.enterMessage(sensorComm.getMessageString());
			//System.out.println(sensorComm.getMessageString());
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		sensorComm.close();
	}
	@Override
	public void interrupt() {
		running = false;
		System.out.println("SensorComData Stoped");
	}
	public static void main(String[] args) {
		SensorComData test= new SensorComData();
		test.start();
	}	
}

