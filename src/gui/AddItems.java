package gui;

import db.DbConnect;
import item.ItemBeheer;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddItems extends GridPane {

    @FXML
    private TextField txtNaam, txtAuteur, txtThema, txtAantalBlz, txtAantalExemp, txtNiveau, txtISBN;
    
    @FXML
    private TextArea txaBeschrijving;
    
    @FXML
    private ComboBox comboItem;
    
    DbConnect connect;
    
    ItemBeheer domeinController;
    
    
    public AddItems(ItemBeheer domeinController) {

        this.domeinController=domeinController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddItems.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        
        
        
    }
    
    @FXML
    public void addItem()
    {
       domeinController.addBoek(txtNaam.getText(),txtISBN.getText(), txtThema.getText(),
               parseInt(txtAantalExemp.getText()),txtAuteur.getText(),parseInt(txtAantalBlz.getText()),txtNiveau.getText(), txaBeschrijving.getText());
       
       Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Item toegevoegd");
alert.setHeaderText(null);
alert.setContentText(txtNaam.getText()+" is toegevoegd");

alert.showAndWait();
    }

}
