import java.util.Comparator;

public class DataGPSComp implements Comparator<DataPoints> {

	@Override
	public int compare(DataPoints o1, DataPoints o2) {
		return Double.compare(o1.getGPSLAT(), o2.getGPSLAT()) + Double.compare(o1.getGPSLONG(), o2.getGPSLONG());
	}

}
