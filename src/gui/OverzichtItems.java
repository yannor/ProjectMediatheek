
package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import db.DbConnect;
import item.*;

public class OverzichtItems extends SplitPane {

    private ItemBeheer domeinController;

    DbConnect connect = new DbConnect();

    @FXML
    private TextField txtZoekTitel, txtZoekThema, txtTitel;

    @FXML
    private TextArea txtBeschrijving;

    @FXML
    private Button btnAdd, btnDelete, btnChange, btnSave, btnCancel;

    @FXML
    private Label lblChange;

    @FXML
    private TreeView<String> treeViewItems;

   

    public OverzichtItems(ItemBeheer domController) {
        this.domeinController = domController;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtItems.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        
        
        updateTreeView();
        

    }

    @FXML
    private void zoekOpThema(ActionEvent event) {

    }

    @FXML
    private void zoekOpNaam(ActionEvent event) {

    }

    @FXML
    private void delete(ActionEvent event) {
        

    }

    @FXML
    private void add(ActionEvent event) {

        //schermAddItemOpenen();

    }

    @FXML
    private void change(ActionEvent event) {

        lblChange.setVisible(true);
        btnSave.setVisible(true);
        btnCancel.setVisible(true);

        txtTitel.setEditable(true);
        txtBeschrijving.setEditable(true);

    }

    @FXML
    private void save(ActionEvent event) {
       

        lblChange.setVisible(false);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        txtTitel.setEditable(false);
        txtBeschrijving.setEditable(false);

        updateTreeView();

    }

    @FXML
    private void cancel(ActionEvent event) {
        lblChange.setVisible(false);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        txtTitel.setEditable(false);
        txtBeschrijving.setEditable(false);
        selectItem();

    }

    private void updateTreeView() {

        TreeItem<String> root = new TreeItem<> ("Items");
        
        TreeItem<String> boekenRoot = new TreeItem<> ("Boeken");
        TreeItem<String> spellenRoot = new TreeItem<> ("Gezelschapsspellen");
        TreeItem<String> vertelRoot = new TreeItem<> ("Verteltassen");
        
       Object []boekenArray= connect.getAlleBoeken().toArray();
       
       for(int i=1; i<=boekenArray.length;i++)
       {
           TreeItem<String> boek = new TreeItem<>(connect.getBoek(i));
           boekenRoot.getChildren().add(boek);
       }
       
       Object []spellenArray= connect.getAlleSpellen().toArray();
       
       for(int i=1; i<=spellenArray.length;i++)
       {
           TreeItem<String> spel = new TreeItem<>(connect.getSpel(i));
           spellenRoot.getChildren().add(spel);
       }
 
        Object []vertelArray= connect.getAlleVertelTassen().toArray();
       
       for(int i=1; i<=vertelArray.length;i++)
       {
           TreeItem<String> verteltas = new TreeItem<>(connect.getVerteltas(i));
           vertelRoot.getChildren().add(verteltas);
       }
         
        
        
         root.getChildren().addAll(boekenRoot, spellenRoot, vertelRoot);
       
         treeViewItems.setRoot(root);
         treeViewItems.setShowRoot(false);
        
        

    }

    @FXML
    public void selectItem() {
       

    }

        
    }

