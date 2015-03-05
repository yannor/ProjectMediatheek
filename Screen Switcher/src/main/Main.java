package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage stage)
    {
        MainPanel root = new MainPanel();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Screen switching");
        stage.show();
    }

    public static void main(String... args)
    {
        Application.launch(Main.class, args);
    }
}
