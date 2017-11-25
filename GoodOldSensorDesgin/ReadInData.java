import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class ReadInData {
	private File file;
	private Map<Integer,DataPoints> data; 
	private Set<DataPoints> dataMarkers;
	
	public ReadInData(File file) {
		this.file = file;
		this.data = new TreeMap<Integer,DataPoints>();
		this.dataMarkers = new TreeSet<DataPoints>(new DataGPSComp());
		load();
	}
	public Map<Integer, DataPoints> getData() {
		return data;
	}
	public void load() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
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
					dp.setTime(Math.abs(Integer.parseInt(strarray[0])));
					data.put(dp.getTime(),dp);
				}catch(Exception e){
					System.err.println("FAILIN------------------------------------------------------");
				}
				data.forEach((time,data) -> {
					dataMarkers.add(data);
				});
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
