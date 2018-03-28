import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

public class SensorWindGraphGUI extends SensorGraphGUI {
	public int count = 0;
	public SensorWindGraphGUI(String sensorFileName, UavMission uavMission) {
		super();
		setSensorFileName(sensorFileName);
		setUavMission(uavMission);
		setSensorFileLoad(new ArrayList<>());
		for (int i = 0; i < uavMission.getNumberUAVS(); i++) {
			getSensorFileLoad().add(new WindGraphGUILoad(sensorFileName,i,getLimit()));
		}
		setAllUavData(new ArrayList<Set<DataPoints>>());
	}
	@Override
	protected void makeSensorFileLoad() {
		for (int i = 0; i < getUavMission().getNumberUAVS(); i++) {
			getSensorFileLoad().add(new WindGraphGUILoad(getSensorFileName(),i,getLimit()));
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
		count = 0;
		getAllUavData().forEach((Uavdata) -> {
			//lineChart.getData().addAll(setXseries(Uavdata,count),setYseries(Uavdata,count),setZseries(Uavdata,count));
			Series<String, Number> speedChart = setSpeedSeries(Uavdata,count);
			lineChart.getData().add(speedChart);
			setActions(speedChart,Uavdata);
			count++;
		});
		xAxis.autosize();
		yAxis.autosize();
		getSensorFileLoad().clear();
		getAllUavData().clear();
		return lineChart;
	}
	private void setActions(Series<String, Number> setSpeedSeries, Set<DataPoints> uavdata) {
		uavdata.forEach(data->{
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call()  {
					System.out.println(this.isRunning());
						setActionsPerlist(setSpeedSeries, data);
					return null;
				}
			};
			Thread th = new Thread(task);
			th.setDaemon(true);
			th.start();
		});
	}
	protected void setActionsPerlist(Series<String, Number> setSpeedSeries, DataPoints data) {
		setSpeedSeries.getData().forEach(node ->{
			double speed = windSpeed( ((WindDataPoints) data).getX(), ((WindDataPoints) data).getY(), ((WindDataPoints) data).getZ());
			if(node.getYValue().doubleValue() == speed) {

				double mag = windDirection(((WindDataPoints) data).getX(), ((WindDataPoints) data).getY(), ((WindDataPoints) data).getZ())[0];
				double angle = windDirection(((WindDataPoints) data).getX(), ((WindDataPoints) data).getY(), ((WindDataPoints) data).getZ())[1];
				angle = angle * 180 /Math.PI;
				double testang = angle;
				String heading = "";
				if(((int) angle) % 360  >0 && ((int) angle) % 360  <180 && ((int) angle) % 360  != 90) {
					heading = "N";
					if (((int) angle) % 360 > 90) {
						heading += "W";
						angle =(180-angle+270);
					}else {
						heading += "E";
						angle = 90 - angle;
					}

				}else if (((int) angle) % 360  >180 && ((int) angle) % 360  <360 && ((int) angle) % 360  != 270) {
					heading = "S";
					if (((int) angle) % 360 < 270) {
						heading += "W";
						angle = 270-angle;
					}else {
						heading += "E";
						angle = (360 - angle + 90);
					}
				}else {
					if (((int) angle) % 360  ==0 ) {
						heading = "E";
						angle = 90;
					}
					if (((int) angle) % 360  ==180) {
						heading = "W";
						angle = 270;
					}
					if(((int) angle) % 360  ==90) {
						heading = "N";
						angle = 0;
					}
					if (((int) angle) % 360  ==270) {
						heading = "S";
						angle = 180;
					}
				}
				String info = "Time: " + node.getXValue()+"\nSpeed: " + speed +"m/s\nMagitude: "+mag + "\nHeading: "+ heading +"\nAngle: "+ angle; //+ "\ntestnag: " + testang;
				Tooltip.install(node.getNode(), new Tooltip(info));
				node.getNode().setOnMouseClicked(hold ->{
					getPointderection().setText(info);
				});
			}
		});
		System.out.println("DONE ACTIONS");
	}
	@Override
	protected VBox ControlPanel(Slider scrollwheel) {
		VBox control = new VBox();
		CheckBox toggleAVG = new CheckBox("Toggle Average");
		toggleAVG.selectedProperty().addListener((obs,oldval, newval)->{
			setAverage(newval);
			setLimit((int) (1000*scrollwheel.getValue()));
			makeSensorFileLoad();
			ready();
			getBorderPane().setCenter(setGraph());
		});

		control.getChildren().addAll(toggleAVG);
		return control;
	}
	private Series<String, Number> setSpeedSeries(Set<DataPoints> uavdata, int count2) {
		Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Uav " + count + " Speed");
		uavdata.forEach((data) -> {
			double speed = windSpeed( ((WindDataPoints) data).getX(), ((WindDataPoints) data).getY(), ((WindDataPoints) data).getZ());
			Data<String, Number> node = new XYChart.Data<>((new Date(data.getTime())).toString() ,speed);
			series.getData().add(node);
		});
		return series;
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
	double windSpeed(double x,double y,double z) {
		double speed;
		/* 
		 * rho = 1.2
    # scale factor sqrt(2) estimated by measurements
    # A in differential pressure
    A = np.sqrt(2)*(np.abs(dp1) + np.abs(dp2) + np.abs(dp3))
    # A in m/s
    A = np.sqrt(2 * rho * A)*/
		double rho = 1.2;
		speed = Math.sqrt(2)*(Math.abs(x) + Math.abs(y) + Math.abs(z));
		speed = Math.sqrt(2 * rho * speed);
		return speed;
	}
	double[] windDirection(double x, double y,double z) {
		double[] MagAngle = new double[2];
		/*
		 *    # parameter for sinus curve, estimated on one measurement at 7.2 m/s
    b=0.64

    s1 = dp1+dp2
    s2 = dp2+dp3

    g1 = s2/s1
    g2 = 1/g1

    # |g1|==|g2| for omega = 3b/2
    if np.abs(g1)<(3*b/2):
        # lookup based on g1
        magn = 2*np.sqrt(1-g1+g1**2)
        w = np.pi/4+np.arctan((2*g1-1)/np.sqrt(3))-(np.sign(s1)-1)*np.pi/2
    else:
        # lookup based on g2
        w = np.pi/2-np.arctan((2*g2-1)/np.sqrt(3))-(np.sign(s2)-1)*np.pi/2*/
		double b = .64;
		double s1 = x+y;
		double s2 = y+z;
		double g1 = s2/s1;
		double g2 = 1/g1;
		if(Math.abs(g1)< (3*b/2)) {
			MagAngle[0] = 2*Math.sqrt(1-g1+g1*2);
			MagAngle[1] = Math.PI/4+Math.atan((2*g1-1)/Math.sqrt(3)) - (Math.sin(s2)-1)*Math.PI/2;
		}else {
			MagAngle[1] = Math.PI/2+Math.atan((2*g1-1)/Math.sqrt(3)) - (Math.sin(s2)-1)*Math.PI/2;
		}
		return MagAngle;
	}
}
