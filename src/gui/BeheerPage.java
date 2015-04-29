package gui;

import java.io.IOException;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.layout.BorderPane;

import util.ItemManagement;
import items.*;

public class BeheerPage extends BorderPane {

    ScreenSwitcher switcher;
    ItemManagement man;

    public BeheerPage(ScreenSwitcher switcher) {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeheerPage.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        man= new ItemManagement();

    }
     @FXML
    public void home()
    {
        switcher.homePageIn();
    }
    @FXML
    public void add()
    {
        List<String> testThemas= new ArrayList<>();
        testThemas.add("thema");
        for(int x=0;x<10;x++)
        {
            man.create(new Boek("Titel "+x, "leeftijd "+x,testThemas, "auteur "+x, " uitgever "+x, " beschrijving "+x, " "+x));
        
        
       
            man.create(new Cd("Titel "+x, "leeftijd "+x,testThemas, "zanger "+x, testThemas, " "+x));
        
        
        
            man.create(new Dvd("Titel "+x, "leeftijd "+x,testThemas, " "+x));
        
            man.create(new Spel("Titel "+x, "leeftijd "+x,testThemas, " uitgeverij "+x," "+x));
        }
        
       
        
    }

}
