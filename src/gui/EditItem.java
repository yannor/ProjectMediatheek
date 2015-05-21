package gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import util.ItemManagement;

public class EditItem  extends BorderPane
{
    @FXML
    private Button btnCancel, btnEdit;
    
    @FXML
    private ListView lvItems;
    
    ScreenSwitcher switcher;
    
     public EditItem(ScreenSwitcher switcher) 
     {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditItem.fxml"));
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
    public void edit()
    {
        //editeerbare cellen in listview OF gui zoals bij ItemToevoegen???
    }
    
    @FXML
    public void cancel()
    {
        switcher.itemBeheer();
    }
}
