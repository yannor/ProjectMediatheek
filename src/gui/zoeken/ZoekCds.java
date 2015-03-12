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

public class ZoekCds extends BorderPane 
{
    @FXML
    private Button btnBackHome, btnLeen;

    @FXML
    private Label lblNaam, lblThema, lblLeeftijd, lblAantal, lblTableView, lblPagLen;

    @FXML
    private TextField txtZoeken, txtThema, txtLeeftijd, txtAantal, txtPagLen;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private TableView<String> tableView;

    TreeItem<String> itemRoot, cdRoot;

    String selected; //wordt later int die het id van het geselecteerd item is
    int test; //al de bovenstaand int maar enkel voor boeken

    private boolean ingelogd = false;
    private String gebruikersNaam;
    private DbConnect connect = new DbConnect();

    private List<String> cdList = new ArrayList<>();

    public ZoekCds(boolean ingelogd, String gebruikersNaam) 
    {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ZoekCds.fxml"));
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
        cdRoot = new TreeItem<>("Cd's");

        for (int i = 0; i < connect.getAlleCds().size(); i++) {

            cdList.add(connect.getAlleCds().get(i).getNaam());
        }

        vulTreeView(cdList);

        itemRoot.getChildren().addAll(cdRoot);
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

    public void vulTreeView(List listCd) {

        basisVisibleZetten(false);
        lblPagLen.setVisible(false);
        txtPagLen.setVisible(false);
        lblTableView.setVisible(false);
        tableView.setVisible(false);
        int lenCd = listCd.size();

        for (int i = 0; i < lenCd; i++) {
            TreeItem<String> cd = new TreeItem<>(listCd.get(i).toString());
            cdRoot.getChildren().add(cd);
        }
        cdRoot.setValue("Cd's (" + lenCd + " cd's)");
        if (lenCd == 1) {
            cdRoot.setValue("Cd's (" + lenCd + " cd)");
        }

        treeView.setRoot(itemRoot);
        treeView.setShowRoot(false);

    }

    private void removeOld() {

        for (int i = 0; i < cdRoot.getChildren().size(); i++) {
            cdRoot.getChildren().clear();
        }
    }

    @FXML
    private void select() {

        try {
            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(itemRoot)) {

                basisVisibleZetten(false);

                lblPagLen.setVisible(false);
                txtPagLen.setVisible(false);
                lblTableView.setVisible(false);
                tableView.setVisible(false);
            }

            if (treeView.getSelectionModel().getSelectedItem().getParent().equals(cdRoot)) {

                Cd cd = connect.getCd(test);

                basisVisibleZetten(true);

                lblNaam.setText(cd.getNaam());

                lblPagLen.setVisible(true);
                txtPagLen.setVisible(true);
                lblTableView.setVisible(true);
                tableView.setVisible(true);

                lblThema.setVisible(false);
                txtThema.setVisible(false);

                lblPagLen.setText("Lengte");

                txtLeeftijd.setText(cd.getLeeftijd());
                txtAantal.setText(cd.getAantal() + "");
                txtPagLen.setText(cd.getLiedjes().get(test).getMin() + ":" + cd.getLiedjes().get(test).getSec());

                lblTableView.setText("Liedjes");
                lblTableView.setLayoutX(25);
                lblTableView.setLayoutY(285);

                tableView.setLayoutX(25);
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

        List<String> allCds = cdList;
        List<String> cdsWel = new ArrayList<>();

        for (int i = 0; i < allCds.size(); i++) {
            if (allCds.get(i).toLowerCase().trim().contains(txtZoeken.getText().toLowerCase().trim())) {
                cdsWel.add(allCds.get(i));
            }
        }

        removeOld();
        vulTreeView(cdsWel);

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