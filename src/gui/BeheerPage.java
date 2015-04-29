package gui;

import items.Boek;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import util.ItemManagement;
/**
 *
 * @author Yannick
 */
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
    public void add()
    {
        List<String> testThemas= new ArrayList<>();
        testThemas.add("thema");
        man.create(new Boek(" titel", " leeftijd",testThemas, " auteur", " uitgever", " beschrijving", " aantal"));
    }

}
