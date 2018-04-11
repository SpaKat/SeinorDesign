import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

public class UavSystemGraphGUI extends UavGraphGUI {
	private ArrayList<Set<UavdataPointSystemStatus>> AllUavData;
	private CheckBox voltageCheckButton = new CheckBox("Voltage");
	private CheckBox currentCheckButton = new CheckBox("Current");
	private Label Pointderection = new Label() ;

	public UavSystemGraphGUI(String sensorFileName, UavMission uavMission) {
		super(sensorFileName, uavMission);
		setSensorFileName(sensorFileName);
		setUavMission(uavMission);
		setSensorFileLoad(new ArrayList<>());
		for (int i = 0; i < uavMission.getNumberUAVS(); i++) {
			getSensorFileLoad().add(new UavSystemGraphGUILoad(sensorFileName,i,getLimit()));
		}
		setAllUavData(new ArrayList<Set<DataPoints>>());
	}
	@Override
	public void ready() {
		getSensorFileLoad().forEach(wait ->{
			try {
				wait.join();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		AllUavData = new ArrayList<>();
		getSensorFileLoad().forEach(data ->{
			AllUavData.add(((UavSystemGraphGUILoad) data).data());
		});
	}
	@Override
	protected void makeSensorFileLoad() {
		for (int i = 0; i < getUavMission().getNumberUAVS(); i++) {
			getSensorFileLoad().add(new UavSystemGraphGUILoad(getSensorFileName(),i,getLimit()));
		}
	}
	@Override
	public LineChart<String, Number> setGraph() {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
		
		xAxis.setLabel("Date & Time");
		yAxis.setLabel("Value"); // enter via constructor
		lineChart.setStyle("-fx-background-color:  transparent;-fx-text-fill: #4682b4;\r\n" + 
				"  -fx-font-size: 14;");
		AllUavData.forEach(Uavdata->{
			if (voltageCheckButton.isSelected()) {
				Voltage(lineChart, Uavdata);
			}
			if (currentCheckButton.isSelected()) {
					Current(lineChart, Uavdata);
			}
		
			
		});
		lineChart.getData().forEach(series ->{
			series.getData().forEach(node ->{
				final String info  = "Time: " +node.getXValue()+ "\nValue: "+node.getYValue().doubleValue();
				Tooltip.install(node.getNode(), new Tooltip(info));
				node.getNode().setOnMouseClicked(click ->{
					Pointderection .setText(info);
				});
			});
		});
		xAxis.autosize();
		yAxis.autosize();
		AllUavData.clear();
		getSensorFileLoad().clear();
		return lineChart;
	}
	private void Voltage(LineChart<String, Number> lineChart, Set<UavdataPointSystemStatus> uavdata) {
		final Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("UAV " + AllUavData.indexOf(uavdata) + " Volage in millivolts");
		uavdata.forEach((datapoint) -> {
			series.getData().add(new XYChart.Data<>(new Date(datapoint.getTime()).toString(),datapoint.getVoltage_battery()));
		});
		lineChart.getData().add(series);
	}
	private void Current(LineChart<String, Number> lineChart, Set<UavdataPointSystemStatus> Uavdata) {
		final Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("UAV " + AllUavData.indexOf(Uavdata)+ " Current in milliamperes");
		Uavdata.forEach((datapoint) -> {
			series.getData().add(new XYChart.Data<>(new Date(datapoint.getTime()).toString(),datapoint.getCurrent_battery()));
		});
		lineChart.getData().add(series);
	}
	@Override
	protected VBox ControlPanel(Slider scrollwheel) {
		VBox vbox = new VBox();
		voltageCheckButton.setSelected(true);
		currentCheckButton.setSelected(true);
		voltageCheckButton.setOnAction(e ->{
			makeSensorFileLoad();
			ready();
			getBorderPane().setCenter(setGraph()); 
			});
		currentCheckButton.setOnAction(e ->{
			makeSensorFileLoad();
			ready();
			getBorderPane().setCenter(setGraph()); 
		});
		vbox.getChildren().addAll(voltageCheckButton,currentCheckButton,Pointderection);
		return vbox;
	}
}
