import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Set;
import java.util.TreeSet;

public class UavSystemReadInData  extends ReadInData{
	private Set<UavdataPointSystemStatus> data;
	public UavSystemReadInData(File file) {
		super(file);
		
	}
	@Override
	public void load() {
		data = new TreeSet<UavdataPointSystemStatus>(new SameTime());
		try {
			BufferedReader br = new BufferedReader(new FileReader(getFile()));
			String currentLine;
			while((currentLine = br.readLine()) != null ){
				try{
					UavdataPointSystemStatus dp = new UavdataPointSystemStatus(currentLine);
					data.add(dp);
					
				}catch(Exception e){
					e.printStackTrace();
					//System.err.println("FAILIN------------------------------------------------------" + file.getName());
				}
			}
			br.close();
		
		} catch (Exception e) {
			//e.printStackTrace();
		}

	}

	public Set<UavdataPointSystemStatus> getTheData() {
		return data;
	}
	public static void main(String[] args) {
		UavSystemReadInData x = new UavSystemReadInData(new File("TimeTest_0_SS_DATA.csv"));
	}
}
