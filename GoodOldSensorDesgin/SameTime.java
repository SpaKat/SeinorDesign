import java.util.Comparator;


public class SameTime implements Comparator<DataPoints> {

	@Override
	public int compare(DataPoints o1, DataPoints o2) {
		if (Math.abs(o1.getTime()- o2.getTime())<(long)1000) {
			return 0;
		} else {
			return 1;
		}

	}
}


