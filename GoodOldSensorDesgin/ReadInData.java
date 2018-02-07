import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class ReadInData {
	private File file;
	private Map<Long, DataPoints> data; 
	private Set<DataPoints> dataMarkers;
	
	public ReadInData(File file) {
		this.file = file;
		System.out.println("READ IN DATA: " +file.getName());
		this.data = new TreeMap<Long,DataPoints>();
		this.dataMarkers = new TreeSet<DataPoints>(new SameTime());
		load();
	}
	public Map<Long, DataPoints> getData() {
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
							strarray[0], 
							strarray[2], 
							Double.parseDouble(strarray[3])
							);
					dp.setTime(Math.abs(Long.parseLong(strarray[1])));
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
