import java.io.File;
import java.util.TreeSet;

public class UavGraphGUILoad extends SensorGraphGUILoad {

	public UavGraphGUILoad(String sensorFileName,int uavnum,int limit) {
		super();
		setLimit(limit);
		setFile(new File(MissionStats.missionID +"_"+uavnum+"_" +sensorFileName + "_DATA.csv"));
		this.start();
	}
	@Override
	public void run() {
		load();
	}
}
