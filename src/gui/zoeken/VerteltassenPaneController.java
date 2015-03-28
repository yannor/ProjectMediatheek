package gui.zoeken;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class VerteltassenPaneController
{
    @FXML
    private Button btnLeen;
    @FXML
    private Label lblNaam;
    @FXML
    private Label lblThema;
    @FXML
    private TextField txtThema;
    @FXML
    private Label lblLeeftijd;
    @FXML
    private TextField txtLeeftijd;
    @FXML
    private Label lblAantal;
    @FXML
    private TextField txtAantal;
    @FXML
    private Label lblAuteur;
    @FXML
    private Label lblBeschrijving;
    @FXML
    private TextArea txaBeschrijving;
    @FXML
    private Label lblTableView;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> colNaam;
    @FXML
    private TableColumn<?, ?> colSoortZanger;
    @FXML
    private TableColumn<?, ?> colAantalLengte;
    @FXML
    private TextField txtAuteur;
    
}
