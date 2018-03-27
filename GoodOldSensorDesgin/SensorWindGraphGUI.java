import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class SensorWindGraphGUI extends SensorGraphGUI {
	private int count = 0;
	public SensorWindGraphGUI(String sensorFileName, UavMission uavMission) {
		super();
		setSensorFileName(sensorFileName);
		setUavMission(uavMission);
		setSensorFileLoad(new ArrayList<>());
		for (int i = 0; i < uavMission.getNumberUAVS(); i++) {
			getSensorFileLoad().add(new WindGraphGUILoad(sensorFileName,i));
		}
		setAllUavData(new ArrayList<Set<DataPoints>>());
	}
	@Override
	protected void makeSensorFileLoad() {
		for (int i = 0; i < getUavMission().getNumberUAVS(); i++) {
			getSensorFileLoad().add(new SensorGraphGUILoad(getSensorFileName(),i));
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public LineChart<String, Number> setGraph() {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Date");
		yAxis.setLabel("Value"); 
		LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
		
		getAllUavData().forEach((Uavdata) -> {
			lineChart.getData().addAll(setXseries(Uavdata,count),setYseries(Uavdata,count),setZseries(Uavdata,count));
			count++;
		});
		xAxis.autosize();
		yAxis.autosize();
		
		return lineChart;
	}
	private Series<String, Number> setXseries(Set<DataPoints> uavdata, int count) {
		Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Uav " + count + "X");
		
		uavdata.forEach((data) -> {
				series.getData().add(new XYChart.Data<>((new Date(data.getTime())).toString() ,((WindDataPoints) data).getX()));
		});
		return series;
	}
	private Series<String, Number> setYseries(Set<DataPoints> uavdata, int count) {
		Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Uav " + count + "Y");
		uavdata.forEach((data) -> {
				series.getData().add(new XYChart.Data<>((new Date(data.getTime())).toString() ,((WindDataPoints) data).getY()));
			});
		
		return series;
	}
	private Series<String, Number> setZseries(Set<DataPoints> uavdata, int count) {
		Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Uav " + count + "Z");
		uavdata.forEach((data) -> {
				series.getData().add(new XYChart.Data<>((new Date(data.getTime())).toString(),((WindDataPoints) data).getZ()));
			});
		return series;
	}

}
