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
public class Zoeken extends BorderPane {

    @FXML
    private Button btnBackHome, btnLeen;

    @FXML
    private Label lblNaam, lblThema, lblLeeftijd, lblAantal, lblBeschrijving, lblAuteur, lblTableView, lblPagLen;

    @FXML
    private TextField txtZoeken, txtThema, txtLeeftijd, txtAantal, txtAuteur, txtPagLen;

    @FXML
    private TextArea txaBeschrijving;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private TableView<String> tableView;
    private TableColumn<String, String> colNaam, colSoortZanger, colAantalLengte;

    TreeItem<String> itemRoot, boekenRoot, cdRoot, dvdRoot, spellenRoot, vertelRoot;

    String selected; //wordt later int die het id van het geselecteerd item is
    int test; //al de bovenstaand int maar enkel voor boeken

    private boolean ingelogd = false;
    private String gebruikersNaam;
    private DbConnect connect = new DbConnect();

    private List<String> cdList = new ArrayList<>();
    private List<String> testDvdList = new ArrayList<>();
    private List<String> testSpelList = new ArrayList<>();
    private List<String> testVertelList = new ArrayList<>();

    private List<String> boekList = new ArrayList<>();

    private ObservableList<Lied> liedjes;

    public Zoeken(boolean ingelogd, String gebruikersNaam) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ZoekPage.fxml"));
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
        boekenRoot = new TreeItem<>("Boeken");
        cdRoot = new TreeItem<>("Cd's");
        dvdRoot = new TreeItem<>("Dvd's");
        spellenRoot = new TreeItem<>("Gezelschapsspellen");
        vertelRoot = new TreeItem<>("Verteltassen");

        testDvdList.add("300");
        testDvdList.add("Avengers");

        testSpelList.add("Wie is het");
        testSpelList.add("Monopoly");

        testVertelList.add("Pesten");
        testVertelList.add("vertrouwen");
        

        for (int i = 0; i < connect.getAlleCds().size(); i++) {

            cdList.add(connect.getAlleCds().get(i).getNaam());
        }

        for (int i = 0; i < connect.getAlleBoeken().size(); i++) {
            boekList.add(connect.getAlleBoeken().get(i).getNaam());
        }

        
        vulTreeView(boekList, cdList, testDvdList, testSpelList, testVertelList);

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

    public void vulTreeView(List listBoek, List listCd, List listDvd, List listSpel, List listVertel) {

        basisVisibleZetten(false);
        lblPagLen.setVisible(false);
        txtPagLen.setVisible(false);
        lblAuteur.setVisible(false);
        txtAuteur.setVisible(false);
        lblBeschrijving.setVisible(false);
        txaBeschrijving.setVisible(false);
        lblTableView.setVisible(false);
        tableView.setVisible(false);

        int lenB = listBoek.size();

        for (int i = 0; i < lenB; i++) {
            TreeItem<String> boek = new TreeItem<>(listBoek.get(i).toString());
            boekenRoot.getChildren().add(boek);
        }
        int lenCd = listCd.size();

        for (int i = 0; i < lenCd; i++) {
            TreeItem<String> cd = new TreeItem<>(listCd.get(i).toString());
            cdRoot.getChildren().add(cd);
        }

        int lenDvd = listDvd.size();

        for (int i = 0; i < lenDvd; i++) {
            TreeItem<String> dvd = new TreeItem<>(listDvd.get(i).toString());
            dvdRoot.getChildren().add(dvd);
        }

        int lenSpel = listSpel.size();

        for (int i = 0; i < lenSpel; i++) {
            TreeItem<String> spel = new TreeItem<>(listSpel.get(i).toString());
            spellenRoot.getChildren().add(spel);
        }

        int lenVertel = listVertel.size();

        for (int i = 0; i < lenVertel; i++) {
            TreeItem<String> verteltas = new TreeItem<>(listVertel.get(i).toString());
            vertelRoot.getChildren().add(verteltas);
        }

        boekenRoot.setValue("Boeken (" + lenB + " boeken)");
        cdRoot.setValue("Cd's (" + lenCd + " cd's)");
        dvdRoot.setValue("Dvd's (" + lenDvd + " dvd's)");
        spellenRoot.setValue("Gezelschapsspellen (" + lenSpel + " spelen)");
        vertelRoot.setValue("Verteltassen (" + lenVertel + " tassen)");

        if (lenB == 1) {
            boekenRoot.setValue("Boeken (" + lenB + " boek)");
        }
        if (lenCd == 1) {
            cdRoot.setValue("Cd's (" + lenCd + " cd)");
        }
        if (lenDvd == 1) {
            dvdRoot.setValue("Dvd's (" + lenDvd + " dvd)");
        }
        if (lenSpel == 1) {
            spellenRoot.setValue("Gezelschapsspellen (" + lenSpel + " spel)");
        }
        if (lenVertel == 1) {
            vertelRoot.setValue("Verteltassen (" + lenVertel + " tas)");
        }

        treeView.setRoot(itemRoot);
        treeView.setShowRoot(false);

    }

    private void removeOld() {

        for (int i = 0; i < cdRoot.getChildren().size(); i++) {
            cdRoot.getChildren().clear();
        }

        for (int i = 0; i < dvdRoot.getChildren().size(); i++) {
            dvdRoot.getChildren().clear();
        }

        for (int i = 0; i < spellenRoot.getChildren().size(); i++) {
            spellenRoot.getChildren().clear();
        }

        for (int i = 0; i < vertelRoot.getChildren().size(); i++) {
            vertelRoot.getChildren().clear();
        }

        for (int i = 0; i < boekenRoot.getChildren().size(); i++) {
            boekenRoot.getChildren().clear();
        }

    }

    @FXML
    private void select() {

        try {
            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(itemRoot)) {

                basisVisibleZetten(false);

                lblPagLen.setVisible(false);
                txtPagLen.setVisible(false);
                lblAuteur.setVisible(false);
                txtAuteur.setVisible(false);
                lblBeschrijving.setVisible(false);
                txaBeschrijving.setVisible(false);
                lblTableView.setVisible(false);
                tableView.setVisible(false);
            }

            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(boekenRoot)) {

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
                lblTableView.setVisible(false);
                tableView.setVisible(false);

                txtThema.setText(boek.getThema());
                txtLeeftijd.setText(boek.getLeeftijd());
                txtAantal.setText("" + boek.getAantal());
                txaBeschrijving.setText(boek.getBeschrijving());
                txtAuteur.setText(boek.getAuteur());
                txtPagLen.setText("" + boek.getPaginas());
                lblPagLen.setText("Pagina's");
                
                
                

            }

            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(cdRoot)) {
                if (boekenRoot.isExpanded()) {
                    test = treeView.getSelectionModel().getSelectedIndex() - 1 - boekenRoot.getChildren().size();
                } else {
                    test = treeView.getSelectionModel().getSelectedIndex() - 1;
                }

                Cd cd = connect.getCd(test);

                basisVisibleZetten(true);

                lblNaam.setText(cd.getNaam());

                lblPagLen.setVisible(true);
                txtPagLen.setVisible(true);
                lblAuteur.setVisible(false);
                txtAuteur.setVisible(false);
                lblBeschrijving.setVisible(false);
                txaBeschrijving.setVisible(false);
                lblTableView.setVisible(true);
                tableView.setVisible(true);

                lblThema.setVisible(false);
                txtThema.setVisible(false);

                lblPagLen.setText("Lengte");

                txtLeeftijd.setText(cd.getLeeftijd());
                txtAantal.setText(cd.getAantal() + "");
                txaBeschrijving.setText("");
                txtAuteur.setText("");
                int min=0;
                int sec=0;
                for(int i=0;i<connect.getLiedjes(cd.getId()).size();i++)
                {
                     min=min+connect.getLiedjes(cd.getId()).get(i).getMin();
                     sec =sec +connect.getLiedjes(cd.getId()).get(i).getSec();
                }
                    
                txtPagLen.setText(min+":"+sec);

                lblTableView.setText("Liedjes");
                lblTableView.setLayoutX(25);
                lblTableView.setLayoutY(285);

                tableView.setLayoutX(25);
                tableView.setLayoutY(325);

            }

            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(dvdRoot)) {

                int hulp = treeView.getSelectionModel().getSelectedIndex() - 3;
                selected = treeView.getSelectionModel().getSelectedItem().toString();
                selected = selected.substring(18, selected.length() - 2);

                basisVisibleZetten(true);

                lblNaam.setText(selected);

                lblPagLen.setVisible(true);
                txtPagLen.setVisible(true);
                lblAuteur.setVisible(false);
                txtAuteur.setVisible(false);
                lblBeschrijving.setVisible(true);
                txaBeschrijving.setVisible(true);
                lblTableView.setVisible(false);
                tableView.setVisible(false);

                lblPagLen.setText("Lengte");
                txtThema.setText(selected);
                txtLeeftijd.setText(selected);
                txtAantal.setText(selected);
                txaBeschrijving.setText(selected);
                txtAuteur.setText(selected);
                txtPagLen.setText(selected);

            }

            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(spellenRoot)) {

                int hulp = treeView.getSelectionModel().getSelectedIndex() - 4;
                selected = treeView.getSelectionModel().getSelectedItem().toString();
                selected = selected.substring(18, selected.length() - 2);

                basisVisibleZetten(true);

                lblNaam.setText(selected);

                lblPagLen.setVisible(false);
                txtPagLen.setVisible(false);
                lblAuteur.setVisible(false);
                txtAuteur.setVisible(false);
                lblBeschrijving.setVisible(true);
                txaBeschrijving.setVisible(true);
                lblTableView.setVisible(false);
                tableView.setVisible(false);

                txtThema.setText(selected);
                txtLeeftijd.setText(selected);
                txtAantal.setText(selected);
                txaBeschrijving.setText(selected);
                txtAuteur.setText(selected);
                txtPagLen.setText(selected);

            }

            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(vertelRoot)) {

                int hulp = treeView.getSelectionModel().getSelectedIndex() - 5;
                selected = treeView.getSelectionModel().getSelectedItem().toString();
                selected = selected.substring(18, selected.length() - 2);

                basisVisibleZetten(true);

                lblNaam.setText(selected);

                lblPagLen.setVisible(false);
                txtPagLen.setVisible(false);
                lblAuteur.setVisible(false);
                txtAuteur.setVisible(false);
                lblBeschrijving.setVisible(true);
                txaBeschrijving.setVisible(true);
                lblTableView.setVisible(true);
                tableView.setVisible(true);

                txtThema.setText(selected);
                txtLeeftijd.setText(selected);
                txtAantal.setText(selected);
                txaBeschrijving.setText(selected);
                txtAuteur.setText(selected);
                txtPagLen.setText(selected);

                lblTableView.setLayoutX(400);
                lblTableView.setLayoutY(285);
                lblTableView.setText("Inhoud");
                tableView.setLayoutX(400);
                tableView.setLayoutY(325);

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

        List<String> allBoeken = boekList;
        List<String> boekenWel = new ArrayList<>();

        for (int i = 0; i < allBoeken.size(); i++) {
            if (connect.getAlleBoeken().get(i).getNaam().toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim())) {

                boekenWel.add(allBoeken.get(i));
            }
        }

        List<String> allCds = cdList;
        List<String> cdsWel = new ArrayList<>();

        for (int i = 0; i < allCds.size(); i++) {
            if (connect.getAlleCds().get(i).getNaam().toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim())) {
                cdsWel.add(allCds.get(i));
            }
        }

        List<String> allDvd = testDvdList;
        List<String> dvdWel = new ArrayList<>();

        for (int i = 0; i < allDvd.size(); i++) {
            if (allDvd.get(i).toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim())) {
                dvdWel.add(allDvd.get(i));
            }
        }

        List<String> allVertel = testVertelList;
        List<String> vertelWel = new ArrayList<>();

        for (int i = 0; i < allVertel.size(); i++) {
            if (allVertel.get(i).toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim())) {
                vertelWel.add(allVertel.get(i));
            }
        }

        List<String> allSpel = testSpelList;
        List<String> spelWel = new ArrayList<>();

        for (int i = 0; i < allSpel.size(); i++) {
            if (allSpel.get(i).toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim())) {
                spelWel.add(allSpel.get(i));
            }
        }

        removeOld();
        vulTreeView(boekenWel, cdsWel, dvdWel, spelWel, vertelWel);

    }
}
