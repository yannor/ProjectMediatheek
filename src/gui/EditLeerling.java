package gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class EditLeerling extends BorderPane 
{
    @FXML
    private Button btnEdit, btnCancel;
    
    @FXML
    private ListView lvLeerlingen;
    
    ScreenSwitcher switcher;
    
    public EditLeerling(ScreenSwitcher switcher)
    {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditLeerling.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
    }
     
    @FXML
    public void edit()
    {
        //editeerbare cellen in listview OF formulier zoals leerling toevoegen
    }
            
    @FXML
    public void cancel()
    {
        switcher.leerlingenBeheer();
    }
}
