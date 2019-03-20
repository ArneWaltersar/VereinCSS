package application;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import verein.Spielpaarung;
import verein.Verein;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Fxml_Controller implements Initializable {
	
    @FXML
    private TextField tf_verein;

	@FXML
    private Button btn_vereine;

    @FXML
    private Button btn_paarungen;

    @FXML
    private Button btn_spielliste;

    @FXML
    private Button btn_ergebnisse;
    
    @FXML
    private Button btn_close;
    
    @FXML
    private Button btn_reset;

    @FXML
    private TextArea ta_vereine;

    @FXML
    private TextArea ta_spiele;
        
    @FXML
    private TableView<Verein> tv_verein;

    @FXML
    private TableColumn<Verein, String> tc_name;

    @FXML
    private TableColumn<Verein, Integer> tc_punkte;

    @FXML
    private TableColumn<Verein, Integer> tc_spiele;

    @FXML
    private TableColumn<Verein, Integer> tc_tore;

    @FXML
    private TableColumn<Verein, Integer> tc_ggntore;
    

    @FXML
    private TableColumn<Verein, Integer> tc_siege;

    @FXML
    private TableColumn<Verein, Integer> tc_unentschieden;

    @FXML
    private TableColumn<Verein, Integer> tc_niederlagen;

    @FXML
    private TableColumn<Verein, Integer> tc_tordiff;

    @FXML
    private TableColumn<Verein, Integer> tc_platz;

    @FXML
    private ListView<Spielpaarung> lv_spiele;
    
    @FXML
    void btn_ergebnisse(ActionEvent event) {
    	//Main.fc.ergebnisseGenerieren();    	
    	Spielpaarung sp = this.lv_spiele.getSelectionModel().getSelectedItem();
    	
    	if(sp != null && sp.getFinish() != 1) { 
    		/*
    		Fxml_Dialog.sp = sp;
    		Fxml_Dialog dialog = new Fxml_Dialog();
    		dialog.display();    		 
    		*/
    		try {
    			Fxml_Dialog.sp = sp;
				VBox root = FXMLLoader.load(getClass().getResource("dialog.fxml"));
				Dialog d = new Dialog();
				d.getDialogPane().setContent(root);
	    		d.initStyle(StageStyle.TRANSPARENT);
				d.getDialogPane().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					
	    		BoxBlur bb = new BoxBlur(3,3,3);
	    		this.btn_close.getScene().getRoot().setEffect(bb);
	    		d.showAndWait();
	    		while(d.isShowing()) {
	    			
	    		}
	    		this.btn_close.getScene().getRoot().setEffect(null);
	    		//System.out.println("optional");
    			this.renderSpielpaarung();
    			this.renderVereine();	  
    			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		/*
    		try {
    			Fxml_Dialog.sp = sp;
				VBox root = FXMLLoader.load(getClass().getResource("dialog.fxml"));
				Dialog d = new Dialog();
				d.getDialogPane().setContent(root);
	    		d.initStyle(StageStyle.TRANSPARENT);
				d.getDialogPane().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						
	    		Optional opt = d.showAndWait();
	    		if (opt.isPresent()) {
	    			System.out.println("optional");
	    			this.renderSpielpaarung();
	    			this.renderVereine();	    			
	    		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		*/
    		
    		
    	} 	    	
    }

    @FXML
    void btn_paarungen(ActionEvent event) {
    	Main.fc.paarungenGenerieren();
    }
    
    @FXML
    void btn_close(ActionEvent event) {
    	//Dateien Speichern
    	Main.fc.close();
    	((Stage) this.btn_close.getScene().getWindow()).close();
    }

    @FXML
    void btn_spielliste(ActionEvent event) {
    	//this.getTa_spiele().setText(Main.fc.getSpielliste());
    	this.renderSpielpaarung();
    	
    	//auﬂerdem Vereine Punktzahl aktualisieren
		this.renderVereine();
    }

    @FXML
    void btn_vereine(ActionEvent event) {
    	if(Main.fc.addVerein(this.tf_verein.getText())) {
    		//this.getTa_vereine().setText(Main.fc.getVereinsliste());
    		
    		this.renderVereine();
    		this.tf_verein.setText("");    		
    	} else {
    		this.tf_verein.setText(Main.fc.vereinError);
    	}
    	
    }

    @FXML
    void tf_verein(ActionEvent event) {
    	//onFocus Clear
    }

    public TextArea getTa_vereine() {
		return ta_vereine;
	}

	public TextArea getTa_spiele() {
		return ta_spiele;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//this.getTa_vereine().setText(Main.fc.getVereinsliste());

		//zu list items machen
		//this.getTa_spiele().setText(Main.fc.getSpielliste());
		//this.lv_spiele.getItems().addAll(Main.fc.getAp());
		this.renderSpielpaarung();
		
		//TableView
		this.tc_name.setCellValueFactory(new PropertyValueFactory<Verein, String>("name"));
		this.tc_spiele.setCellValueFactory(new PropertyValueFactory<Verein, Integer>("spiele"));
		this.tc_punkte.setCellValueFactory(new PropertyValueFactory<Verein, Integer>("punkte"));
		this.tc_tore.setCellValueFactory(new PropertyValueFactory<Verein, Integer>("tore"));
		this.tc_ggntore.setCellValueFactory(new PropertyValueFactory<Verein, Integer>("gegentore"));
		
		this.tc_siege.setCellValueFactory(new PropertyValueFactory<Verein, Integer>("siege"));
		this.tc_unentschieden.setCellValueFactory(new PropertyValueFactory<Verein, Integer>("unentschieden"));
		this.tc_niederlagen.setCellValueFactory(new PropertyValueFactory<Verein, Integer>("niederlagen"));
		this.tc_tordiff.setCellValueFactory(new PropertyValueFactory<Verein, Integer>("tordiff"));
		
		this.ermittelPlatz();
		
		this.tc_platz.setCellValueFactory(new PropertyValueFactory<Verein, Integer>("platz"));
		
		//Vereine beim start anzeigen		
		this.renderVereine();
		
	}
	
	private void ermittelPlatz() {
		//Platz
		for(int index = 0; index < Main.fc.getAv().size(); index++) {
			Main.fc.getAv().get(index).setPlatz(index+1);
		}
		
	}

	private void renderVereine() {
		this.tv_verein.getItems().clear();
		this.ermittelPlatz();
		this.tv_verein.getItems().addAll(Main.fc.getAv());
	}
	
	private void renderSpielpaarung() {
		this.lv_spiele.getItems().clear();
    	this.lv_spiele.getItems().addAll(Main.fc.getAp());
	}
}
