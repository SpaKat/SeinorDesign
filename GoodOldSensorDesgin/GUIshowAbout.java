import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GUIshowAbout extends Stage {

	final String aboutText = "This program was written so we could do weather stuff and hopefully get a good grade";

	public GUIshowAbout() {
	// Create the text label
			Label aboutLabel = new Label();
			aboutLabel.setWrapText(true);
			aboutLabel.setTextAlignment(TextAlignment.CENTER);
			aboutLabel.setFont(Font.font("Comic Sans MS", 14));
			aboutLabel.setText(aboutText);

			// Add the label to a StackPane
			StackPane pane = new StackPane();
			pane.getChildren().add(aboutLabel);

			// Create and display said the aforementioned pane in a new stage 	
			Scene scene = new Scene(pane, 550, 100);
			setScene(scene);
			setTitle("About this program");
			setResizable(false);
			show();
	}
	
}
