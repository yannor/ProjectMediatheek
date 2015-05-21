package gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import util.ItemManagement;

public class ItemToevoegen extends BorderPane
{
    @FXML
    private Label lblSoort,lblTitel,lblAuteur,lblThema, lblBeschrijving;
    
    @FXML
    private Button btnToevoegen,btnCancel;
    
    ScreenSwitcher switcher;
    
     public ItemToevoegen(ScreenSwitcher switcher) 
     {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemToevoegen.fxml"));
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
    public void add()
    {
        //lln toevoegen
    }
    
    @FXML
    public void cancel()
    {
        switcher.itemBeheer();
    }
}
