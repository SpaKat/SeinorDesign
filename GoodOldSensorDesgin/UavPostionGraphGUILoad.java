import java.io.File;
import java.util.Set;
import java.util.TreeSet;

public class UavPostionGraphGUILoad extends SensorGraphGUILoad {

	private Set<UavdataPointPosition> avgSet;
	public UavPostionGraphGUILoad(String sensorFileName,int uavnum,int limit) {
		super();
		setLimit(limit);
		avgSet= new TreeSet<UavdataPointPosition>(new AverageTime(getLimit()));
		setFile(new File(MissionStats.missionID +"_"+uavnum+"_" +sensorFileName + "_DATA.csv"));
		this.start();
	}
	@Override
	public void run() {
		load();
	}
	@Override
	public void load(){
		UavPostionReadInData readin = new UavPostionReadInData(getFile());
		avgSet.addAll(readin.getTheData());
	}
	public Set<UavdataPointPosition> data (){
		return avgSet;
	}
	public static void main(String[] args) {
		MissionStats.missionID ="TimeTest"; 
		new UavPostionGraphGUILoad(Names.mavlinkGlobalPosition,0,1000);
	}
}
