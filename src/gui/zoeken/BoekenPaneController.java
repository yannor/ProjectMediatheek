/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.zoeken;

import db.DbConnect;
import items.Boek;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
    
    private Boek boek;
    private boolean ingelogd;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextField txtUitgeverij;
    
    public BoekenPaneController(Boek boek, boolean ingelogd)
    {
        this.boek = boek;
        this.ingelogd = ingelogd;
        
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
        }
        lblNaam.setText(boek.getNaam());

        txtThema.setText(boek.getThema());
        txtLeeftijd.setText(boek.getLeeftijd());
        txtAantal.setText("" + boek.getAantal());
        txaBeschrijving.setText(boek.getBeschrijving());
        txtAuteur.setText(boek.getAuteur());
        txtUitgeverij.setText(boek.getUitgeverij());
    }
}
