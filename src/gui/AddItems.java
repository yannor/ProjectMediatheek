package gui;

import db.DbConnect;
import item.ItemBeheer;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AddItems extends GridPane {

    @FXML
    private TextField txtNaam, txtAuteur, txtThema, txtAantalBlz, txtAantalExemp, txtNiveau, txtISBN;
    
      @FXML
    private Label lblNaam, lblAuteur, lblThema, lblAantalBlz, lblAantalExemp, lblNiveau, lblISBN;

    @FXML
    private TextArea txaBeschrijving;

    @FXML
    private ComboBox comboItem;

    DbConnect connect;

    ItemBeheer domeinController;

    public AddItems(ItemBeheer domeinController) {

        this.domeinController = domeinController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddItems.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        comboItem.getSelectionModel().selectFirst();
    }

    @FXML
    public void addItem() {
        if (comboItem.getSelectionModel().getSelectedIndex() == 0) {
            domeinController.addBoek(txtNaam.getText(), txtISBN.getText(), txtThema.getText(),
                    parseInt(txtAantalExemp.getText()), txtAuteur.getText(), parseInt(txtAantalBlz.getText()), txtNiveau.getText(), txaBeschrijving.getText());
        }

        if (comboItem.getSelectionModel().getSelectedIndex() == 1)//gezelschapsspel
        {
       domeinController.addSpel(txtNaam.getText(),
                    parseInt(txtAantalExemp.getText()),txaBeschrijving.getText(),"",txtThema.getText() );
        }

        if (comboItem.getSelectionModel().getSelectedIndex() == 2)//verteltas
        {
     domeinController.addVerteltas(txtNaam.getText(),
                    parseInt(txtAantalExemp.getText()),txaBeschrijving.getText(),txtThema.getText(), txtThema.getText() );
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Item toegevoegd");
        alert.setHeaderText(null);
        alert.setContentText("\""+txtNaam.getText() + "\" is toegevoegd");

        alert.showAndWait();
    }

    @FXML
    public void cancel() {
        Stage stage = new Stage();
        //stage.close();
        Stage dezeStage = (Stage) txtNaam.getScene().getWindow();
        dezeStage.close();
        Scene scene = new Scene(new OverzichtItems(domeinController));
        stage.setScene(scene);
        stage.setTitle("Items");
        stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });
        stage.show();
    }

    @FXML
    public void chooseItem() {
        if (comboItem.getSelectionModel().getSelectedIndex() == 0)//boek
        {
            txtAuteur.setVisible(true);
            txtAantalBlz.setVisible(true);
            txtISBN.setVisible(true);
            
            lblAuteur.setVisible(true);
            lblAantalBlz.setVisible(true);
            lblISBN.setVisible(true);
        }

        if (comboItem.getSelectionModel().getSelectedIndex() == 1)//gezelschapsspel
        {
            txtAuteur.setVisible(false);
            txtAantalBlz.setVisible(false);
            txtISBN.setVisible(false);
            
            lblAuteur.setVisible(false);
            lblAantalBlz.setVisible(false);
            lblISBN.setVisible(false);
        }

        if (comboItem.getSelectionModel().getSelectedIndex() == 2)//verteltas
        {
            txtAuteur.setVisible(false);
            txtAantalBlz.setVisible(false);
            txtISBN.setVisible(false);
            
            lblAuteur.setVisible(false);
            lblAantalBlz.setVisible(false);
            lblISBN.setVisible(false);
        }
    }

}
