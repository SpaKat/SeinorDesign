import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadInUavData extends Thread{

	private File selectedFile;
	private ArrayList<String> readinStrings = new ArrayList<>();
	
	public ReadInUavData(File selectedFile) {
		this.selectedFile = selectedFile;
		start();
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Readin........");
			BufferedReader br = new BufferedReader(new FileReader(selectedFile));
			String str;
			while((str = br.readLine()) != null) {
				readinStrings.add(str);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<String> getReadinStrings() {
		return readinStrings;
	}
}
