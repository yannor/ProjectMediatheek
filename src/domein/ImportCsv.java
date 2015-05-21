package domein;

import java.io.*;
import java.sql.*;
import javafx.stage.FileChooser;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import util.*;

public class ImportCsv {

    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

 


   

    public void readCsvUsingLoad(File file) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/media", "root", "root")) {

            String loadQuery = "LOAD DATA LOCAL INFILE \'" + file.getAbsoluteFile()+ "\' INTO TABLE gebruikers FIELDS TERMINATED BY ';'" + " LINES TERMINATED BY '\n' (NAAM,VOORNAAM, straat, email,klas) ";
            //System.out.println(loadQuery);
            Statement stmt = connection.createStatement();
            stmt.execute(loadQuery);
            System.out.println("Data Successfully Uploaded");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
verwijderEersteRij();




        
    }
    
    
     private static void verwijderEersteRij() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/media", "root", "root")) {

            String loadQuery = "DELETE FROM GEBRUIKERS WHERE NAAM='Naam' ";
            
            Statement stmt = connection.createStatement();
            stmt.execute(loadQuery);
            System.out.println("Eerste rij weg");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
