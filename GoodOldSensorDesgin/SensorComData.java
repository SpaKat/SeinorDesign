
public class SensorComData extends Thread{
	SensorCom sensorComm;
	DataFormat data;
	private boolean running = true;
	public SensorComData() {
		sensorComm = new SensorCom();
		data = new DataFormat();
	}

	public SensorComData(SensorCom sensorComm) {
		this.sensorComm = sensorComm;
		data = new DataFormat();
	}
	@Override
	public void run() {
		sensorComm.open();
		while(running ){
			try {
			System.out.printf("%s \n",sensorComm.getMessageString() );
			data.enterMessage(sensorComm.getMessageString());
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
		SensorComData test= new SensorComData(new SensorCom("COM12"));
		test.start();
	}	
}

