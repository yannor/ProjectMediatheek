package main;

import gui.ScreenSwitcher;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Yannick
 */

public class Mediatheek extends Application {

    @Override
    public void start(Stage primaryStage) {
	

	//HomePage_uit home= new HomePage_uit();
	primaryStage.setTitle("De Lettertuin");
	//primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/resources/gui/img/logo_krekel.png")));
        ScreenSwitcher screen = new ScreenSwitcher();
	screen.zoeken(false);
	//Scene scene = new Scene(switcher);
        
        Scene scene = new Scene(screen);
	primaryStage.setScene(scene);
	primaryStage.show();
	//ScenicView.show(scene);
    }

    public static void main(String[] args) {
	launch(args);
    }

}
