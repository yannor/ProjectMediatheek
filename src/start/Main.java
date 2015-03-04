package start;

import db.DbConnect;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.*;

import gui.HomePage_uit;

public class Main extends Application {

    DbConnect connect;
    @Override
    public void start(Stage stage) {//ItemBeheer itemBeheer = new ItemBeheer();

        Image applicationIcon = new Image("gui/afbeeldingen/logo_krekel.png");
        stage.getIcons().add(applicationIcon);

        Scene scene = new Scene(new HomePage_uit());
        stage.setScene(scene);
        stage.setTitle("Home");

        stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(scene.getWidth());
            stage.setMinHeight(scene.getHeight());
        });
        stage.show();
        

    }

    public static void main(String... args) {
        Application.launch(Main.class, args);
        
    }

}
