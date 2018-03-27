import java.io.File;

public class WindGraphGUILoad extends SensorGraphGUILoad {

	public WindGraphGUILoad(String sensorFileName,int uavnum) {
		super();
		setFile(new File(MissionStats.missionID +"_"+uavnum+"_" +sensorFileName + "_DATA.csv"));
		this.start();
	}
	@Override
	public void load(){
		setReadin(new WindReadInData(getFile()));
	}

}
