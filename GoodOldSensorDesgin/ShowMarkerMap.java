import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class ShowMarkerMap {

	Map<Integer,DataPoints> dataSameTime = new TreeMap<Integer,DataPoints>();
	String name;
	public ShowMarkerMap(Map<Integer, DataPoints> map, String name) {
		dataSameTime.putAll(map);
		this.name = name;
		buildStage();
	}

	private void buildStage() {
		sortData();
		Scene scene = new Scene(setGraph());
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle(name);
		stage.show();
	}

	private void sortData() {
		System.out.println(dataSameTime.size());
	}

	@SuppressWarnings("deprecation")
	private LineChart<Number,Number> setGraph() {
		
		final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        
        xAxis.setLabel("Time");
        yAxis.setLabel("Value"); // enter via constructor
     
        
        
        Series<Number, Number> series = new XYChart.Series<Number, Number>();
        
        series.setName("Trend");
        
        dataSameTime.forEach((time,data) -> {
			series.getData().add(new XYChart.Data<>(time ,data.getSensordata()));
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
