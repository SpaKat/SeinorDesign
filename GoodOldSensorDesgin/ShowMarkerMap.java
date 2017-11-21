import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class ShowMarkerMap {

	Set<DataPoints> dataSameTime = new TreeSet<DataPoints>(new SameTime());
	Set<DataPoints> dataAverageTime = new TreeSet<DataPoints>(new AverageTime());
	String name;
	public ShowMarkerMap(Stream<DataPoints> filter, String name) {
		dataSameTime =  filter.collect(Collectors.toSet());
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
		dataAverageTime.addAll(dataSameTime);
		System.out.println(dataAverageTime.size());	
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
        for (DataPoints dataPoints : dataAverageTime) {
        Date date = new Date(dataPoints.getTime());
			series.getData().add(new XYChart.Data<>(date.getSeconds() ,dataPoints.getSensordata()));
		}
        xAxis.autosize();
        lineChart.getData().add(series);
        	dataSameTime.clear();
		return lineChart;
	}

	

}
