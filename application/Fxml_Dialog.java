package application;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import verein.Spielpaarung;

public class Fxml_Dialog implements Initializable {

    @FXML
    private Button btn_confirm;

    @FXML
    private Button btn_erase;

    @FXML
    private TextField tf_tore1;

    @FXML
    private TextField tf_tore2;

    @FXML
    private Label l_verein1;

    @FXML
    private Label l_verein2;
    
    public static Spielpaarung sp;
        
    @FXML
    void confirm(ActionEvent event) {
    	Integer toreVereinLinks = null;
    	Integer toreVereinRechts = null;
    	
    	try {
    		toreVereinLinks = Integer.parseInt(this.tf_tore1.getText());
    	} catch (NumberFormatException e) {
    		//System.out.println("Tore1 ungültige Eingabe!");
    	}
    	try {
    		toreVereinRechts = Integer.parseInt(this.tf_tore2.getText());
    	} catch (NumberFormatException e) {
    		//System.out.println("Tore2 ungültige Eingabe!");
    	}
    	
    	
    	if (toreVereinLinks != null && toreVereinRechts != null && toreVereinLinks >= 0 && toreVereinRechts >= 0) {
    		Fxml_Dialog.sp.ausspielen(toreVereinLinks, toreVereinRechts);
        	((Stage) this.btn_confirm.getScene().getWindow()).close();
    	}
    }

    @FXML
    void erase(ActionEvent event) {
    	((Stage) this.btn_erase.getScene().getWindow()).close();
    }
    
    
	public Optional display() {
		try {
			VBox root = FXMLLoader.load(getClass().getResource("dialog.fxml"));
			Stage primaryStage = new Stage();
			System.out.println("stage new");
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			//primaryStage.initStyle(StageStyle.TRANSPARENT);
			//primaryStage.initStyle(StageStyle.DECORATED);
			//primaryStage.initStyle(StageStyle.UNIFIED);
			//primaryStage.initStyle(StageStyle.UTILITY);
			/*
			primaryStage.setOnCloseRequest( e -> {
				System.out.println("Render");
			});
			*/
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.ofNullable("close");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.l_verein1.setText(Fxml_Dialog.sp.getLinks().getName());
		this.l_verein2.setText(Fxml_Dialog.sp.getRechts().getName());		
	}

}
