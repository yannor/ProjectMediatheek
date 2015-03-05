package main;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Screen1 extends AnchorPane implements Screen
{
    @FXML private Label label;
    
    public Screen1()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Screen1.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void show()
    {
        label.setText("Screen 1 is showing");
    }

    @Override
    public void hide()
    {
        label.setText("Screen 1 is hidden");
    }
}
