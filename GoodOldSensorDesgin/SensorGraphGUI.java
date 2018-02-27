import java.util.Date;
import java.util.Set;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class SensorGraphGUI {
	private String sensorFileName;
	private SensorGraphGUILoad sensorFileLoad;
	private Set<DataPoints> data;
	
	public SensorGraphGUI() {
		// TODO Auto-generated constructor stub
	}
	public SensorGraphGUI(String sensorFileName) {
		this.sensorFileName = sensorFileName;
		sensorFileLoad = new SensorGraphGUILoad(sensorFileName);
	}


	public void ready() {
		try {
			sensorFileLoad.join();
		} catch (InterruptedException e) {
			System.err.println("Sensor File Load failed " + sensorFileName + "\n");
		}
		data = sensorFileLoad.getData();
	}


	public void show() {
		Scene scene = new Scene(setGraph());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle(sensorFileName);
		stage.show();
	}
	public LineChart<String, Number> setGraph() {
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Date");
		final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
		xAxis.setLabel("Time");
		yAxis.setLabel("Value"); // enter via constructor
		Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Trend");
		data.forEach((data) -> {
			series.getData().add(new XYChart.Data<>((new Date(data.getTime())).toString() ,data.getSensordata()));
		});
		xAxis.autosize();
		lineChart.getData().add(series);
		data.clear();
		return lineChart;
	}

	public String getSensorFileName() {
		return sensorFileName;
	}
	public SensorGraphGUILoad getSensorFileLoad() {
		return sensorFileLoad;
	}
	public Set<DataPoints> getData() {
		return data;
	}
	public void setSensorFileName(String sensorFileName) {
		this.sensorFileName = sensorFileName;
	}
	public void setSensorFileLoad(SensorGraphGUILoad sensorFileLoad) {
		this.sensorFileLoad = sensorFileLoad;
	}
	public void setData(Set<DataPoints> data) {
		this.data = data;
	}
	
	
}

