import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class WindReadInData extends ReadInData {

	public WindReadInData(File file) {
		super(file);
	}
	@Override
	public void load() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(getFile()));
			String currentLine;
			while((currentLine = br.readLine()) != null ){
				try{
					String[] strarray = currentLine.split(SensorDataFormat.SPLIT);
					DataPoints dp = new WindDataPoints(
							strarray[0], 
							strarray[1]
							);
					((WindDataPoints) dp).setX(Double.parseDouble(strarray[3]));
					((WindDataPoints) dp).setY(Double.parseDouble(strarray[4]));
					((WindDataPoints) dp).setZ(Double.parseDouble(strarray[5]));
					dp.setTime(Math.abs(Long.parseLong(strarray[1])));
					getData().add(dp);
				}catch(Exception e){
					//System.err.println("FAILIN------------------------------------------------------" + getFile().getName());
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
