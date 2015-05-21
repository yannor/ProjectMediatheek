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
    private Label lblItemsToevoegen;
    
    @FXML
    private Button btnItems,btnHome, btnBoek, btnCd, btnSpel, btnFilm, btnTas;
    
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
    public void addBoek()
    {
        switcher.addBoek();
    }
    public void addCd()
    {
        switcher.addCd();
    }
    public void addFilm()
    {
        switcher.addFilm();
    }
    public void addSpel()
    {
        switcher.addSpel();
    }
    public void addTas()
    {
        switcher.addTas();
    }
     
    @FXML
    public void items()
    {
        switcher.itemBeheer();
    }
    
    @FXML
    public void home()
    {
        switcher.homePageIn();
    }
}
