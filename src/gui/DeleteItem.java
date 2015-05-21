package gui;

import items.Item;
import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import util.ItemManagement;


public class DeleteItem extends BorderPane  
{
    @FXML
    private Button btnDelete, btnCancel;
    
    @FXML
    private ListView lvItems;
    ObservableList<Item> items;
    ScreenSwitcher switcher;
    ItemManagement man = new ItemManagement();
    
    public DeleteItem(ScreenSwitcher switcher) {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
       items = FXCollections.observableArrayList();
       items.clear();
       items.addAll(man.getItems());
       lvItems.setItems(items);

    }
    
    public void delete()
    {
        //code Dries
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.setTitle("Item verwijderen");
//        alert.setHeaderText(null);
//        alert.setContentText("Opgelet,U staat op het punt om een item uit de databank te verwijderen, wilt u doorgaan?");
//        alert.showAndWait();
//        //verwijderen uit lijst
//        final int selectedItemId = lvItems.getSelectionModel().getSelectedIndex();
//        if (selectedItemId != -1) 
//        {
//          Object itemToRemove = lvItems.getSelectionModel().getSelectedItem();
//          lvItems.getItems().remove(selectedItemId);
//         
//          Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
//          alert2.setTitle("Item verwijderd");
//          alert2.setHeaderText(null);
//          alert2.setContentText("Het item is verwijderd uit de databank en kan dus niet meer uitgeleend worden! U kan verdergaan met items verwijderen of teruggaan naar het beheerscherm door op de \"Annuleren\" knop te klikken");
//          alert2.show();
          
           //verwijderen uit database
   //     }
          //code analoog aan DeleteLeerling
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Verwijder item");
          alert.setHeaderText("U staat op het punt om het item: "+items.get(lvItems.getSelectionModel().getSelectedIndex()).getNaam() + " te verwijderen");
          alert.setContentText("Bent u zeker?");
          
          Optional<ButtonType> result = alert.showAndWait();
          if (result.get() == ButtonType.OK){
              man.destroy(items.get(lvItems.getSelectionModel().getSelectedIndex()).getId());
           final int selectedItemId = lvItems.getSelectionModel().getSelectedIndex();
           if (selectedItemId != -1) 
           {
                
                lvItems.getItems().remove(selectedItemId);
           }
        
          Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
          alert2.setTitle("Verwijderd");
          alert2.setHeaderText(null);
          alert2.setContentText("Het item is verwijderd");
          alert2.showAndWait();
          
          }
          else{
              alert.close();
          }
          
          
      
    }
    
    public void cancel()
    {
        switcher.itemBeheer();
    }
}
