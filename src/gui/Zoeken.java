package gui;

import java.io.IOException;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import items.*;
import domein.*;
import util.*;

public class Zoeken extends BorderPane implements Screen
{

    private final ItemManagement itemManagement = new ItemManagement();
    private final UitleningRepository uitlen = new UitleningRepository();
    ScreenSwitcher switcher;

    @FXML
    private ListView listItems, listLiedjes, listExemplaren;

    @FXML
    private Button btnAllemaal, btnCds, btnBoeken, btnDvds, btnSpellen, btnTassen, btnLeenUit;

    ObservableList<Item> items;
    ObservableList<Item> exemplaren;

    @FXML
    private TextField txtAuteur, txtUitgever, txtZanger, txtUitgeverij;

    @FXML
    private TextArea txaBeschrijving;

    @FXML
    private TextField txtAantal1, txtLeeftijd1, txtAantal, txtLeeftijd, txtAantal4, txtLeeftijd4,
            txtAantal2, txtLeeftijd2, txtAantal3, txtLeeftijd3;

    @FXML
    private Label lblNaam, lblThema1, lblThema2, lblThema3, lblThema4, lblThema5, lblNaam1, lblThema11, lblThema21, lblThema31, lblThema41, lblThema51,
            lblNaam2, lblThema12, lblThema22, lblThema32, lblThema42, lblThema52, lblNaam3, lblThema13, lblThema23, lblThema33, lblThema43, lblThema53,
            lblNaam4, lblThema14, lblThema24, lblThema34, lblThema44, lblThema54;

    @FXML
    private Tab tabBoek, tabCd, tabDvd, tabSpel, tabTas;

    @FXML
    private Button btnHome, btnAanmelden;

    @FXML
    private TextField txtZoeken;

    @FXML
    private TabPane detailsPane;

    public Zoeken(ScreenSwitcher switcher)
    {

        this.switcher = switcher;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Zoeken.fxml"));
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
        items = FXCollections.observableArrayList();

        if (switcher.getGebruiker().getTypeGebruiker() == TypeGebruiker.ADMIN)
        {
            btnHome.setVisible(true);
            btnHome.setDisable(false);
            btnAanmelden.setVisible(false);
        }
        else if (switcher.getGebruiker().getTypeGebruiker() == TypeGebruiker.ANDERE)
        {
            btnHome.setVisible(true);
            btnHome.setDisable(false);
            btnAanmelden.setVisible(false);
        }
        else if (switcher.getGebruiker().getTypeGebruiker() == TypeGebruiker.LEERLING)
        {
            btnHome.setVisible(false);
            btnHome.setDisable(true);
            btnAanmelden.setVisible(true);
        }
        else
        {
            System.err.println("Geen gebruiker");
        }

        items.addAll(itemManagement.getItems());

        listItems.setItems(items);

        btnAllemaal.setTextFill(Color.RED);
        tabBoek.setDisable(true);
        tabCd.setDisable(true);
        tabDvd.setDisable(true);
        tabSpel.setDisable(true);
        tabTas.setDisable(true);

    }

    @FXML
    public void aanmelden()
    {
        switcher.setTop(new Login(switcher));
    }

    @FXML
    public void home()
    {
        switcher.homePageIn();
    }

    @FXML
    public void allemaal()
    {

        items.clear();
        items.addAll(itemManagement.getItems());

        listItems.setItems(items);

        veranderGeselecteerdButton(1);

    }

    @FXML
    public void cds()
    {

        items.clear();
        for (int x = 0; x < itemManagement.getItems().size(); x++)
        {
            if (itemManagement.getItems().get(x) instanceof Cd)
            {
                items.add(itemManagement.getItems().get(x));
            }
        }

        listItems.setItems(items);
        veranderGeselecteerdButton(3);
    }

    @FXML
    public void dvds()
    {

        items.clear();
        for (int x = 0; x < itemManagement.getItems().size(); x++)
        {
            if (itemManagement.getItems().get(x) instanceof Dvd)
            {
                items.add(itemManagement.getItems().get(x));
            }
        }

        listItems.setItems(items);

        veranderGeselecteerdButton(4);
    }

    @FXML
    public void spellen()
    {
        items.clear();
        for (int x = 0; x < itemManagement.getItems().size(); x++)
        {
            if (itemManagement.getItems().get(x) instanceof Spel)
            {
                items.add(itemManagement.getItems().get(x));
            }
        }

        listItems.setItems(items);

        veranderGeselecteerdButton(5);
    }

    @FXML
    public void tassen()
    {
        items.clear();
        for (int x = 0; x < itemManagement.getItems().size(); x++)
        {
            if (itemManagement.getItems().get(x) instanceof Verteltas)
            {
                items.add(itemManagement.getItems().get(x));
            }
        }

        listItems.setItems(items);

        veranderGeselecteerdButton(6);

    }

    @FXML
    public void boeken()
    {
        items.clear();
        for (int x = 0; x < itemManagement.getItems().size(); x++)
        {
            if (itemManagement.getItems().get(x) instanceof Boek)
            {
                items.add(itemManagement.getItems().get(x));
            }
        }

        listItems.setItems(items);
        veranderGeselecteerdButton(2);

    }

    public void leenUit(Item ex, Gebruiker gebr)
    {
        uitlen.uitleningToevoegen(new Uitlening(ex, gebr));
    }

    public void selectItem()
    {

        Item plaatsLijst = (Item) listItems.getSelectionModel().getSelectedItem(); //plaats in de lijst van het geslecteerde item
        int d = plaatsLijst.getId(); //id van het geslecteerde item

        if (listItems.getSelectionModel().getSelectedItem() instanceof Boek)
        {

            sluitTabs(1);
            for (int y = 0; y < itemManagement.getItems().size(); y++)
            {
                if (itemManagement.getItems().get(y).getId() == d)
                {
                    Boek hulp = (Boek) itemManagement.getItems().get(y);
                    lblNaam.setText(hulp.getNaam());
                    
                    txtLeeftijd.setText(hulp.getLeeftijd());

                    txtAuteur.setText(hulp.getAuteur());
                    txtUitgever.setText(hulp.getUitgever());
                    txaBeschrijving.setText(hulp.getBeschrijving());

                   // lblThema1.setText();
                    lblThema2.setText("");
                    lblThema3.setText("");
                    lblThema4.setText("");
                    lblThema5.setText("");
                }
            }
        }

        if (listItems.getSelectionModel().getSelectedItem() instanceof Cd)
        {

            sluitTabs(2);
            for (int y = 0; y < itemManagement.getItems().size(); y++)
            {

                if (itemManagement.getItems().get(y).getId() == d)
                {
                    Cd hulp = (Cd) itemManagement.getItems().get(y);
                    lblNaam1.setText(hulp.getNaam());
                  
                    txtLeeftijd1.setText(hulp.getLeeftijd());

                  
                  

                    lblThema11.setText("");
                    lblThema21.setText("");
                    lblThema31.setText("");
                    lblThema41.setText("");
                    lblThema51.setText("");
                }
            }
        }

        if (listItems.getSelectionModel().getSelectedItem() instanceof Dvd)
        {

            sluitTabs(3);
            for (int y = 0; y < itemManagement.getItems().size(); y++)
            {
                if (itemManagement.getItems().get(y).getId() == d)
                {
                    Dvd hulp = (Dvd) itemManagement.getItems().get(y);

                    lblNaam2.setText(hulp.getNaam());
                   
                    txtLeeftijd2.setText(hulp.getLeeftijd());

                    lblThema12.setText("");
                    lblThema22.setText("");
                    lblThema32.setText("");
                    lblThema42.setText("");
                    lblThema52.setText("");
                }
            }
        }

        if (listItems.getSelectionModel().getSelectedItem() instanceof Spel)
        {

            sluitTabs(4);
            for (int y = 0; y < itemManagement.getItems().size(); y++)
            {
                if (itemManagement.getItems().get(y).getId() == d)
                {
                    Spel hulp = (Spel) itemManagement.getItems().get(y);

                    lblNaam3.setText(hulp.getNaam());
                   
                    txtLeeftijd3.setText(hulp.getLeeftijd());
                    txtUitgeverij.setText(hulp.getUitgeverij());

                    lblThema13.setText("");
                    lblThema23.setText("");
                    lblThema33.setText("");
                    lblThema43.setText("");
                    lblThema53.setText("");
                }
            }
        }

        if (listItems.getSelectionModel().getSelectedItem() instanceof Verteltas)
        {

            sluitTabs(5);
            for (int y = 0; y < itemManagement.getItems().size(); y++)
            {
                if (itemManagement.getItems().get(y).getId() == d)
                {
                    Verteltas hulp = (Verteltas) itemManagement.getItems().get(y);
                    lblNaam4.setText(hulp.getNaam());
                   
                    txtLeeftijd4.setText(hulp.getLeeftijd());

                    for (int len = 0; len < hulp.getExInhoud().size(); len++)
                    {
                        exemplaren.add(hulp.getExInhoud().get(len));
                    }

                    listExemplaren.setItems(exemplaren);

                    lblThema14.setText("");

                    lblThema24.setText("");
                    lblThema34.setText("");
                    lblThema44.setText("");
                    lblThema54.setText("");
                }
            }
        }

    }

    public void veranderGeselecteerdButton(int x)
    {
        switch (x)
        {
            case 1:
                btnAllemaal.setTextFill(Color.RED);
                btnBoeken.setTextFill(Color.BLACK);
                btnCds.setTextFill(Color.BLACK);
                btnDvds.setTextFill(Color.BLACK);
                btnSpellen.setTextFill(Color.BLACK);
                btnTassen.setTextFill(Color.BLACK);
                break;
            case 2:
                btnAllemaal.setTextFill(Color.BLACK);
                btnBoeken.setTextFill(Color.RED);
                btnCds.setTextFill(Color.BLACK);
                btnDvds.setTextFill(Color.BLACK);
                btnSpellen.setTextFill(Color.BLACK);
                btnTassen.setTextFill(Color.BLACK);
                break;

            case 3:
                btnAllemaal.setTextFill(Color.BLACK);
                btnBoeken.setTextFill(Color.BLACK);
                btnCds.setTextFill(Color.RED);
                btnDvds.setTextFill(Color.BLACK);
                btnSpellen.setTextFill(Color.BLACK);
                btnTassen.setTextFill(Color.BLACK);
                break;
            case 4:
                btnAllemaal.setTextFill(Color.BLACK);
                btnBoeken.setTextFill(Color.BLACK);
                btnCds.setTextFill(Color.BLACK);
                btnDvds.setTextFill(Color.RED);
                btnSpellen.setTextFill(Color.BLACK);
                btnTassen.setTextFill(Color.BLACK);
                break;
            case 5:
                btnAllemaal.setTextFill(Color.BLACK);
                btnBoeken.setTextFill(Color.BLACK);
                btnCds.setTextFill(Color.BLACK);
                btnDvds.setTextFill(Color.BLACK);
                btnSpellen.setTextFill(Color.RED);
                btnTassen.setTextFill(Color.BLACK);
                break;
            case 6:
                btnAllemaal.setTextFill(Color.BLACK);
                btnBoeken.setTextFill(Color.BLACK);
                btnCds.setTextFill(Color.BLACK);
                btnDvds.setTextFill(Color.BLACK);
                btnSpellen.setTextFill(Color.BLACK);
                btnTassen.setTextFill(Color.RED);
                break;
        }
    }

    public void sluitTabs(int x)
    {
        switch (x)
        {

            case 1:
                tabBoek.setDisable(false);
                tabCd.setDisable(true);
                tabDvd.setDisable(true);
                tabSpel.setDisable(true);
                tabTas.setDisable(true);
                detailsPane.getSelectionModel().select(tabBoek);

                break;
            case 2:
                tabBoek.setDisable(true);
                tabCd.setDisable(false);
                tabDvd.setDisable(true);
                tabSpel.setDisable(true);
                tabTas.setDisable(true);
                detailsPane.getSelectionModel().select(tabCd);
                break;
            case 3:
                tabBoek.setDisable(true);
                tabCd.setDisable(true);
                tabDvd.setDisable(false);
                tabSpel.setDisable(true);
                tabTas.setDisable(true);
                detailsPane.getSelectionModel().select(tabDvd);
                break;
            case 4:
                tabBoek.setDisable(true);
                tabCd.setDisable(true);
                tabDvd.setDisable(true);
                tabSpel.setDisable(false);
                tabTas.setDisable(true);
                detailsPane.getSelectionModel().select(tabSpel);
                break;
            case 5:
                tabBoek.setDisable(true);
                tabCd.setDisable(true);
                tabDvd.setDisable(true);
                tabSpel.setDisable(true);
                tabTas.setDisable(false);
                detailsPane.getSelectionModel().select(tabTas);
                break;
        }
    }

      @FXML
    public void zoeken()
    {
        items.clear();
        
           int hulp= itemManagement.getItems().size();
           
           
           for(int x=0;x<hulp;x++)
           {
               if(itemManagement.getItems().get(x).getNaam().toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim()))
               {
                   if(items.contains(itemManagement.getItems().get(x)))
                   {
                       
                   }
                   else
                   items.add(itemManagement.getItems().get(x));
               }
               
               if(itemManagement.getItems().get(x).getLeeftijd().toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim()))
               {
                   if(items.contains(itemManagement.getItems().get(x)))
                   {
                       
                   }
                   else
                   items.add(itemManagement.getItems().get(x));
               }
              
               
              
               
                
           }
          
        
        listItems.setItems(items);
    }
}
