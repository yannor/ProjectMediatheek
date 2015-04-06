package gui.zoeken;

import items.Verteltas;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class VerteltassenPaneController extends AnchorPane
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
    private boolean ingelogd;
    private Verteltas verteltas;

    public VerteltassenPaneController(Verteltas verteltas, boolean ingelogd)
    {
        this.ingelogd = ingelogd;
        this.verteltas = verteltas;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VerteltassenPane.fxml"));
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
        
        vulGegevensIn();
    }

    private void vulGegevensIn()
    {
        if(!ingelogd)
        {
            btnLeen.setDisable(true);
            btnLeen.setVisible(false);
        }
        
        lblNaam.setText(verteltas.getNaam());
        txtThema.setText(verteltas.getThema());
        txtLeeftijd.setText(verteltas.getLeeftijd());
        txtAantal.setText("" + verteltas.getAantal());
        txaBeschrijving.setText(verteltas.getBeschrijving());
    }
}
