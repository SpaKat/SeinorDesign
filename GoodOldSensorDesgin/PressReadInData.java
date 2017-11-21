import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Collectors;

public class PressReadInData{
	private File pressureFile;
	private Vector<DataPoints> data;
	private Set<DataPoints> dataMarkers;
	
	public PressReadInData(File pressureFile) {
		this.pressureFile = pressureFile;
		this.data = new Vector<DataPoints>();
		this.dataMarkers = new TreeSet<DataPoints>(new DataGPSComp());
		load();
	}
	public Vector<DataPoints> getData() {
		return data;
	}
	public void load() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(pressureFile));
			String currentLine;
			while((currentLine = br.readLine()) != null ){
				try{
					String[] strarray = currentLine.split(DataFormat.SPLIT);
					DataPoints dp = new DataPoints(
							strarray[1], 
							Double.parseDouble(strarray[2]), 
							Double.parseDouble(strarray[3]), 
							Double.parseDouble(strarray[4])
							);
					dp.setTime(Integer.parseInt(strarray[0]));
					data.add(dp);
					dataMarkers.add(dp);
					
					
				}catch(Exception e){
					System.err.println("FAILIN------------------------------------------------------");
				}
			}
			br.close();
			 

			System.out.println(dataMarkers.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public Set<DataPoints> getDataMarkers() {
		return dataMarkers;
	}
}