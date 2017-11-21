import java.io.File;


public class PressureMapLoad extends Thread{

	PressReadInData readin;
	public PressureMapLoad() {
		this.start();
	}
	
	@Override
	public void run() {
		load();
	}
	private void load(){
		File pressureFile = new File("PRESSURE_DATA.csv");
		readin = new PressReadInData(pressureFile);
	}
	public PressReadInData getReadin() {
		return readin;
	}
}

