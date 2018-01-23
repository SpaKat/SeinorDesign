import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.xa.XAResource;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class ShowMarkerMap {

	private Map<Integer,DataPoints> dataSameTime = new TreeMap<Integer,DataPoints>();
	private String name;

	public ShowMarkerMap(Map<Integer, DataPoints> map, String name) {
		dataSameTime.putAll(map);
		this.name = name;
		buildStage();
		System.out.println(name);
	}

	private void buildStage() {
		Scene scene = new Scene(setGraph());

		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle(name);
		stage.show();
	}
	private LineChart<String, Number> setGraph() {
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Date");
		final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
		
		
		xAxis.setLabel("Time in Milliseconds");
		yAxis.setLabel("Value"); // enter via constructor



		Series<String, Number> series = new XYChart.Series<String, Number>();

		series.setName("Trend");

		dataSameTime.forEach((time,data) -> {
			series.getData().add(new XYChart.Data<>((new Date(time)).toString() ,data.getSensordata()));
		});
		/*
        for (DataPoints dataPoints : dataSameTime) {
        Date date = new Date(dataPoints.getTime());
			series.getData().add(new XYChart.Data<>(date.getSeconds() ,dataPoints.getSensordata()));
		}
		 */
		xAxis.autosize();

		lineChart.getData().add(series);
		dataSameTime.clear();
		return lineChart;
	}



}
