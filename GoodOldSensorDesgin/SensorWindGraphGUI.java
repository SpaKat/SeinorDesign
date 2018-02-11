import java.util.Date;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class SensorWindGraphGUI extends SensorGraphGUI {

	public SensorWindGraphGUI(String sensorFileName) {
		super();
		setSensorFileName(sensorFileName);
		setSensorFileLoad(new WindGraphGUILoad(sensorFileName));
	}
	@SuppressWarnings("unchecked")
	@Override
	public LineChart<String, Number> setGraph() {
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Date");
		yAxis.setLabel("Value"); 
		final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
		xAxis.autosize();
		yAxis.autosize();
		lineChart.getData().addAll(setXseries(),setYseries(),setZseries());
		getData().clear();
		return lineChart;
	}
	private Series<String, Number> setXseries() {
		Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("X vector");
		getData().forEach((data) -> {
			series.getData().add(new XYChart.Data<>((new Date(data.getTime())).toString() ,((WindDataPoints) data).getX()));
		});
		return series;
	}
	private Series<String, Number> setYseries() {
		Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Y vector");
		getData().forEach((data) -> {
			series.getData().add(new XYChart.Data<>((new Date(data.getTime())).toString() ,((WindDataPoints) data).getY()));
		});
		return series;
	}
	private Series<String, Number> setZseries() {
		Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Z vector");
		getData().forEach((data) -> {
			series.getData().add(new XYChart.Data<>((new Date(data.getTime())).toString() ,((WindDataPoints) data).getZ()));
		});
		return series;
	}
}
