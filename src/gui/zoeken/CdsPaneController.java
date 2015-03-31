/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.zoeken;

import db.DbConnect;
import items.Cd;
import items.Lied;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Robin
 */
public class CdsPaneController extends AnchorPane
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
    private Label lblTableView;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn colSoortZanger;
    @FXML
    private TableColumn colAantalLengte;
    @FXML
    private TableColumn colNaam;
    @FXML
    private TextField txtPagLen;
    
    private Cd cd;
    private DbConnect db;
    private boolean ingelogd;

    public CdsPaneController(Cd cd, boolean ingelogd)
    {
        this.cd = cd;
        this.ingelogd = ingelogd;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CdsPane.fxml"));
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
        lblNaam.setText(cd.getNaam());
        
        txtThema.setText(cd.getThema());
        txtLeeftijd.setText(cd.getLeeftijd());
        txtAantal.setText("" + cd.getAantal());
        
        // db.getLiedjes(cd.getId()) werkt niet
        ObservableList<Lied> liedjes = FXCollections.observableArrayList(new ArrayList<Lied>());
        
        colSoortZanger.setCellValueFactory(new PropertyValueFactory<Lied, String>("zanger"));
        colAantalLengte.setCellValueFactory(new PropertyValueFactory<Lied, String>("aantal"));
        colNaam.setCellValueFactory(new PropertyValueFactory<Lied, String>("naam"));
        
        tableView.setItems(liedjes);
        tableView.getColumns().addAll(colSoortZanger, colAantalLengte, colNaam);
    }
}
