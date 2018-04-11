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

public class UavPostionGraphGUI extends SensorGraphGUI {

	private ArrayList<Set<UavdataPointPosition>> AllUavData;
	private CheckBox altCheckButton = new CheckBox("Altitude");
	private CheckBox relative_altCheckButton = new CheckBox("Relative Altitude");
	private Label Pointderection = new Label() ;
	public UavPostionGraphGUI(String sensorFileName, UavMission uavMission) {
		super(sensorFileName, uavMission);
		setSensorFileName(sensorFileName);
		setUavMission(uavMission);
		setSensorFileLoad(new ArrayList<>());
		for (int i = 0; i < uavMission.getNumberUAVS(); i++) {
			getSensorFileLoad().add(new UavPostionGraphGUILoad(sensorFileName,i,getLimit()));
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
			AllUavData.add(((UavPostionGraphGUILoad) data).data());
			
		});
	}
	@Override
	protected void makeSensorFileLoad() {
		for (int i = 0; i < getUavMission().getNumberUAVS(); i++) {
			getSensorFileLoad().add(new UavPostionGraphGUILoad(getSensorFileName(),i,getLimit()));
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
			if (altCheckButton.isSelected()) {
				Altitude(lineChart, Uavdata);
			}
			if (relative_altCheckButton.isSelected()) {
				Relative_alt(lineChart, Uavdata);
			}
			
		});
		lineChart.getData().forEach(series ->{
			series.getData().forEach(node ->{
				final String info  = "Time: " +node.getXValue()+ "\nValue: "+node.getYValue().doubleValue();
				Tooltip.install(node.getNode(), new Tooltip(info));
				node.getNode().setOnMouseClicked(click ->{
					Pointderection.setText(info);
				});
			});
		});
		xAxis.autosize();
		yAxis.autosize();
		AllUavData.clear();
		getSensorFileLoad().clear();
		return lineChart;
	}
	private void Altitude(LineChart<String, Number> lineChart, Set<UavdataPointPosition> uavdata) {
		final Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("UAV " + AllUavData.indexOf(uavdata) + " Altitude in meters");
		uavdata.forEach((datapoint) -> {
			series.getData().add(new XYChart.Data<>(new Date(datapoint.getTime()).toString(),datapoint.getAltitude()));
		});
		lineChart.getData().add(series);
	}
	private void Relative_alt(LineChart<String, Number> lineChart, Set<UavdataPointPosition> Uavdata) {
		final Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("UAV " + AllUavData.indexOf(Uavdata)+ " Relative_alt in meters");
		Uavdata.forEach((datapoint) -> {
			series.getData().add(new XYChart.Data<>(new Date(datapoint.getTime()).toString(),datapoint.getRelative_alt()));
		});
		lineChart.getData().add(series);
	}
	@Override
	protected VBox ControlPanel(Slider scrollwheel) {
		VBox vbox = new VBox();
		altCheckButton.setSelected(false);
		relative_altCheckButton.setSelected(true);
		altCheckButton.setOnAction(e ->{
			makeSensorFileLoad();
			ready();
			getBorderPane().setCenter(setGraph());
		});
		relative_altCheckButton.setOnAction(e ->{
			makeSensorFileLoad();
			ready();
			getBorderPane().setCenter(setGraph());
		});
		vbox.getChildren().addAll(altCheckButton,relative_altCheckButton,Pointderection);
		return vbox;
	}
}
