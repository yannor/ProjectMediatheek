/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.zoeken;

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

    private Dvd dvd;
    private boolean ingelogd;
    
    public DvdsPaneController(Dvd dvd, boolean ingelogd)
    {
        this.dvd = dvd;
        
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
        }
        
        lblNaam.setText(dvd.getNaam());
        
        txtThema.setText(dvd.getThema());
        txtLeeftijd.setText(dvd.getLeeftijd());
        txtAantal.setText("" + dvd.getAantal());
        txtPagLen.setText("" + dvd.getMin());
    }
    
    
}
