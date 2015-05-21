package gui;


import java.io.IOException;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;

import domein.ImportCsv;
import java.io.File;
import java.util.Optional;
import javafx.stage.FileChooser.ExtensionFilter;
import util.GebruikerRepository;

public class BeheerLeerlingen extends BorderPane
{
    @FXML
    private Button btnHome, btnAdd, btnDelete, btnEdit, btnExcel, btnDeleteAll, btnHomeBeheer;
    
    @FXML
    private Label lblBeheerLeerlingen;
    
    ScreenSwitcher switcher;
    
    ImportCsv imp = new ImportCsv();
    GebruikerRepository gebr = new GebruikerRepository();
    //constructor switcher maken..ik krijg altijd een wit scherm ipv mijne gui als ik het doe zoals bij andere pagina's
    //indien nodig ook klasse switcher zelf aanpassen
    
    public BeheerLeerlingen(ScreenSwitcher switcher) {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeheerLeerlingen.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       

    }
    @FXML
    private void home()
    {
      switcher.homePageIn();
    }
    @FXML
    private void add()
    {
         Stage stage = new Stage();
        stage.setTitle("Leerling toevoegen");
	//primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/resources/gui/img/logo_krekel.png")));
        ScreenSwitcher screen = new ScreenSwitcher();
	NewGebruiker leerlingToevoegen= new NewGebruiker(screen);
	//Scene scene = new Scene(switcher);
        
        Scene scene = new Scene(leerlingToevoegen);
	stage.setScene(scene);
	stage.show();
    }
    @FXML
    private void delete()
    {
        
       Stage stage = new Stage();
        stage.setTitle("Alle Leerlingen");
	//primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/resources/gui/img/logo_krekel.png")));
        ScreenSwitcher screen = new ScreenSwitcher();
	DeleteLeerling alleGebruikers= new DeleteLeerling();
	//Scene scene = new Scene(switcher);
        
        Scene scene = new Scene(alleGebruikers);
	stage.setScene(scene);
	stage.show();
       
    }
    @FXML
    private void edit()
    {
        switcher.editLeerling();
        //naar editLeerling(nog te maken)(zelfe als LeerlingToevoegen maar dan met ingevulde gegevens voor geselecteerde lln 
        //uit db en velden editeerbaar en save knop ipv toevoegen)
        //voorstel:opent een listview van alle leerlingen met bovenaan zoekfunctie
    }
    @FXML
    private void importExcel()
    {
        
        Stage stage= new Stage();
        FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Kies een lijst van leerlingen");

fileChooser.getExtensionFilters().addAll(
         new ExtensionFilter("Csv Files", "*.csv"));
    
 File selectedFile = fileChooser.showOpenDialog(stage);
 if (selectedFile != null) {
     System.out.println(selectedFile.getAbsolutePath());
    imp.readCsvUsingLoad(selectedFile);
 }
    }
    
    
    
    
    @FXML
    private void deleteAll()
    {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Leerlingen verwijderen");

alert.setContentText("Bent u zeker dat u alle leerlingen wilt verwijderen?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
     
     gebr.deletAll();
     
     Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
alert2.setTitle("Verwijderd");
alert2.setHeaderText(null);
alert2.setContentText("Alle leerlingen zijn verwijderd");

alert2.showAndWait();

} else {
    alert.close();
     
}
      
    }
    
    @FXML
    private void homeBeheer()
    {
        switcher.beheer();
    }
    
    
  
    
    
    
    
       
}
    
