/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.zoeken;

import db.DbConnect;
import items.Spel;
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
public class SpellenPaneController extends AnchorPane
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
    private Label lblBeschrijving;
    @FXML
    private TextArea txaBeschrijving;
    @FXML
    private TextField txtUitgeverij;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Button btnOpslaan;
    @FXML
    private Button btnAnnuleren;
    
    private Spel spel;
    private boolean ingelogd;
    private DbConnect db;
    
    public SpellenPaneController(Spel spel, boolean ingelogd)
    {
        this.spel = spel;
        this.ingelogd = ingelogd;
        db = new DbConnect();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SpellenPane.fxml"));
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
            txtUitgeverij.setEditable(false);
        }
        
        lblNaam.setText(spel.getNaam());
        txtThema.setText(spel.getThema());
        txtLeeftijd.setText(spel.getLeeftijd());
        txtAantal.setText("" + spel.getAantal());
        txtUitgeverij.setText(spel.getUitgeverij());
        txaBeschrijving.setText(spel.getBeschrijving());
    }
    
    @FXML
    private void opslaan()
    {
        spel.setThema(txtThema.getText());
        spel.setLeeftijd(txtLeeftijd.getText());
        spel.setAantal(Integer.parseInt(txtAantal.getText()));
        spel.setBeschrijving(txaBeschrijving.getText());
        spel.setUitgeverij(txtUitgeverij.getText());
        
        db.wijzigSpel(spel);
    }
    
    @FXML
    private void annuleren()
    {
        txtThema.setText(spel.getThema());
        txtLeeftijd.setText(spel.getLeeftijd());
        txtAantal.setText("" + spel.getAantal());
        txaBeschrijving.setText(spel.getBeschrijving());
        txtUitgeverij.setText(spel.getUitgeverij());
    }
}
