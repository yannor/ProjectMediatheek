package gui.zoeken;

import db.DbConnect;
import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;
import java.util.*;

import gui.HomePage;
import gui.HomePage_uit;
import items.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Zoeken extends BorderPane 
{
    @FXML private Button btnBackHome;
    @FXML private TextField txtZoeken;
    @FXML private TreeView<String> treeView;

    TreeItem<String> itemRoot, boekenRoot, cdRoot, dvdRoot, spellenRoot, vertelRoot;

    String selected; //wordt later int die het id van het geselecteerd item is
    int test; //al de bovenstaand int maar enkel voor boeken

    private boolean ingelogd = false;
    private String gebruikersNaam;
    private DbConnect connect = new DbConnect();

    private List<String> cdList = new ArrayList<>();
    private List<String> dvdList = new ArrayList<>();
    private List<String> spelList = new ArrayList<>();
    private List<String> vertelList = new ArrayList<>();
    private List<String> boekList = new ArrayList<>();
    
    private List<Integer> keys = new ArrayList<>();
    
    private HashMap<Integer, String> boekMap = new HashMap<>();
    private HashMap<Integer, String> cdMap = new HashMap<>();
    private HashMap<Integer, String> dvdMap = new HashMap<>();
    private HashMap<Integer, String> spelMap = new HashMap<>();
    private HashMap<Integer, String> vertelMap = new HashMap<>();

    public Zoeken(boolean ingelogd, String gebruikersNaam) 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ZoekPage.fxml"));
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

        this.ingelogd = ingelogd;
        this.gebruikersNaam = gebruikersNaam;

        itemRoot = new TreeItem<>();
        boekenRoot = new TreeItem<>("Boeken");
        cdRoot = new TreeItem<>("Cd's");
        dvdRoot = new TreeItem<>("Dvd's");
        spellenRoot = new TreeItem<>("Gezelschapsspellen");
        vertelRoot = new TreeItem<>("Verteltassen");
        
        List<Boek> alleBoeken = connect.getAlleBoeken();
        for (int i = 0; i < alleBoeken.size(); i++) 
        {
            boekList.add(alleBoeken.get(i).getNaam());
            boekMap.put(alleBoeken.get(i).getId(), alleBoeken.get(i).getNaam());
        }

        List<Cd> alleCds = connect.getAlleCds();
        for (int i = 0; i < alleCds.size(); i++) 
        {
            cdList.add(alleCds.get(i).getNaam());
            cdMap.put(alleCds.get(i).getId(), alleCds.get(i).getNaam());
        }
        
        List<Dvd> alleDvds = connect.getAlleDvds();
        for (int i = 0; i < alleDvds.size(); i++) 
        {
            dvdList.add(alleDvds.get(i).getNaam());
            dvdMap.put(alleDvds.get(i).getId(), alleDvds.get(i).getNaam());
        }
        
        List<Spel> alleSpellen = connect.getAlleSpelen();
        for (int i = 0; i < alleSpellen.size(); i++) 
        {
            spelList.add(alleSpellen.get(i).getNaam());
            spelMap.put(alleSpellen.get(i).getId(), alleSpellen.get(i).getNaam());
        }
        
        List<Verteltas> alleVerteltassen = connect.getAlleVerteltassen();
        for (int i = 0; i < alleVerteltassen.size(); i++) 
        {
            vertelList.add(alleVerteltassen.get(i).getNaam());
            vertelMap.put(alleVerteltassen.get(i).getId(), alleVerteltassen.get(i).getNaam());
        }
        
        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() 
        {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oud, TreeItem<String> nieuw) 
            {
                select();
            }
        });
        
        vulTreeView(boekMap, cdMap, dvdMap, spelMap, vertelMap);

        itemRoot.getChildren().addAll(boekenRoot, cdRoot, dvdRoot, spellenRoot, vertelRoot);
    }

    @FXML
    public void backHome() 
    {
        if (ingelogd) 
        {
            Scene scene = new Scene(new HomePage(gebruikersNaam));
            Stage stage = new Stage();
            Image applicationIcon = new Image("gui/afbeeldingen/logo_krekel.png");
            stage.getIcons().add(applicationIcon);
            stage.close();
            Stage dezeStage = (Stage) btnBackHome.getScene().getWindow();
            dezeStage.close();
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.setOnShown((WindowEvent t) -> 
            {
                stage.setMinWidth(scene.getWidth());
                stage.setMinHeight(scene.getHeight());
            });
            stage.show();
        } 
        else 
        {
            Scene scene = new Scene(new HomePage_uit());
            Stage stage = new Stage();
            stage.close();
            Image applicationIcon = new Image("gui/afbeeldingen/logo_krekel.png");
            stage.getIcons().add(applicationIcon);
            Stage dezeStage = (Stage) btnBackHome.getScene().getWindow();
            dezeStage.close();
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.setOnShown((WindowEvent t) -> 
            {
                stage.setMinWidth(scene.getWidth());
                stage.setMinHeight(scene.getHeight());
            });
            stage.show();
        }
    }

    public void vulTreeView(HashMap mapBoek, HashMap mapCd, HashMap mapDvd, HashMap mapSpel, HashMap mapVertel) 
    {
        Iterator it = mapBoek.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry pair = (Map.Entry)it.next();
            TreeItem<String> boek = new TreeItem<>(pair.getValue().toString());
            boekenRoot.getChildren().add(boek);
            keys.add((Integer) pair.getKey());
        }

        it = mapCd.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry pair = (Map.Entry)it.next();
            TreeItem<String> cd = new TreeItem<>(pair.getValue().toString());
            cdRoot.getChildren().add(cd);
            keys.add((Integer) pair.getKey());
        }
        
        it = mapDvd.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry pair = (Map.Entry)it.next();
            TreeItem<String> dvd = new TreeItem<>(pair.getValue().toString());
            dvdRoot.getChildren().add(dvd);
            keys.add((Integer) pair.getKey());
        }
        
        it = mapSpel.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry pair = (Map.Entry)it.next();
            TreeItem<String> spel = new TreeItem<>(pair.getValue().toString());
            spellenRoot.getChildren().add(spel);
            keys.add((Integer) pair.getKey());
        }
        
        it = mapVertel.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry pair = (Map.Entry)it.next();
            TreeItem<String> vertel = new TreeItem<>(pair.getValue().toString());
            vertelRoot.getChildren().add(vertel);
            keys.add((Integer) pair.getKey());
        }

        boekenRoot.setValue("Boeken (" + mapBoek.size() + " boeken)");
        cdRoot.setValue("Cd's (" + mapCd.size() + " cd's)");
        dvdRoot.setValue("Dvd's (" + mapDvd.size() + " dvd's)");
        spellenRoot.setValue("Gezelschapsspellen (" + mapSpel.size() + " spelen)");
        vertelRoot.setValue("Verteltassen (" + mapVertel.size() + " tassen)");

        if (mapBoek.size() == 1) 
        {
            boekenRoot.setValue("Boeken (" + mapBoek.size() + " boek)");
        }
        if (mapCd.size() == 1) 
        {
            cdRoot.setValue("Cd's (" + mapCd.size() + " cd)");
        }
        if (mapDvd.size() == 1) 
        {
            dvdRoot.setValue("Dvd's (" + mapDvd.size() + " dvd)");
        }
        if (mapSpel.size() == 1) 
        {
            spellenRoot.setValue("Gezelschapsspellen (" + mapSpel.size() + " spel)");
        }
        if (mapVertel.size() == 1) 
        {
            vertelRoot.setValue("Verteltassen (" + mapVertel.size() + " tas)");
        }

        treeView.setRoot(itemRoot);
        treeView.setShowRoot(false);
    }

    private void removeOld() 
    {
        keys = new ArrayList<>();
        for (int i = 0; i < cdRoot.getChildren().size(); i++) 
        {
            cdRoot.getChildren().clear();
        }

        for (int i = 0; i < dvdRoot.getChildren().size(); i++) 
        {
            dvdRoot.getChildren().clear();
        }

        for (int i = 0; i < spellenRoot.getChildren().size(); i++) 
        {
            spellenRoot.getChildren().clear();
        }

        for (int i = 0; i < vertelRoot.getChildren().size(); i++) 
        {
            vertelRoot.getChildren().clear();
        }

        for (int i = 0; i < boekenRoot.getChildren().size(); i++) 
        {
            boekenRoot.getChildren().clear();
        }
    }

    @FXML
    private void select() 
    {
        try
        {
//            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(itemRoot))
//            {
//            }

            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(boekenRoot))
            {
                int selectedId = keys.get(treeView.getSelectionModel().getSelectedIndex() - 1);
                System.out.println("Selected id: " + selectedId);
                System.out.println("Selected index: " + (treeView.getSelectionModel().getSelectedIndex() - 1));
                Boek boek = connect.getBoek(selectedId);

                //ander pane
                setCenter(null);
                setCenter(new BoekenPaneController(boek, ingelogd));
            }

            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(cdRoot)) 
            {
                int i = 0;
                if(!boekenRoot.isExpanded())
                {
                    i += boekenRoot.getChildren().size();
                }
                int selectedId = keys.get(treeView.getSelectionModel().getSelectedIndex() - 2 + i);
                System.out.println("Selected id: " + selectedId);
                System.out.println("Selected index: " + (treeView.getSelectionModel().getSelectedIndex() - 2 + i));
                i = 0;
                Cd cd = connect.getCd(selectedId);
                //ander pane
                setCenter(null);
                setCenter(new CdsPaneController(cd, ingelogd));
            }

            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(dvdRoot)) 
            {
                int i = 0;
                if(!boekenRoot.isExpanded())
                {
                    i += boekenRoot.getChildren().size();
                }
                if(!cdRoot.isExpanded())
                {
                    i += cdRoot.getChildren().size();
                }
                int selectedId = keys.get(treeView.getSelectionModel().getSelectedIndex() - 3 + i);
                
                System.out.println("Selected id: " + selectedId);
                System.out.println("Selected index: " + (treeView.getSelectionModel().getSelectedIndex() - 3 + i));
                Dvd dvd = connect.getDvd(selectedId);
                
                setCenter(null);
                setCenter(new DvdsPaneController(dvd, ingelogd));
            }

            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(spellenRoot)) 
            {
                int i = 0;
                if(!boekenRoot.isExpanded())
                {
                    i += boekenRoot.getChildren().size();
                }
                if(!cdRoot.isExpanded())
                {
                    i += cdRoot.getChildren().size();
                }
                if(!dvdRoot.isExpanded())
                {
                    i += dvdRoot.getChildren().size();
                }
                int selectedId = keys.get(treeView.getSelectionModel().getSelectedIndex() - 4 + i);
                
                System.out.println("Selected id: " + selectedId);
                System.out.println("Selected index: " + (treeView.getSelectionModel().getSelectedIndex() - 4 + i));
                Spel spel = connect.getSpel(selectedId);
                
                setCenter(null);
                setCenter(new SpellenPaneController(spel, ingelogd));
            }

            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(vertelRoot)) 
            {
                int i = 0;
                if(!boekenRoot.isExpanded())
                {
                    i += boekenRoot.getChildren().size();
                }
                if(!cdRoot.isExpanded())
                {
                    i += cdRoot.getChildren().size();
                }
                if(!dvdRoot.isExpanded())
                {
                    i += dvdRoot.getChildren().size();
                }
                if(!spellenRoot.isExpanded())
                {
                    i += spellenRoot.getChildren().size();
                }

                int selectedId = keys.get(treeView.getSelectionModel().getSelectedIndex() - 5 + i);
                
                System.out.println("Selected id: " + selectedId);
                System.out.println("Selected index: " + (treeView.getSelectionModel().getSelectedIndex() - 5 + i));
                Verteltas verteltas = connect.getVerteltas(selectedId);
                
                setCenter(null);
                setCenter(new VerteltassenPaneController(verteltas, ingelogd));
            }
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }

    }

   
    @FXML
    public void search() 
    {
        HashMap<Integer, String> boekenWel = new HashMap<>();
        
        Iterator it = boekMap.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry pair = (Map.Entry)it.next();
            TreeItem<String> boek = new TreeItem<>(pair.getValue().toString());
            if (boek.getValue().toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim()))
            {
                boekenWel.put((Integer) pair.getKey(), pair.getValue().toString());
            }
        }

        HashMap<Integer, String> cdsWel = new HashMap<>();

        it = cdMap.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry pair = (Map.Entry)it.next();
            TreeItem<String> cd = new TreeItem<>(pair.getValue().toString());
            if (cd.getValue().toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim()))
            {
                cdsWel.put((Integer) pair.getKey(), pair.getValue().toString());
            }
        }

        HashMap<Integer, String> dvdWel = new HashMap<>();

        it = dvdMap.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry pair = (Map.Entry)it.next();
            TreeItem<String> dvd = new TreeItem<>(pair.getValue().toString());
            if (dvd.getValue().toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim()))
            {
                dvdWel.put((Integer) pair.getKey(), pair.getValue().toString());
            }
        }

        HashMap<Integer, String> vertelWel = new HashMap<>();

        it = vertelMap.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry pair = (Map.Entry)it.next();
            TreeItem<String> vertel = new TreeItem<>(pair.getValue().toString());
            if (vertel.getValue().toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim()))
            {
                vertelWel.put((Integer) pair.getKey(), pair.getValue().toString());
            }
        }

        HashMap<Integer, String> spelWel = new HashMap<>();

        it = spelMap.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry pair = (Map.Entry)it.next();
            TreeItem<String> spel = new TreeItem<>(pair.getValue().toString());
            if (spel.getValue().toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim()))
            {
                spelWel.put((Integer) pair.getKey(), pair.getValue().toString());
            }
        }

        removeOld();
        vulTreeView(boekenWel, cdsWel, dvdWel, spelWel, vertelWel);
    }
}
