import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Set;
import java.util.TreeSet;

public class UavPostionReadInData extends ReadInData{
	private Set<UavdataPointPosition> data;
	public UavPostionReadInData(File file) {
		super(file);
		
	}
	@Override
	public void load() {
		data = new TreeSet<UavdataPointPosition>(new SameTime());
		try {
			BufferedReader br = new BufferedReader(new FileReader(getFile()));
			String currentLine;
			while((currentLine = br.readLine()) != null ){
				try{
					UavdataPointPosition dp = new UavdataPointPosition(currentLine);
					
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

	public Set<UavdataPointPosition> getTheData() {
		return data;
	}
	public static void main(String[] args) {
		UavPostionReadInData x = new UavPostionReadInData(new File("TimeTest_0_Global_Position_DATA.csv"));
	}
}
