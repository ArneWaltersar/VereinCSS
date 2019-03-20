package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import verein.Func_Controller;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class Main extends Application {	
	
	public static Func_Controller fc = new Func_Controller();	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//VBox root = FXMLLoader.load(getClass().getResource("vereine2.fxml"));
			AnchorPane root = FXMLLoader.load(getClass().getResource("vereine3.fxml"));
			
			//Scene scene = new Scene(root,640,400);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Verein-App");
			primaryStage.initStyle(StageStyle.TRANSPARENT);			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
