package gui;

import domein.Gebruiker;
import domein.TypeGebruiker;
import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import util.ItemManagement;

public class HomePage extends Pane implements Screen
{

    ScreenSwitcher switcher;
    @FXML
    private Button btnUitloggen, btnZoeken, btnUitleningen, btnBeheer;

    ItemManagement it = new ItemManagement();

    @FXML
    private Label lblWelkom;

    public HomePage(ScreenSwitcher switcher)
    {

        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try
        {
            loader.load();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        lblWelkom.setText("Welkom " + this.switcher.getGebruiker().getVoorNaam() + " " + this.switcher.getGebruiker().getNaam());
        if(this.switcher.getGebruiker().getTypeGebruiker() == TypeGebruiker.ADMIN)
        {
            
        }
        else if(this.switcher.getGebruiker().getTypeGebruiker() == TypeGebruiker.ANDERE)
        {
            btnBeheer.setDisable(true);
            btnBeheer.setVisible(false);
        }
        // mag niet, leerling mag enkel zoeken en niet naar de homepage komen
        else if(this.switcher.getGebruiker().getTypeGebruiker() == TypeGebruiker.LEERLING)
        {
            System.err.println("Toegang geweigerd");
            this.switcher.zoeken();
        }
    }

    @FXML
    public void afmelden()
    {
        switcher.setGebruiker(new Gebruiker());
        switcher.zoeken();

    }
//

    @FXML
    public void zoeken()
    {

        switcher.zoeken();

    }
//

    @FXML
    public void uitleningTerugbrengen()
    {

        switcher.uitleningen();

    }
//
//    @FXML
//    public void beheer() {
//
//        Stage stage = new Stage();
//        //stage.close();
//        Image applicationIcon = new Image("gui/afbeeldingen/logo_krekel.png");
//        stage.getIcons().add(applicationIcon);
//        Stage dezeStage = (Stage) btnBeheer.getScene().getWindow();
//        dezeStage.close();
//
//        Scene scene = new Scene(new gui.instellingen.BeheerVrij(gebruikersNaam));
//        stage.setScene(scene);
//        stage.setTitle("Beheer");
//
//        stage.show();
//
//    }

    public void beheer()
    {
        switcher.beheer();

    }
}
