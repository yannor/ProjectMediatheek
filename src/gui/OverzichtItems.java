package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import db.DbConnect;
import item.*;
import static java.lang.Integer.parseInt;
import java.util.Optional;
import javafx.scene.AccessibleAttribute;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class OverzichtItems extends SplitPane {

    private ItemBeheer domeinController;

    DbConnect connect = new DbConnect();

    @FXML
    private TextField txtZoekTitel, txtZoekThema, txtNaam, txtISBN, txtThema, txtAantal, txtAuteur, txtPaginas, txtLeesniveau;

    @FXML
    private TextArea txtBeschrijving;

    @FXML
    private Button btnAdd, btnDelete, btnChange, btnSave, btnCancel;

    @FXML
    private Label lblChange, lblISBN;

    @FXML
    private TreeView<String> treeViewItems;

    TreeItem<String> root;

    TreeItem<String> boekenRoot;
    TreeItem<String> spellenRoot;
    TreeItem<String> vertelRoot;

    int selected;

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
        root = new TreeItem<>();
        boekenRoot = new TreeItem<>("Boeken");
        spellenRoot = new TreeItem<>("Gezelschapsspellen");
        vertelRoot = new TreeItem<>("Verteltassen");

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

        Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Delete item");

alert.setContentText("Bent u zeker dat u "+treeViewItems.getSelectionModel().getSelectedItem().getValue()+" wilt verwijderen?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    // ... user chose OK
      if (treeViewItems.getSelectionModel().getSelectedItem().getParent().equals(boekenRoot)) {

            domeinController.deleteBoek(selected);
        }
        if (treeViewItems.getSelectionModel().getSelectedItem().getParent().equals(spellenRoot)) {
            domeinController.deleteSpel(selected );

        }
        if (treeViewItems.getSelectionModel().getSelectedItem().getParent().equals(vertelRoot)) {
            domeinController.deleteVerteltas(selected );
        }
        
} else {
    // ... user chose CANCEL or closed the dialog
}
      
    }

    @FXML
    private void add(ActionEvent event) {

        schermAddItemOpenen();
    }

    @FXML
    private void change(ActionEvent event) {

        lblChange.setVisible(true);
        btnSave.setVisible(true);
        btnCancel.setVisible(true);

        txtNaam.setEditable(true);
        txtISBN.setEditable(true);
        txtThema.setEditable(true);
        txtAantal.setEditable(true);
        txtAuteur.setEditable(true);
        txtPaginas.setEditable(true);
        txtLeesniveau.setEditable(true);
        txtBeschrijving.setEditable(true);

    }

    @FXML
    private void save(ActionEvent event) {

        lblChange.setVisible(false);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        txtNaam.setEditable(false);
        txtISBN.setEditable(false);
        txtThema.setEditable(false);
        txtAantal.setEditable(false);
        txtAuteur.setEditable(false);
        txtPaginas.setEditable(false);
        txtLeesniveau.setEditable(false);
        txtBeschrijving.setEditable(false);

        if (treeViewItems.getSelectionModel().getSelectedItem().getParent().equals(boekenRoot)) {

            domeinController.updateBoek(selected, txtNaam.getText(), txtISBN.getText(), txtThema.getText(), parseInt(txtAantal.getText()), txtAuteur.getText(),
                    parseInt(txtPaginas.getText()), txtLeesniveau.getText(), txtBeschrijving.getText());
        }
        if (treeViewItems.getSelectionModel().getSelectedItem().getParent().equals(spellenRoot)) {
            domeinController.updateSpel(selected , txtNaam.getText(), parseInt(txtAantal.getText()), txtBeschrijving.getText(),
                    "", txtThema.getText());

        }
        if (treeViewItems.getSelectionModel().getSelectedItem().getParent().equals(vertelRoot)) {
            domeinController.updateVerteltas(selected , txtNaam.getText(), parseInt(txtAantal.getText()), txtBeschrijving.getText(),
                    txtThema.getText(), txtLeesniveau.getText());
        }

    }

    @FXML
    private void cancel(ActionEvent event) {
        lblChange.setVisible(false);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        select();
        txtNaam.setEditable(false);
        txtISBN.setEditable(false);
        txtThema.setEditable(false);
        txtAantal.setEditable(false);
        txtAuteur.setEditable(false);
        txtPaginas.setEditable(false);
        txtLeesniveau.setEditable(false);
        txtBeschrijving.setEditable(false);

    }

    private void updateTreeView() {

        
        int lenB = connect.getAlleBoeken().size();

        for (int i = 1; i <= lenB; i++) {
            TreeItem<String> boek = new TreeItem<>(connect.getAlleBoeken().get(i - 1).getTitel());
            boekenRoot.getChildren().add(boek);
        }
        int lenS = connect.getAlleSpellen().size();

        for (int i = 1; i <= lenS; i++) {
            TreeItem<String> spel = new TreeItem<>(connect.getAlleSpellen().get(i - 1).getTitel());
            spellenRoot.getChildren().add(spel);
        }

        int lenV = connect.getAlleVertelTassen().size();

        for (int i = 1; i <= lenV; i++) {
            TreeItem<String> verteltas = new TreeItem<>(connect.getAlleVertelTassen().get(i - 1).getTitel());
            vertelRoot.getChildren().add(verteltas);
        }

        root.getChildren().addAll(boekenRoot, spellenRoot, vertelRoot);

        treeViewItems.setRoot(root);
        treeViewItems.setShowRoot(false);

        txtNaam.setEditable(false);
        txtISBN.setEditable(false);
        txtThema.setEditable(false);
        txtAantal.setEditable(false);
        txtAuteur.setEditable(false);
        txtPaginas.setEditable(false);
        txtLeesniveau.setEditable(false);
        txtBeschrijving.setEditable(false);

    }

    @FXML
    private void select() {

        if (treeViewItems.getSelectionModel().getSelectedItem().getParent().equals(root)|| treeViewItems.getSelectionModel().getSelectedItem().equals(root)) {
            txtNaam.setText("");
            txtISBN.setText("");
            txtThema.setText("");
            txtAantal.setText("");
            txtAuteur.setText("");
            txtPaginas.setText("");
            txtLeesniveau.setText("");
            txtBeschrijving.setText("");
            txtISBN.setVisible(false);
            lblISBN.setVisible(false);
        }
        

        if (treeViewItems.getSelectionModel().getSelectedItem().getParent().equals(boekenRoot)) {
            
            int hulp = treeViewItems.getSelectionModel().getSelectedIndex() - 1;
            selected = connect.getAlleBoeken().get(hulp).getId();
            
            txtNaam.setText(connect.getBoek(selected).getTitel());
            txtISBN.setVisible(true);
            lblISBN.setVisible(true);
            txtISBN.setText(connect.getBoek(selected).getIsbn());
            txtThema.setText(connect.getBoek(selected).getThema());
            txtAantal.setText("" + connect.getBoek(selected).getAantalExemplaren());
            txtAuteur.setText(connect.getBoek(selected).getAuteur());
            txtPaginas.setText("" + connect.getBoek(selected).getAantalBlz());
            txtLeesniveau.setText(connect.getBoek(selected).getMoeilijkheidgraad());
            txtBeschrijving.setText(connect.getBoek(selected).getBeschrijving());
        }
        if (treeViewItems.getSelectionModel().getSelectedItem().getParent().equals(spellenRoot)) {
            int hulp=0;
            if(boekenRoot.isExpanded())
            {
               hulp = treeViewItems.getSelectionModel().getSelectedIndex()-2-connect.getAlleBoeken().size() ; 
            }
            else
            {
                 hulp = treeViewItems.getSelectionModel().getSelectedIndex()-2 ;
            }
            
            
            selected = connect.getAlleSpellen().get(hulp).getId();
            
            txtNaam.setText(connect.getSpel(selected).getTitel());
            txtISBN.setText("");
            txtISBN.setVisible(false);
            lblISBN.setVisible(false);
            txtThema.setText(connect.getSpel(selected).getThema());
            txtAantal.setText("" + connect.getSpel(selected).getAantalExemplaren());
            txtAuteur.setText("");
            txtPaginas.setText("");
            txtLeesniveau.setText("");
            txtBeschrijving.setText(connect.getSpel(selected).getBeschrijving());
        }
        if (treeViewItems.getSelectionModel().getSelectedItem().getParent().equals(vertelRoot)) {
            int hulp=0;
            if(boekenRoot.isExpanded()&!spellenRoot.isExpanded())
            {
               hulp = treeViewItems.getSelectionModel().getSelectedIndex()-3-connect.getAlleBoeken().size() ; 
            }
            else
                if(spellenRoot.isExpanded()&!boekenRoot.isExpanded())
            {
               hulp = treeViewItems.getSelectionModel().getSelectedIndex()-3-connect.getAlleSpellen().size() ; 
            }
            else
                    if(spellenRoot.isExpanded()&&boekenRoot.isExpanded())
                    {
                      hulp = treeViewItems.getSelectionModel().getSelectedIndex()-3-connect.getAlleSpellen().size()-connect.getAlleBoeken().size() ;  
                    }
            else
                    {hulp = treeViewItems.getSelectionModel().getSelectedIndex() - 3;}
            
            selected = connect.getAlleVertelTassen().get(hulp).getId();

            txtNaam.setText(connect.getVerteltas(selected).getTitel());
            txtISBN.setText("");
            txtISBN.setVisible(false);
            lblISBN.setVisible(false);
            txtThema.setText(connect.getVerteltas(selected).getThema());
            txtAantal.setText("" + connect.getVerteltas(selected).getAantalExemplaren());
            txtAuteur.setText("");
            txtPaginas.setText("");
            txtLeesniveau.setText("");
            txtBeschrijving.setText(connect.getVerteltas(selected).getBeschrijving());
        
        }
        
        

    }

    public void schermAddItemOpenen() {
        Stage stage = new Stage();
        //stage.close();
        Stage dezeStage = (Stage) treeViewItems.getScene().getWindow();
        dezeStage.close();
        Scene scene = new Scene(new AddItems(domeinController));
        stage.setScene(scene);
        stage.setTitle("Item Toevoegen");
        stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });
        stage.show();
    }

}
