package gui.zoeken;

import db.DbConnect;
import gui.HomePage;
import gui.HomePage_uit;
import items.Boek;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ZoekBoeken extends BorderPane
{
    @FXML
    private Button btnBackHome, btnLeen;

    @FXML
    private Label lblNaam, lblThema, lblLeeftijd, lblAantal, lblBeschrijving, lblAuteur, lblPagLen;

    @FXML
    private TextField txtZoeken, txtThema, txtLeeftijd, txtAantal, txtAuteur, txtPagLen;

    @FXML
    private TextArea txaBeschrijving;

    @FXML
    private TreeView<String> treeView;

    TreeItem<String> itemRoot, boekenRoot, cdRoot, dvdRoot, spellenRoot, vertelRoot;

    String selected; //wordt later int die het id van het geselecteerd item is
    int test; //al de bovenstaand int maar enkel voor boeken

    private boolean ingelogd = false;
    private String gebruikersNaam;
    private DbConnect connect = new DbConnect();

    private List<String> boekList = new ArrayList<>();

    public ZoekBoeken(boolean ingelogd, String gebruikersNaam)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ZoekBoeken.fxml"));
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

        for (int i = 0; i < connect.getAlleBoeken().size(); i++) 
        {
            boekList.add(connect.getAlleBoeken().get(i).getNaam());
        }

        vulTreeView(boekList);

        itemRoot.getChildren().addAll(boekenRoot);
        btnLeen.setVisible(ingelogd);
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
            //stage.close();
            Stage dezeStage = (Stage) btnBackHome.getScene().getWindow();
            dezeStage.close();
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.setOnShown((WindowEvent t) -> {
                stage.setMinWidth(scene.getWidth());
                stage.setMinHeight(scene.getHeight());
            });
            stage.show();
        } 
        else
        {
            Scene scene = new Scene(new HomePage_uit());
            Stage stage = new Stage();
            //stage.close();
            Image applicationIcon = new Image("gui/afbeeldingen/logo_krekel.png");
            stage.getIcons().add(applicationIcon);
            Stage dezeStage = (Stage) btnBackHome.getScene().getWindow();
            dezeStage.close();
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.setOnShown((WindowEvent t) -> {
                stage.setMinWidth(scene.getWidth());
                stage.setMinHeight(scene.getHeight());
            });
            stage.show();
        }
    }

    public void vulTreeView(List listBoek) 
    {
        basisVisibleZetten(false);
        lblPagLen.setVisible(false);
        txtPagLen.setVisible(false);
        lblAuteur.setVisible(false);
        txtAuteur.setVisible(false);
        lblBeschrijving.setVisible(false);
        txaBeschrijving.setVisible(false);

        int lenB = listBoek.size();

        for (int i = 0; i < lenB; i++) 
        {
            TreeItem<String> boek = new TreeItem<>(listBoek.get(i).toString());
            boekenRoot.getChildren().add(boek);
        }

        boekenRoot.setValue("Boeken (" + lenB + " boeken)");

        if (lenB == 1) {
            boekenRoot.setValue("Boeken (" + lenB + " boek)");
        }

        treeView.setRoot(itemRoot);
        treeView.setShowRoot(false);

    }

    private void removeOld() 
    {
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
            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(itemRoot)) 
            {

                basisVisibleZetten(false);

                lblPagLen.setVisible(false);
                txtPagLen.setVisible(false);
                lblAuteur.setVisible(false);
                txtAuteur.setVisible(false);
                lblBeschrijving.setVisible(false);
                txaBeschrijving.setVisible(false);
            }

            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(boekenRoot)) 
            {
                // int hulp = treeView.getSelectionModel().getSelectedIndex() - 1;
                test = treeView.getSelectionModel().getSelectedIndex();

                Boek boek = connect.getBoek(test);

                basisVisibleZetten(true);

                lblNaam.setText(boek.getNaam());

                lblPagLen.setVisible(true);
                txtPagLen.setVisible(true);
                lblAuteur.setVisible(true);
                txtAuteur.setVisible(true);
                lblBeschrijving.setVisible(true);
                txaBeschrijving.setVisible(true);

                txtThema.setText(boek.getThema());
                txtLeeftijd.setText(boek.getLeeftijd());
                txtAantal.setText("" + boek.getAantal());
                txaBeschrijving.setText(boek.getBeschrijving());
                txtAuteur.setText(boek.getAuteur());
                txtPagLen.setText("" + boek.getPaginas());
                lblPagLen.setText("Pagina's");

            }
        } 
        catch (NullPointerException nu) 
        {
            System.out.println("Nullpointer");
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }

    private void basisVisibleZetten(boolean visible) 
    {
        lblNaam.setVisible(visible);
        lblAantal.setVisible(visible);
        txtAantal.setVisible(visible);
        lblLeeftijd.setVisible(visible);
        txtLeeftijd.setVisible(visible);
        lblThema.setVisible(visible);
        txtThema.setVisible(visible);
    }

    @FXML
    public void search() 
    {
        List<String> allBoeken = boekList;
        List<String> boekenWel = new ArrayList<>();

        for (int i = 0; i < allBoeken.size(); i++) 
        {
            if (allBoeken.get(i).toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim())) 
            {
                boekenWel.add(allBoeken.get(i));
            }
        }

        removeOld();
        vulTreeView(boekenWel);
    }
    
    @FXML
    public void btnBoekenPressed()
    {
        Stage stage = (Stage) lblNaam.getScene().getWindow();
        stage.setScene(null);
        stage.setScene(new Scene(new ZoekBoeken(ingelogd, gebruikersNaam)));
    }
    
    @FXML
    public void btnCdsPressed()
    {
        Stage stage = (Stage) lblNaam.getScene().getWindow();
        stage.setScene(null);
        stage.setScene(new Scene(new ZoekCds(ingelogd, gebruikersNaam)));
    }
    
    @FXML
    public void btnDvdsPressed()
    {
        Stage stage = (Stage) lblNaam.getScene().getWindow();
        stage.setScene(null);
        stage.setScene(new Scene(new ZoekDvds(ingelogd, gebruikersNaam)));
    }
    
    @FXML
    public void btnSpellenPressed()
    {
        Stage stage = (Stage) lblNaam.getScene().getWindow();
        stage.setScene(null);
        stage.setScene(new Scene(new ZoekSpellen(ingelogd, gebruikersNaam)));
    }
}
