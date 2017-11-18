import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUIshowMavProx extends Stage {
	//pane
	GridPane grid = new GridPane();
	private BorderPane pane; 
	
	public GUIshowMavProx() {
	// Add StackPane
			pane = new BorderPane();
			
	//define text field
			final TextField commandText = new TextField();
			Label label = new Label ("Enter command:");
			commandText.setPromptText("What're you waiting for?");
			commandText.setPrefColumnCount(20);
			commandText.getText();
			GridPane.setConstraints(commandText, 2, 0);
			GridPane.setConstraints(label, 1, 0);
			 
	//Defining the enter button
			Button enter = new Button("Enter");
			enter.setStyle("-fx-font: 20 Times New Roman;"); //change button color
			GridPane.setConstraints(enter, 3, 0);
			
			//Defining the Clear button
			Button clear = new Button("Clear");
			clear.setStyle("-fx-font: 20 Times New Roman;"); //change button color
			GridPane.setConstraints(clear, 4, 0);
			
			//add it all
			grid.getChildren().addAll(clear, enter, label, commandText);
			
			//Setting an action for the Clear button
			clear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			    public void handle(ActionEvent e) {
			        commandText.clear();
			      
			    }
			});
			
			// Create and display said the aforementioned pane in a new stage 	
			Scene scene = new Scene(pane, 700, 400);
			setScene(scene);
			setTitle("MavProxy");
			setResizable(false);
			show();
			//GridPane things
			grid.setPadding(new Insets(20, 20, 20, 20));
			grid.setVgap(5);
			grid.setHgap(5);
			pane.setCenter(grid);
			
	}
	//once user presses enter, call openPython class
	/*public void actionPerformed(ActionEvent e) {     
                 
         }
	    }*/
	
	 
}
