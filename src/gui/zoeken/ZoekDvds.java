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

public class ZoekDvds extends BorderPane {

    @FXML
    private Button btnBackHome, btnLeen;

    @FXML
    private Label lblNaam, lblThema, lblLeeftijd, lblAantal, lblBeschrijving, lblPagLen;

    @FXML
    private TextField txtZoeken, txtThema, txtLeeftijd, txtAantal, txtPagLen;

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

    private List<String> testDvdList = new ArrayList<>();

    public ZoekDvds(boolean ingelogd, String gebruikersNaam) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ZoekDvds.fxml"));
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
        dvdRoot = new TreeItem<>("Dvd's");

        testDvdList.add("300");
        testDvdList.add("Avengers");

        vulTreeView(testDvdList);

        itemRoot.getChildren().addAll(boekenRoot, cdRoot, dvdRoot, spellenRoot, vertelRoot);
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

    public void vulTreeView(List listDvd) {

        basisVisibleZetten(false);
        lblPagLen.setVisible(false);
        txtPagLen.setVisible(false);
        lblBeschrijving.setVisible(false);
        txaBeschrijving.setVisible(false);
        int lenDvd = listDvd.size();

        for (int i = 0; i < lenDvd; i++) {
            TreeItem<String> dvd = new TreeItem<>(listDvd.get(i).toString());
            dvdRoot.getChildren().add(dvd);
        }
        dvdRoot.setValue("Dvd's (" + lenDvd + " dvd's)");

        if (lenDvd == 1) {
            dvdRoot.setValue("Dvd's (" + lenDvd + " dvd)");
        }

        treeView.setRoot(itemRoot);
        treeView.setShowRoot(false);

    }

    private void removeOld() {
        for (int i = 0; i < dvdRoot.getChildren().size(); i++) {
            dvdRoot.getChildren().clear();
        }
    }

    @FXML
    private void select() {

        try {
            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(itemRoot)) {

                basisVisibleZetten(false);

                lblPagLen.setVisible(false);
                txtPagLen.setVisible(false);
                lblBeschrijving.setVisible(false);
                txaBeschrijving.setVisible(false);
            }

            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(dvdRoot)) {

                int hulp = treeView.getSelectionModel().getSelectedIndex() - 3;
                selected = treeView.getSelectionModel().getSelectedItem().toString();
                selected = selected.substring(18, selected.length() - 2);

                basisVisibleZetten(true);

                lblNaam.setText(selected);

                lblPagLen.setVisible(true);
                txtPagLen.setVisible(true);
                lblBeschrijving.setVisible(true);
                txaBeschrijving.setVisible(true);

                lblPagLen.setText("Lengte");
                txtThema.setText(selected);
                txtLeeftijd.setText(selected);
                txtAantal.setText(selected);
                txaBeschrijving.setText(selected);
                txtPagLen.setText(selected);

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
        List<String> allDvd = testDvdList;
        List<String> dvdWel = new ArrayList<>();

        for (int i = 0; i < allDvd.size(); i++) {
            if (allDvd.get(i).toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim())) {
                dvdWel.add(allDvd.get(i));
            }
        }

        removeOld();
        vulTreeView(dvdWel);
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
