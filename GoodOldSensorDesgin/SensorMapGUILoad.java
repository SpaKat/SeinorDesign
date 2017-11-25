import java.io.File;


public class SensorMapGUILoad extends Thread{

	ReadInData readin;
	String name;
	public SensorMapGUILoad(String name) {
		this.name = name;
		this.start();
	}
	
	@Override
	public void run() {
		load();
	}
	private void load(){
		File file = new File(name + "_DATA.csv");
		System.out.println(name + "_DATA.csv");
		readin = new ReadInData(file);
	}
	public ReadInData getReadin() {
		return readin;
	}
}

