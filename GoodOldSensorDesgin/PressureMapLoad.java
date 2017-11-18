import java.io.File;


public class PressureMapLoad{

	PressReadInData readin;
	public PressureMapLoad() {
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

