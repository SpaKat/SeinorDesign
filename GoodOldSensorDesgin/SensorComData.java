
public class SensorComData extends Thread{
	SensorCom sensorComm;
	DataFormat data;
	private boolean running = true;
	public SensorComData() {
		sensorComm = new SensorCom();
		sensorComm.initialize();
		data = new DataFormat();
	}

	public SensorComData(SensorCom sensorComm) {
		this.sensorComm = sensorComm;
		sensorComm.initialize();
		data = new DataFormat();
	}
	@Override
	public void run() {
		while(running ){
			//System.out.printf("%s \n",sensorComm.getMessageString() );
			data.enterMessage(sensorComm.getMessageString());
		}
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

