package gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import util.ItemManagement;


public class BeheerItems extends BorderPane
{
    @FXML
    private Button btnHome, btnAdd, btnDelete, btnEdit, btnExcel, btnHomeBeheer;
    
    @FXML
    private Label lblBeheerItems;
    
    ScreenSwitcher switcher;
    
    //constructor switcher maken..ik krijg altijd een wit scherm ipv mijne gui als ik het doe zoals bij andere pagina's
    //indien nodig ook klasse switcher zelf aanpassen
    public BeheerItems(ScreenSwitcher switcher) {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeheerItems.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       ItemManagement man = new ItemManagement();

    }
    @FXML
    private void home()
    {
      switcher.homePageIn();
    }
    @FXML
    private void add()
    {
        switcher.itemToevoegen();
    }
    @FXML
    private void delete()
    {
       switcher.deleteItem();
        //voorstel: opent een listview van alle items met bovenaan zoekfunctie, item selecteren, knop verwijderen
        //melding "bent u zeker?" -> ok = verwijderd, cancel = terug naar listview
    }
    @FXML
    private void edit()
    {
        switcher.editItem();
      
        //voorstel: opent zoekpage maar met mogelijkheid om details aan te passen, wijzigingen slaan vanzelf op zonder
        //knop om te bevestigen, dus GEEN opslaan knop
    }
    @FXML
    private void importExcel()
    {
        //nog te maken
        //filebrowser opent -> excel bestand selecteren -> excel wordt geimporteerd
    }
    
    @FXML
    private void homeBeheer()
    {
        switcher.beheer();
    }
}
