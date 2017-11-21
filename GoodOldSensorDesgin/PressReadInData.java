import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

public class PressReadInData{
	public File pressureFile;
	public Vector<DataPoints> data;

	public PressReadInData(File pressureFile) {
		this.pressureFile = pressureFile;
		this.data = new Vector<DataPoints>();
		load();
	}
	public Vector<DataPoints> getData() {
		return data;
	}
	public void load() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(pressureFile));
			String currentLine;
			int counter = 0;
			while((currentLine = br.readLine()) != null && counter++<10){
				try{
					String[] strarray = currentLine.split(DataFormat.SPLIT);
					data.add(new DataPoints(
							strarray[0], 
							Double.parseDouble(strarray[1]), 
							Double.parseDouble(strarray[2]), 
							Double.parseDouble(strarray[3])
							));
					System.out.println("WORKIN>...............................<");
				}catch(Exception e){
					System.err.println("FAILIN------------------------------------------------------");
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}