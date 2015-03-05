package main;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class MainPanel extends BorderPane
{
    private final Screen1 screen1;
    private final Screen2 screen2;
    
    public MainPanel()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        screen1 = new Screen1();
        screen2 = new Screen2();
        setCenter(screen1);
        screen1.show();
    }
    
    @FXML private void screen1()
    {
        ((Screen)getCenter()).hide();
        setCenter(screen1);
        screen1.show();
    }
    
    @FXML private void screen2()
    {
        ((Screen)getCenter()).hide();
        setCenter(screen2);
        screen2.show();
    }
}
