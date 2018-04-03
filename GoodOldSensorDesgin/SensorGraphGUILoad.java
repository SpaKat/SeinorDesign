import java.io.File;
import java.util.Set;
import java.util.TreeSet;


public class SensorGraphGUILoad extends Thread{

	private ReadInData readin;
	private File file;
	private int limit;
	private TreeSet<DataPoints> tempTreeSet;
	public SensorGraphGUILoad(String name,int UAVnum, int limit) {
		this.setLimit(limit);
		file = new File(MissionStats.missionID +"_"+UAVnum+"_" +name + "_DATA.csv");
		//System.out.println(file.getName());
		setTempTreeSet(new TreeSet<DataPoints>(new AverageTime(limit)));
		this.start();
	}
	
	public SensorGraphGUILoad() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		load();
		getTempTreeSet().addAll(readin.getData());
		//System.out.println("DONE");
	}
	public void load(){
		readin = new ReadInData(file);
	}
	public TreeSet<DataPoints> getData() {
		
		return getTempTreeSet();
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

	public TreeSet<DataPoints> getTempTreeSet() {
		return tempTreeSet;
	}

	public void setTempTreeSet(TreeSet<DataPoints> tempTreeSet) {
		this.tempTreeSet = tempTreeSet;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}

