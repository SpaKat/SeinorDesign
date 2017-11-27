import java.io.File;


public class SensorMapGUILoad extends Thread{

	ReadInData readin;
	File file;
	public SensorMapGUILoad(String name) {
		file = new File(name + "_DATA.csv");
		System.out.println(name + "_DATA.csv");
		this.start();
	}
	
	@Override
	public void run() {
		load();
	}
	private void load(){
		readin = new ReadInData(file);
	}
	public ReadInData getReadin() {
		return readin;
	}
}

