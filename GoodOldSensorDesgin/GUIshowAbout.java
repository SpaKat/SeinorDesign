/* the "About" section*/
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GUIshowAbout extends Stage {

	final String aboutText = "This program is intended to serve as a ground station to UAV. Functionality includes saving UAV/sensor data, creating/opening/saving missions, connecting to Mavlink, and more. This program is property of the UAV Storm Chasers. ";

	public GUIshowAbout() {
			Label aboutLabel = new Label();		//create new label
			aboutLabel.setWrapText(true);
			aboutLabel.setTextAlignment(TextAlignment.CENTER);
			aboutLabel.setFont(Font.font("Comic Sans MS", 14));
			aboutLabel.setText(aboutText);
			StackPane pane = new StackPane();
			pane.getChildren().add(aboutLabel);		// Adds label to a StackPane
			Scene scene = new Scene(pane, 550, 100);		// Create and display aforementioned pane in a new stage 
			setScene(scene);
			setTitle("About this program");
			setResizable(false);
			show();
	}
	
}
