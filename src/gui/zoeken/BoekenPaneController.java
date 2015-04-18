/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.zoeken;

import db.DbConnect;
import items.Boek;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class BoekenPaneController extends AnchorPane
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
    private Label lblAuteur;
    @FXML
    private Label lblPagLen;
    @FXML
    private Label lblBeschrijving;
    @FXML
    private TextArea txaBeschrijving;
    @FXML
    private TextField txtAuteur;
    @FXML
    private TextField txtPagLen;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextField txtUitgeverij;
    @FXML
    private Button btnOpslaan;
    @FXML
    private Button btnAnnuleren;
    
    private Boek boek;
    private DbConnect db;
    private boolean ingelogd;
    
    
    public BoekenPaneController(Boek boek, boolean ingelogd)
    {
        this.boek = boek;
        this.ingelogd = ingelogd;
        db = new DbConnect();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoekenPane.fxml"));
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
            txtAuteur.setEditable(false);
            txtUitgeverij.setEditable(false);
        }
        lblNaam.setText(boek.getNaam());

        txtThema.setText(boek.getThema());
        txtLeeftijd.setText(boek.getLeeftijd());
        txtAantal.setText("" + boek.getAantal());
        txaBeschrijving.setText(boek.getBeschrijving());
        txtAuteur.setText(boek.getAuteur());
        txtUitgeverij.setText(boek.getUitgeverij());
    }
    
    @FXML
    private void opslaan()
    {
        boek.setThema(txtThema.getText());
        boek.setLeeftijd(txtLeeftijd.getText());
        boek.setAantal(Integer.parseInt(txtAantal.getText()));
        boek.setBeschrijving(txaBeschrijving.getText());
        boek.setAuteur(txtAuteur.getText());
        boek.setUitgeverij(txtUitgeverij.getText());
        
        db.wijzigBoek(boek);
    }
    
    @FXML
    private void annuleren()
    {
        txtThema.setText(boek.getThema());
        txtLeeftijd.setText(boek.getLeeftijd());
        txtAantal.setText("" + boek.getAantal());
        txaBeschrijving.setText(boek.getBeschrijving());
        txtAuteur.setText(boek.getAuteur());
        txtUitgeverij.setText(boek.getUitgeverij());
    }
}
