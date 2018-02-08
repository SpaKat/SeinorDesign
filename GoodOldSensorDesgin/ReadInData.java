import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class ReadInData {
	private File file;
	private Set<DataPoints> data; 
	
	public ReadInData(File file) {
		this.file = file;
		System.out.println("READ IN DATA: " +file.getName());
		this.data = new TreeSet<DataPoints>(new SameTime());
		load();
	}
	public Set<DataPoints> getData() {
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
					data.add(dp);
				}catch(Exception e){
					System.err.println("FAILIN------------------------------------------------------" + file.getName());
				}
			}
			br.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public void setData(Set<DataPoints> data) {
		this.data = data;
	}
}
