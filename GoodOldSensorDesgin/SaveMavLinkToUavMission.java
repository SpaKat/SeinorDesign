import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveMavLinkToUavMission {

	public SaveMavLinkToUavMission(UavMission mission, MavLinkComLog comLog) {
		
		comLog.getMavLinkComs().forEach( (commandName,commandObject) ->{
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File(mission.getID()+"_"+commandName+".csv")));
				commandObject.getUavData().forEach(commandData ->{
					try {
						bw.write(commandData.toString());
						bw.newLine();
					} catch (IOException e) {e.printStackTrace();}
				});
				
				
				bw.close();	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	
	}

}
