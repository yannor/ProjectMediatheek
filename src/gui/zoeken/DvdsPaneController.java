/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.zoeken;

import db.DbConnect;
import items.Dvd;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Robin
 */
public class DvdsPaneController extends AnchorPane
{
    @FXML
    private Button btnLeen;
    @FXML
    private Label lblNaam;
    @FXML
    private Label lblThema;
    @FXML
    private TextField txtThema;
    @FXML
    private Label lblLeeftijd;
    @FXML
    private TextField txtLeeftijd;
    @FXML
    private Label lblAantal;
    @FXML
    private TextField txtAantal;
    @FXML
    private Label lblPagLen;
    @FXML
    private Label lblBeschrijving;
    @FXML
    private TextArea txaBeschrijving;
    @FXML
    private TextField txtPagLen;
    @FXML
    private Button btnOpslaan;
    @FXML
    private Button btnAnnuleren;

    private Dvd dvd;
    private boolean ingelogd;
    private DbConnect db;
    
    public DvdsPaneController(Dvd dvd, boolean ingelogd)
    {
        this.dvd = dvd;
        this.ingelogd = ingelogd;
        db = new DbConnect();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DvdsPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try 
        {
            loader.load();
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
        vulGegevensIn();
    }

    private void vulGegevensIn()
    {
        if(!ingelogd)
        {
            btnLeen.setDisable(true);
            btnLeen.setVisible(false);
            btnOpslaan.setDisable(true);
            btnAnnuleren.setDisable(true);
            btnOpslaan.setVisible(false);
            btnAnnuleren.setVisible(false);
            
            txtThema.setEditable(false);
            txtLeeftijd.setEditable(false);
            txtAantal.setEditable(false);
            txaBeschrijving.setEditable(false);
        }
        
        lblNaam.setText(dvd.getNaam());
        
        txtThema.setText(dvd.getThema());
        txtLeeftijd.setText(dvd.getLeeftijd());
        txtAantal.setText("" + dvd.getAantal());
        txaBeschrijving.setText(dvd.getBeschrijving());
    }
    
    @FXML
    private void opslaan()
    {
        dvd.setThema(txtThema.getText());
        dvd.setLeeftijd(txtLeeftijd.getText());
        dvd.setAantal(Integer.parseInt(txtAantal.getText()));
        dvd.setBeschrijving(txaBeschrijving.getText());
        
        db.wijzigDvd(dvd);
    }
    
    @FXML
    private void annuleren()
    {
        txtThema.setText(dvd.getThema());
        txtLeeftijd.setText(dvd.getLeeftijd());
        txtAantal.setText("" + dvd.getAantal());
        txaBeschrijving.setText(dvd.getBeschrijving());
    }
}
