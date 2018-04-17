import java.util.Date;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class LoadSensorDataFromFileSetTime extends HBox {

	private Date date = new Date();
	private boolean validDate = true;
	public LoadSensorDataFromFileSetTime() {
		reset();
		setAlignment(Pos.CENTER);
	}

	private void reset() {
		getChildren().clear();
		Text dateT= new Text("Enter Start of the mission\n"
				+ "month-day-year hour:min:sec\n"
				+ "ex: 12-3-2018 15:34:30");
		TextField dateTf = new TextField("month-day-year hour:min:sec");
		dateTf.setPrefWidth(200);
		dateTf.textProperty().addListener( (obs,oldVal,newVal) ->{
			try {
				String[] day_time = newVal.split(" ");
				String[] day = day_time[0].split("-");

				date.setYear(Integer.parseInt(day[2])-1900);
				date.setMonth(Integer.parseInt(day[0]));
				date.setDate(Integer.parseInt(day[1]));

				String[] time = day_time[1].split(":");
				date.setHours(Integer.parseInt(time[0]));
				date.setMinutes(Integer.parseInt(time[1]));
				date.setSeconds(Integer.parseInt(time[2]));
				System.out.println(date.toString() +" YOSH!!!!!"+date.getTime());
				dateTf.setStyle("-fx-background-color: green");
				validDate = true;
			}catch(Exception e) {
				//date.setTime(System.currentTimeMillis());
				//System.out.println("Fuck");1609693696707
				if(validDate != false) {
					dateTf.setText("12-3-2018 15:34:30");
					dateTf.setStyle("-fx-background-color: red");
				}
				validDate = false;
			}
		});


		getChildren().addAll(dateT,dateTf);
	}
	public boolean isValidDate() {
		return validDate;
	}
	public Date getDate() {
		return date;
	}
}
