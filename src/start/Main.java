package start;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import item.*;
import gui.OverzichtItems;


public class Main extends Application 
{

    @Override
    public void start(Stage stage) 
    {ItemBeheer itemBeheer = new ItemBeheer();
    
    
Scene scene = new Scene(new OverzichtItems(itemBeheer));
stage.setScene(scene);
stage.setTitle("Items");
stage.setOnShown((WindowEvent t) -> {
stage.setMinWidth(stage.getWidth());
stage.setMinHeight(stage.getHeight());
});
stage.show();
    }
    
public static void main(String... args) {
        Application.launch(Main.class, args);
    }

}
