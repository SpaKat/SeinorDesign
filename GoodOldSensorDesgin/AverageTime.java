import java.util.Comparator;

public class AverageTime implements Comparator<DataPoints> {
	int limit = 1000;
	public AverageTime(int limit) {
		this.limit = limit;
	}
	@Override
	public int compare(DataPoints o1, DataPoints o2) {
		
		if(Math.abs(o1.getTime() - o2.getTime()) <= limit){
			return 0;
		}
		return 1;
	}
}