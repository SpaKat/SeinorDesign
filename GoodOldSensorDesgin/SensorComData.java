
public class SensorComData extends Thread{
	SensorCom sensorComm;
	DataFormat data;
	public SensorComData() {
		sensorComm = new SensorCom();
		sensorComm.initialize();
		data = new DataFormat();
	}

	@Override
	public void run() {
		while(true){
		//	System.out.printf("%s \t %d \n",sensorComm.getMessageString(), (System.currentTimeMillis()/1000));
			data.enterMessage(sensorComm.getMessageString());
		}
	}

	public static void main(String[] args) {
		SensorComData test= new SensorComData();
		test.start();
	}	
}

