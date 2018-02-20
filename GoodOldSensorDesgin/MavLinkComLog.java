import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class MavLinkComLog {
	private ReadInUavData readinData;
	private Map<String,MavLinkCommad> mavLinkComs;
	public MavLinkComLog(File selectedFile) throws InterruptedException {
		readinData = new ReadInUavData(selectedFile);
		mavlinkCommand();
		readinData.join();
		ParseData();
	}
	private void mavlinkCommand() {
		mavLinkComs = new TreeMap<>();
		mavLinkComs.put("mavlink_global_position_int_t",new MavLinkCommadPostion());
	}
	private void ParseData() {
		readinData.getReadinStrings().forEach(str ->{
			String[] strArray = str.split(",");
			try {
			mavLinkComs.get(strArray[9]).add(str);
			}catch (Exception e) {
				// handle not needed commands
			}
		});
	}
	public Map<String, MavLinkCommad> getMavLinkComs() {
		return mavLinkComs;
	}
	
}
