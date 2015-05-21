package gui;

import java.io.IOException;
import java.util.Optional;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

import util.*;
import domein.Gebruiker;

public class SelectGebruiker extends BorderPane {

    
    ItemManagement man;
    private final GebruikerRepository gebrMan= new GebruikerRepository();
    
    @FXML
    TextField txtZoeken;
    
    @FXML
    ListView listGebruikers;
    ObservableList<Gebruiker> gebruikers;

    public SelectGebruiker() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectGebruiker.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        gebruikers = FXCollections.observableArrayList();
        //man= new ItemManagement();
        gebruikers.addAll(gebrMan.getAlleGebruikers());
        listGebruikers.setItems(gebruikers);

        
    }
    
    
    
    public void zoeken()
    {
 
         gebruikers.clear();
        
           int hulp= gebrMan.getAlleGebruikers().size();
           
           
           for(int x=0;x<hulp;x++)
           {
               if(gebrMan.getAlleGebruikers().get(x).getNaam().toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim()))
               {
                  
                   gebruikers.add(gebrMan.getAlleGebruikers().get(x));
               }
               else
               
               if(gebrMan.getAlleGebruikers().get(x).getVoorNaam().toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim()))
               {
                  gebruikers.add(gebrMan.getAlleGebruikers().get(x));
               }
               else
               if(gebrMan.getAlleGebruikers().get(x).getKlas().toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim()))
               {
                   gebruikers.add(gebrMan.getAlleGebruikers().get(x));
               }   
                
           }
          
        
        listGebruikers.setItems(gebruikers);
    }
    
    
    public void verwijderGebruiker()
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Verwijder leerling");
alert.setHeaderText("U staat op het punt om "+gebruikers.get(listGebruikers.getSelectionModel().getSelectedIndex()).getVoorNaam()+" "+
        gebruikers.get(listGebruikers.getSelectionModel().getSelectedIndex()).getNaam()+" te verwijderen.");
alert.setContentText("Bent u zeker?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
     gebrMan.destroy(gebruikers.get(listGebruikers.getSelectionModel().getSelectedIndex()).getId());
     
     
     Alert alert2 = new Alert(AlertType.INFORMATION);
alert2.setTitle("Verwijderd");
alert2.setHeaderText(null);
alert2.setContentText("De gebruiker is verwijderd");

alert2.showAndWait();
gebruikers=gebrMan.getAlleGebruikers();
     listGebruikers.setItems(gebruikers);
} else {
    alert.close();
     
}
      
    }
    
}