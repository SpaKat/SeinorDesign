import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SensorGraphGUI{
	private String sensorFileName;
	private ArrayList<SensorGraphGUILoad> sensorFileLoad;
	private ArrayList<Set<DataPoints>> AllUavData;
	private Stage stage = new Stage();
	private BorderPane borderPane = new BorderPane();
	private int limit = 10000;
	private int currentCount = 0;
	private UavMission uavMission;
	private boolean average = false;
	private  boolean add = true;
	private Label Pointderection = new Label("INFO");
	public SensorGraphGUI() {

	}
	public SensorGraphGUI(String sensorFileName, UavMission uavMission) {
		this.sensorFileName = sensorFileName;
		this.uavMission = uavMission;
		sensorFileLoad = new ArrayList<>();
		for (int i = 0; i < uavMission.getNumberUAVS(); i++) {
			sensorFileLoad.add(new SensorGraphGUILoad(sensorFileName,i,limit));
		}
		AllUavData = new ArrayList<Set<DataPoints>>();
	}
	public void ready() {
		sensorFileLoad.forEach(wait ->{
			try {
				wait.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		sensorFileLoad.forEach(data ->{
			AllUavData.add(data.getData());
		});
	}
	protected void makeSensorFileLoad() {
		for (int i = 0; i < uavMission.getNumberUAVS(); i++) {
			sensorFileLoad.add(new SensorGraphGUILoad(sensorFileName,i,limit));
		}
	} 
	public void show() {
		VBox vbox = new VBox();
		borderPane = new BorderPane();
		Slider scrollwheel = new Slider(0, 30, limit/1000);
		scrollwheel.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				limit = (int) (1000*scrollwheel.getValue());
				makeSensorFileLoad();
				ready();
				borderPane.setCenter(setGraph());
				//System.out.println(limit);
			}
		});
		scrollwheel.setSnapToTicks(true);
		scrollwheel.setShowTickMarks(true);
		scrollwheel.setShowTickLabels(true);
		scrollwheel.setMajorTickUnit(1);
		scrollwheel.setBlockIncrement(1);
		VBox control = ControlPanel(scrollwheel);
		vbox.getChildren().addAll(scrollwheel,control,Pointderection);
		
		borderPane.setBottom(vbox);
		borderPane.setCenter(setGraph());
		Scene scene = new Scene(borderPane);
		stage.setScene(scene);
		stage.setTitle(sensorFileName);
		stage.show();
	}
	protected VBox ControlPanel(Slider scrollwheel) {
		VBox control = new VBox();
		CheckBox toggleAVG = new CheckBox("Toggle Average");
		toggleAVG.selectedProperty().addListener((obs,oldval, newval)->{
			average = newval;
			limit = (int) (1000*scrollwheel.getValue());
			makeSensorFileLoad();
			ready();
			borderPane.setCenter(setGraph());
			System.out.println(limit);
		});
		control.getChildren().add(toggleAVG);
		return control;
	}

	public LineChart<String, Number> setGraph() {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
		
		xAxis.setLabel("Date & Time");
		yAxis.setLabel("Value"); // enter via constructor
		lineChart.setStyle("-fx-background-color:  transparent;-fx-text-fill: #4682b4;\r\n" + 
				"  -fx-font-size: 14;");
		
		if (average) {
			final Series<String, Number> series = new XYChart.Series<String, Number>();
			
			series.setName("Avg of " + uavMission.getNumberUAVS() + " UAVs");
			ArrayList<DataPoints> avgData = new ArrayList<>();
			AllUavData.forEach(Uavdata->{
				Uavdata.forEach((datapoint) -> {
					if(avgData.isEmpty()) {
						avgData.add(datapoint);
					}
					add = true;
					avgData.forEach(temp ->{
						if(Math.abs(temp.getTime() - datapoint.getTime())<=limit ) {
							add = false;
							temp.setSensordata((temp.getSensordata() + datapoint.getSensordata())/2);
						}
					});
					if (add) {
						avgData.add(datapoint);
					}
					
				});
			});
			avgData.forEach(datapoint ->{
				System.out.println(datapoint.getTime() + "_"+ datapoint.getSensordata());
				series.getData().add(new XYChart.Data<>(new Date(datapoint.getTime()).toString(),datapoint.getSensordata()));
			});
			avgData.clear();
			lineChart.getData().add(series);
			
		}else {
			AllUavData.forEach(Uavdata->{
				final Series<String, Number> series = new XYChart.Series<String, Number>();
				series.setName("UAV " + AllUavData.indexOf(Uavdata));
				Uavdata.forEach((datapoint) -> {
					series.getData().add(new XYChart.Data<>(new Date(datapoint.getTime()).toString(),datapoint.getSensordata()));
				});
				lineChart.getData().add(series);
			});
		}
		
		lineChart.getData().forEach(series ->{
			series.getData().forEach(node ->{
				final String info  = "Time: " +node.getXValue()+ "\nSensorValue: "+node.getYValue().doubleValue();
				Tooltip.install(node.getNode(), new Tooltip(info));
				node.getNode().setOnMouseClicked(click ->{
					Pointderection.setText(info);
				});
			});
		});
		xAxis.autosize();
		yAxis.autosize();
		AllUavData.clear();
		sensorFileLoad.clear();
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
	public void setSensorFileName(String sensorFileName) {
		this.sensorFileName = sensorFileName;
	}
	public ArrayList<SensorGraphGUILoad> getSensorFileLoad() {
		return sensorFileLoad;
	}
	public ArrayList<Set<DataPoints>> getAllUavData() {
		return AllUavData;
	}
	public Stage getStage() {
		return stage;
	}
	public BorderPane getBorderPane() {
		return borderPane;
	}
	public UavMission getUavMission() {
		return uavMission;
	}
	public boolean isAverage() {
		return average;
	}
	public boolean isAdd() {
		return add;
	}
	public void setSensorFileLoad(ArrayList<SensorGraphGUILoad> sensorFileLoad) {
		this.sensorFileLoad = sensorFileLoad;
	}
	public void setAllUavData(ArrayList<Set<DataPoints>> allUavData) {
		AllUavData = allUavData;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public void setBorderPane(BorderPane borderPane) {
		this.borderPane = borderPane;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public void setUavMission(UavMission uavMission) {
		this.uavMission = uavMission;
	}
	public void setAverage(boolean average) {
		this.average = average;
	}
	public void setAdd(boolean add) {
		this.add = add;
	}
	public Label getPointderection() {
		return Pointderection;
	}
	public void setPointderection(Label pointderection) {
		Pointderection = pointderection;
	}
}

