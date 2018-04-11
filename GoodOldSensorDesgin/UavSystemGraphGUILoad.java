import java.io.File;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class UavSystemGraphGUILoad extends SensorGraphGUILoad {
	private Set<UavdataPointSystemStatus> avgSet;
	public UavSystemGraphGUILoad(String sensorFileName,int uavnum,int limit) {
		super();
		setLimit(limit);
		avgSet= new TreeSet<UavdataPointSystemStatus>(new AverageTime(getLimit()));
		setFile(new File(MissionStats.missionID +"_"+uavnum+"_" +sensorFileName + "_DATA.csv"));
		this.start();
	}
	@Override
	public void run() {
		load();
	}
	@Override
	public void load(){
		UavSystemReadInData readin = new UavSystemReadInData(getFile());
		avgSet.addAll(readin.getTheData());
		
	}
	public Set<UavdataPointSystemStatus> data (){
		return avgSet;
	}
	public static void main(String[] args) {
		MissionStats.missionID ="TimeTest"; 
		new UavSystemGraphGUILoad("SS",0,1000);
	}
}
