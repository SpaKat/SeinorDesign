import java.util.Date;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SensorGraphGUI{
	private String sensorFileName;
	private SensorGraphGUILoad sensorFileLoad;
	private Set<DataPoints> data;
	private Stage stage = new Stage();
	private BorderPane borderPane = new BorderPane();
	private int limit = 10000;
	private int currentCount = 0;
	public SensorGraphGUI() {
		
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
		data = sensorFileLoad.getData(limit);
	}
	protected void makeSensorFileLoad() {
		sensorFileLoad = new SensorGraphGUILoad(sensorFileName);
	}
	public void show() {
		VBox vbox = new VBox();
		
		Slider scrollwheel = new Slider(0, 10, limit/1000);
		scrollwheel.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				limit = (int) (1000*scrollwheel.getValue());
				makeSensorFileLoad();
				ready();
				borderPane.setCenter(setGraph());
				System.out.println(limit);
			}


		});
		scrollwheel.setSnapToTicks(true);
		scrollwheel.setShowTickMarks(true);
		scrollwheel.setShowTickLabels(true);
		scrollwheel.setMajorTickUnit(1);
		scrollwheel.setBlockIncrement(1);
		vbox.getChildren().add(scrollwheel);
		borderPane.setBottom(vbox);
		borderPane.setCenter(setGraph());
		Scene scene = new Scene(borderPane);
		stage.setScene(scene);
		stage.setTitle(sensorFileName);
		stage.show();
	}
	public LineChart<String, Number> setGraph() {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
		xAxis.setLabel("Date");
		xAxis.setLabel("Time");
		yAxis.setLabel("Value"); // enter via constructor
		lineChart.setStyle("-fx-background-color:  transparent;-fx-text-fill: #4682b4;\r\n" + 
				"  -fx-font-size: 14;");
		Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Trend");

		data.forEach((data) -> {
			series.getData().add(new XYChart.Data<>(new Date(data.getTime()).toString(),data.getSensordata()));
		});	
		lineChart.getData().add(series);
		xAxis.autosize();
		yAxis.autosize();
		data.clear();
		return lineChart;
	}
	public int getLimit() {
		return limit;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
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

