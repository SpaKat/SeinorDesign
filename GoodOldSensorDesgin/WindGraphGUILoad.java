import java.io.File;
import java.util.TreeSet;

public class WindGraphGUILoad extends SensorGraphGUILoad {

	public WindGraphGUILoad(String sensorFileName,int uavnum,int limit) {
		super();
		setTempTreeSet(new TreeSet<DataPoints>(new AverageTime(limit)));
		setLimit(limit);
		setFile(new File(MissionStats.missionID +"_"+uavnum+"_" +sensorFileName + "_DATA.csv"));
		this.start();
	}
	@Override
	public void load(){
		setReadin(new WindReadInData(getFile()));
	}

}
