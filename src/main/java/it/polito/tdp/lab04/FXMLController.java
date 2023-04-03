/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.lab04.model.*;

import it.polito.tdp.lab04.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="btnCerca"
    private Button btnCerca; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorso"
    private Button btnCercaIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML // fx:id="checkMatricola"
    private CheckBox checkMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="comboCorsi"
    private ComboBox<Corso> comboCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    
    @FXML
    void doCerca(ActionEvent event) {

    }
    
    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	int matr;
    	Corso c = this.comboCorsi.getValue();
    	
    	try {
    		
    		matr = Integer.parseInt(this.txtMatricola.getText());
    		Studente s = this.model.getNomeECognome(matr);
    		this.txtResult.clear();
    		
    		if(s != null) {
    			
    			List<Corso> l = new LinkedList<Corso>(this.model.getCorsiACuiEIscrittoUnoStudente(s));
    			
    			if(c == null) {
	    			
	    	    	for(Corso cc : l) {
	    	    		this.txtResult.appendText(cc.toStringVero() + "\n");
	    	    	}
    			}else {
    				if(l.contains(c)) {
    					this.txtResult.setText("Studente già iscritto al corso");
    				}else {
    					this.txtResult.setText("Studente non iscritto al corso");
    				}
    			}
    			
    		}else {
    			this.txtResult.setText("La matricola inserita non è presente nel database");
    		}
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		this.txtResult.setText("Inserire numero di matricola valido");
    		return;
    	}

    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	
    	Corso c = this.comboCorsi.getValue();
    	
    	this.txtResult.clear();
    	if(c != null) {
	    	List<Studente> l = new LinkedList<Studente>(this.model.getStudentiIscrittiAlCorso(c));
	    	
	    	if(!l.isEmpty()) {
	    		for(Studente s : l) {
	    			this.txtResult.appendText(s.toString() + "\n");
	    		}
	    	}
    	}else {
    		this.txtResult.setText("Non è stato inserito nessun corso");
    	}
    }

    @FXML
    void doCheckMatricola(ActionEvent event) {
    	
    	int matr;
    	
    	try {
    		
    		matr = Integer.parseInt(this.txtMatricola.getText());
    		Studente s = this.model.getNomeECognome(matr);
    		
    		if(s != null) {
    			this.txtNome.setText(s.getNome());
    			this.txtCognome.setText(s.getCognome());
    		}else {
    			this.txtResult.setText("La matricola inserita non è presente nel database");
    		}
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		this.txtResult.setText("Inserire numero di matricola valido");
    		return;
    	}

    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	int matr;
    	Corso c = this.comboCorsi.getValue();
    	
    	try {
    		
    		matr = Integer.parseInt(this.txtMatricola.getText());
    		Studente s = this.model.getNomeECognome(matr);
    		this.txtResult.clear();
    		
    		if(s != null && c != null) {
    			
    			List<Corso> l = new LinkedList<Corso>(this.model.getCorsiACuiEIscrittoUnoStudente(s));
    			
    			if(l.contains(c)) {
    				this.txtResult.setText("Lo studente è già iscritto al corso");
    			}else {
    				boolean controllo = false;
    				
    				controllo = this.model.inserisciIscrizione(s, c);
    				
    				if(controllo == true) {
    					this.txtResult.setText("Studente registrato correttamente!");
    				}else {
    					this.txtResult.setText("Errore nella registrazione dello studente!");
    				}
    			}
    			
    		}else {
    			this.txtResult.setText("La matricola o il corso non sono stati inseriti correttamente");
    		}
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		this.txtResult.setText("Inserire numero di matricola valido");
    		return;
    	}

    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	this.txtCognome.clear();
    	this.txtMatricola.clear();
    	this.txtNome.clear();
    	this.txtResult.clear();
    	this.checkMatricola.setSelected(false);
    	this.comboCorsi.getSelectionModel().clearSelection();

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert checkMatricola != null : "fx:id=\"checkMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert comboCorsi != null : "fx:id=\"comboCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
        

    }
    public void setModel(Model model) {
		this.model = model;
		this.comboCorsi.getItems().addAll(this.model.getTuttiCorsi());
	}

}