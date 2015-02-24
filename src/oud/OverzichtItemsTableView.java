
package oud;

import gui.*;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import db.DbConnect;
import item.*;

public class OverzichtItemsTableView extends SplitPane {

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
    private TableView<Item> TableItems;

    @FXML
    private TableColumn<Item, Integer> idCol;

    @FXML
    private TableColumn<Item, String> naamCol;

    public OverzichtItemsTableView(ItemBeheer domController) {
        this.domeinController = domController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtItems.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        updateDetails();

    }

    @FXML
    private void zoekOpThema(ActionEvent event) {

    }

    @FXML
    private void zoekOpNaam(ActionEvent event) {

    }

    @FXML
    private void delete(ActionEvent event) {
        Item item = TableItems.getSelectionModel().getSelectedItem();
        txtTitel.setText("");
        txtBeschrijving.setText("");
        domeinController.removeItem(item);
        updateDetails();

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
        String itemTitel = txtTitel.getText();
        String itemBes = txtBeschrijving.getText();
        int itemId = TableItems.getSelectionModel().getSelectedItem().getId();
        domeinController.updateItem(itemId, itemTitel, itemBes);

        lblChange.setVisible(false);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        txtTitel.setEditable(false);
        txtBeschrijving.setEditable(false);

        updateDetails();

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

    private void updateDetails() {

       // TableItems.setItems(domeinController.getItems());

        idCol.setCellValueFactory(new PropertyValueFactory<>(getId()));

        naamCol.setCellValueFactory(new PropertyValueFactory<>("titel"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableItems.setItems(domeinController.getItems());

    }

    @FXML
    public void selectItem() {
        txtTitel.setText(TableItems.getSelectionModel().getSelectedItem().getTitel());
        txtBeschrijving.setText(TableItems.getSelectionModel().getSelectedItem().getBeschrijving());

    }
/*
    protected void schermAddItemOpenen() {
        Stage stage = new Stage();
        stage.setTitle("Item toevoegen");

        Scene scene = new Scene(new ItemToevoegen(new ItemBeheer()));
        stage.setScene(scene);

        this.setDisable(true);

        //Het hoofdvenster mag niet afgesloten worden
       
        Stage stageItems = (Stage) txtBeschrijving.getScene().getWindow();
        EventHandler handler = (Event event) -> {
            event.consume();
        };
        stageItems.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, handler);

        //het subvenster mag niet gesloten  worden
        //----------------------------------------        
        stage.setOnCloseRequest(
                (WindowEvent event) -> {
                    event.consume();
                });

        //luisteraar indien het subscherm gesloten wordt. 
        //---------------------------------------------
        stage.addEventHandler(WindowEvent.WINDOW_HIDING, (WindowEvent event) -> {
            OverzichtItems.this.setDisable(false);

            stageItems.removeEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, handler);
        });

        // Het subscherm wordt niet kleiner dan het minimum scherm.
        //---------------------------------------------------------
        stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });

        stage.show();

        
        //bij de anier hieronder wordt dit scherm gesloten bij het opstarten en terug geopend als item is toegevoegd, op deze manier wordt nieuw ite direct ingeladen
        /*
        Stage stage = new Stage();
        //stage.close();
        Stage dezeStage = (Stage) TableItems.getScene().getWindow();
        dezeStage.close();
        Scene scene = new Scene(new ItemToevoegen(domeinController));
stage.setScene(scene);
stage.setTitle("Item Toevoegen");
stage.setOnShown((WindowEvent t) -> {
stage.setMinWidth(stage.getWidth());
stage.setMinHeight(stage.getHeight());
});
stage.show();*/
        
    }

