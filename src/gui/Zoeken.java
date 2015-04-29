package gui;


import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import util.ItemManagement;
import items.*;
import domein.*;
import java.util.ArrayList;
import java.util.List;
import util.*;
/**
 *
 * @author Yannick
 */
public class Zoeken extends BorderPane implements Screen {

   private final ItemManagement itemManagement= new ItemManagement();
    private final UitleningRepository uitlen= new UitleningRepository();
     ScreenSwitcher switcher;
     
     @FXML
     private ListView listItems;
     
     @FXML
             private Button btnAllemaal, btnCds, btnBoeken, btnDvds, btnSpellen, btnTassen;
     
     ObservableList<Item> items;
     
     @FXML
     private TextField txtAuteur, txtAantal, txtLeeftijd, txtUitgever;
     
     @FXML
     private TextArea txaBeschrijving;
     
      @FXML
     private Label lblNaam, lblThema1, lblThema2, lblThema3, lblThema4, lblThema5;
     
      @FXML
     private Tab tabBoek, tabCd, tabDvd, tabSpel, tabTas;
     
     
     @FXML
     private Button btnHome, btnAanmelden;

    public Zoeken(ScreenSwitcher switcher, boolean aangemeld) {

        this.switcher=switcher;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Zoeken.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        items= FXCollections.observableArrayList();
        
       btnHome.setVisible(aangemeld);
       btnHome.setDisable(!aangemeld);
        btnAanmelden.setVisible(!aangemeld);
       
        
       items.addAll(itemManagement.getItems());
      
       listItems.setItems(items);
       
       btnAllemaal.setTextFill(Color.RED);
                tabBoek.setDisable(true);
                tabCd.setDisable(true);
                tabDvd.setDisable(true);
                tabSpel.setDisable(true);
                tabTas.setDisable(true);
       
       
       
    }

     @FXML
    public void aanmelden() {

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Aanmelden");
        dialog.setHeaderText("Wat is jouw naam");


        Optional<String> result = dialog.showAndWait();
        String inlog = result.toString().substring(9, result.toString().length() - 1);

        if (inlog.equalsIgnoreCase("Yannick")) {

           
            switcher.homePageIn();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aanmelden");
            alert.setHeaderText("Foute gebruikersnaam");

            alert.showAndWait();
        }
        
        

    }


  @FXML
    public void home() {
        switcher.homePageIn();
    }

 @FXML
    public void allemaal() {
       
 
        items.clear();
        items.addAll(itemManagement.getItems());
       
        listItems.setItems(items);
        
       veranderGeselecteerdButton(1); 
        
        
    }
    
    @FXML
    public void cds() {
       
          items.clear();
        for(int x=0;x<itemManagement.getItems().size();x++)
        {
            if(itemManagement.getItems().get(x) instanceof Cd)
            {
                items.add(itemManagement.getItems().get(x));
            }
        }

        listItems.setItems(items);
         veranderGeselecteerdButton(3);
    }
    
    @FXML
    public void dvds() {
       
        items.clear();
        for(int x=0;x<itemManagement.getItems().size();x++)
        {
            if(itemManagement.getItems().get(x) instanceof Dvd)
            {
                items.add(itemManagement.getItems().get(x));
            }
        }

        listItems.setItems(items);
        
         veranderGeselecteerdButton(4);
    }
    
    @FXML
    public void spellen() {
       items.clear();
        for(int x=0;x<itemManagement.getItems().size();x++)
        {
            if(itemManagement.getItems().get(x) instanceof Spel)
            {
                items.add(itemManagement.getItems().get(x));
            }
        }

        listItems.setItems(items);
        
         veranderGeselecteerdButton(5); 
    }
    
    @FXML
    public void tassen() {
     items.clear();
        for(int x=0;x<itemManagement.getItems().size();x++)
        {
            if(itemManagement.getItems().get(x) instanceof Verteltas)
            {
                items.add(itemManagement.getItems().get(x));
            }
        }

        listItems.setItems(items);
        
         veranderGeselecteerdButton(6);   
    }
    
    @FXML
    public void boeken() {
       items.clear();
        for(int x=0;x<itemManagement.getItems().size();x++)
        {
            if(itemManagement.getItems().get(x) instanceof Boek)
            {
                items.add(itemManagement.getItems().get(x));
            }
        }

        listItems.setItems(items); 
        veranderGeselecteerdButton(2);
        
    }

   
    
    public void leenUit(Exemplaar ex, Gebruiker gebr) 
    {
        uitlen.uitleningToevoegen(new Uitlening(ex, gebr));
    }

    
    public void selectItem()
    {
          int plaatsLijst=listItems.getSelectionModel().getSelectedIndex(); //plaats in de lijst van het geslecteerde item
          int d= itemManagement.getItems().get(plaatsLijst).getId(); //id van het geslecteerde item
          
       
       if(itemManagement.getItems().get(plaatsLijst) instanceof Boek)
       {
           
          sluitTabs(1); 
          for(int y=0;y<itemManagement.getItems().size();y++)
          {
              if(itemManagement.getBoeken().get(y).getId()==d)
              {
                   lblNaam.setText( itemManagement.getBoeken().get(y).getNaam());
                   txtAantal.setText( itemManagement.getBoeken().get(y).getAantal());
                    txtAuteur.setText( itemManagement.getBoeken().get(y).getAuteur());
                     txtUitgever.setText( itemManagement.getBoeken().get(y).getUitgever());
                     txtLeeftijd.setText( itemManagement.getBoeken().get(y).getLeeftijd());
                     txaBeschrijving.setText( itemManagement.getBoeken().get(y).getBeschrijving());
                     
                     
                    
                     lblThema1.setText("");
                     
                  
                     
                     
                     lblThema2.setText( "");
                     lblThema3.setText( "");
                     lblThema4.setText("");
                     lblThema5.setText( "");
              }
          }

       }
       
    }
    
    
    
    
    
    
    
     public void veranderGeselecteerdButton(int x)
    {
        switch(x)
        {
            case 1:  
                btnAllemaal.setTextFill(Color.RED);
                btnBoeken.setTextFill(Color.BLACK);
                btnCds.setTextFill(Color.BLACK);
                btnDvds.setTextFill(Color.BLACK);
                btnSpellen.setTextFill(Color.BLACK);
                btnTassen.setTextFill(Color.BLACK);
                break;
            case 2:
                btnAllemaal.setTextFill(Color.BLACK);
                btnBoeken.setTextFill(Color.RED);
                btnCds.setTextFill(Color.BLACK);
                btnDvds.setTextFill(Color.BLACK);
                btnSpellen.setTextFill(Color.BLACK);
                btnTassen.setTextFill(Color.BLACK);
                break;
                
            case 3:
                btnAllemaal.setTextFill(Color.BLACK);
                btnBoeken.setTextFill(Color.BLACK);
                btnCds.setTextFill(Color.RED);
                btnDvds.setTextFill(Color.BLACK);
                btnSpellen.setTextFill(Color.BLACK);
                btnTassen.setTextFill(Color.BLACK);
                break;
            case 4:
                btnAllemaal.setTextFill(Color.BLACK);
                btnBoeken.setTextFill(Color.BLACK);
                btnCds.setTextFill(Color.BLACK);
                btnDvds.setTextFill(Color.RED);
                btnSpellen.setTextFill(Color.BLACK);
                btnTassen.setTextFill(Color.BLACK);
                break;
            case 5:
                btnAllemaal.setTextFill(Color.BLACK);
                btnBoeken.setTextFill(Color.BLACK);
                btnCds.setTextFill(Color.BLACK);
                btnDvds.setTextFill(Color.BLACK);
                btnSpellen.setTextFill(Color.RED);
                btnTassen.setTextFill(Color.BLACK);
                break;
            case 6:
                btnAllemaal.setTextFill(Color.BLACK);
                btnBoeken.setTextFill(Color.BLACK);
                btnCds.setTextFill(Color.BLACK);
                btnDvds.setTextFill(Color.BLACK);
                btnSpellen.setTextFill(Color.BLACK);
                btnTassen.setTextFill(Color.RED);
                break;
        }
    }
    
     
     public void sluitTabs(int x)
     {
         switch(x)
         {
             
        
          case 1:  
                tabBoek.setDisable(false);
                tabCd.setDisable(true);
                tabDvd.setDisable(true);
                tabSpel.setDisable(true);
                tabTas.setDisable(true);
                break;
           case 2:  
                tabBoek.setDisable(true);
                tabCd.setDisable(false);
                tabDvd.setDisable(true);
                tabSpel.setDisable(true);
                tabTas.setDisable(true);
                break;
           case 3:  
               tabBoek.setDisable(true);
                tabCd.setDisable(true);
                tabDvd.setDisable(false);
                tabSpel.setDisable(true);
                tabTas.setDisable(true);
                break;
           case 4:  
                tabBoek.setDisable(true);
                tabCd.setDisable(true);
                tabDvd.setDisable(true);
                tabSpel.setDisable(false);
                tabTas.setDisable(true);
                break;
           case 5:  
               tabBoek.setDisable(true);
                tabCd.setDisable(true);
                tabDvd.setDisable(true);
                tabSpel.setDisable(true);
                tabTas.setDisable(false);
                break;
                }
     }
}

 