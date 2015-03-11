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
import javafx.collections.ObservableList;

import gui.HomePage;
import gui.HomePage_uit;
import items.*;

// cd en boeken werken via db, de rest nog niet.
//bij cd wordt de tableview nog niet gevuld met de liedjes
public class ZoekSpellen extends BorderPane {

    @FXML
    private Button btnBackHome, btnLeen;

    @FXML
    private Label lblNaam, lblThema, lblLeeftijd, lblAantal, lblBeschrijving;

    @FXML
    private TextField txtZoeken, txtThema, txtLeeftijd, txtAantal;

    @FXML
    private TextArea txaBeschrijving;

    @FXML
    private TreeView<String> treeView;

    TreeItem<String> itemRoot,spellenRoot;

    String selected; //wordt later int die het id van het geselecteerd item is
    int test; //al de bovenstaand int maar enkel voor boeken

    private boolean ingelogd = false;
    private String gebruikersNaam;
    private DbConnect connect = new DbConnect();

    private List<String> testSpelList = new ArrayList<>();

    public ZoekSpellen(boolean ingelogd, String gebruikersNaam) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ZoekSpellen.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.ingelogd = ingelogd;
        this.gebruikersNaam = gebruikersNaam;

        itemRoot = new TreeItem<>();
        
        spellenRoot = new TreeItem<>("Gezelschapsspellen");
        
        testSpelList.add("Wie is het");
        testSpelList.add("Monopoly");

        vulTreeView(testSpelList);

        itemRoot.getChildren().addAll(spellenRoot);
        btnLeen.setVisible(ingelogd);

    }

    @FXML
    public void backHome() {

        if (ingelogd) {
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

        } else {
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

    public void vulTreeView(List listSpel) {

        basisVisibleZetten(false);
        lblBeschrijving.setVisible(false);
        txaBeschrijving.setVisible(false);

        int lenSpel = listSpel.size();

        for (int i = 0; i < lenSpel; i++) {
            TreeItem<String> spel = new TreeItem<>(listSpel.get(i).toString());
            spellenRoot.getChildren().add(spel);
        }

        spellenRoot.setValue("Gezelschapsspellen (" + lenSpel + " spelen)");

        if (lenSpel == 1) {
            spellenRoot.setValue("Gezelschapsspellen (" + lenSpel + " spel)");
        }

        treeView.setRoot(itemRoot);
        treeView.setShowRoot(false);

    }

    private void removeOld() {

        for (int i = 0; i < spellenRoot.getChildren().size(); i++) {
            spellenRoot.getChildren().clear();
        }

    }

    @FXML
    private void select() {

        try {
            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(itemRoot)) {

                basisVisibleZetten(false);

                lblBeschrijving.setVisible(false);
                txaBeschrijving.setVisible(false);
            }

            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(spellenRoot)) {

                int hulp = treeView.getSelectionModel().getSelectedIndex() - 4;
                selected = treeView.getSelectionModel().getSelectedItem().toString();
                selected = selected.substring(18, selected.length() - 2);

                basisVisibleZetten(true);

                lblNaam.setText(selected);

                lblBeschrijving.setVisible(true);
                txaBeschrijving.setVisible(true);

                txtThema.setText(selected);
                txtLeeftijd.setText(selected);
                txtAantal.setText(selected);
                txaBeschrijving.setText(selected);

            }
        } catch (NullPointerException nu) {
            System.out.println("Nullpointer");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void basisVisibleZetten(boolean visible) {

        lblNaam.setVisible(visible);
        lblAantal.setVisible(visible);
        txtAantal.setVisible(visible);
        lblLeeftijd.setVisible(visible);
        txtLeeftijd.setVisible(visible);
        lblThema.setVisible(visible);
        txtThema.setVisible(visible);
    }

    @FXML
    public void search() {

        List<String> allSpel = testSpelList;
        List<String> spelWel = new ArrayList<>();

        for (int i = 0; i < allSpel.size(); i++) {
            if (allSpel.get(i).toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim())) {
                spelWel.add(allSpel.get(i));
            }
        }

        removeOld();
        vulTreeView(spelWel);

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
