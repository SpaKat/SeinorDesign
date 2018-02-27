import java.io.File;
import java.util.Set;


public class SensorGraphGUILoad extends Thread{

	private ReadInData readin;
	private File file;
	public SensorGraphGUILoad(String name) {
		file = new File(MissionStats.missionID +"_" +name + "_DATA.csv");
		System.out.println(file.getName());
		this.start();
	}
	public SensorGraphGUILoad() {
		
	}
	
	@Override
	public void run() {
		load();
	}
	public void load(){
		readin = new ReadInData(file);
	}
	public Set<DataPoints> getData() {
		return readin.getData();
	}
	public ReadInData getReadin() {
		return readin;
	}
	public File getFile() {
		return file;
	}
	public void setReadin(ReadInData readin) {
		this.readin = readin;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
}
