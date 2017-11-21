import java.util.Comparator;


public class SameTime implements Comparator<DataPoints> {

	@Override
	public int compare(DataPoints o1, DataPoints o2) {
		return Long.compare(o1.getTime(), o2.getTime());
	}
}

class AverageTime implements Comparator<DataPoints> {

	@Override
	public int compare(DataPoints o1, DataPoints o2) {
		int limit = 250;
		if(Math.abs(o1.getTime() - o2.getTime()) <= limit){
			return 0;
		}
		return 1;
	}
}
