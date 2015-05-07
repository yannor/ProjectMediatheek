package domein;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.io.BufferedReader;
import java.sql.DriverManager;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import util.JPAUtil;

public class ImportCsv {

    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

    public static void main(String[] args) {

        readCsvUsingLoad();

    }

    private static void readCsv() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/media", "root", "root");
            BufferedReader br = new BufferedReader(new FileReader("C:/Users/gisela/Desktop/School/2015-2015 semester 2/Project/LeerlingenCsv.csv"));
            String line;
            line=br.readLine();
            while ((line = br.readLine()) != null) {
                String[] value = line.split(";");
                String sql = "INSERT into gebruikers(naam, voornaam, straat, email, klas) "
                        + "values ('" + value[0] + "','" + value[1] + "','" + value[2] + "','" + value[3] + "','" + value[4] + "')";
                System.out.println(sql);
                PreparedStatement pst = null;
                try {
                    pst = connection.prepareStatement(sql);
                    pst.executeUpdate();
                } finally {
                    if (pst != null) {
                        pst.close();
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private static void readCsvUsingLoad() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/media", "root", "root")) {

            String loadQuery = "LOAD DATA LOCAL INFILE '" + "C:/Users/gisela/Desktop/School/2015-2015 semester 2/Project/LeerlingenCsv.csv" + "' INTO TABLE gebruikers FIELDS TERMINATED BY ';'" + " LINES TERMINATED BY '\n' (NAAM,VOORNAAM, straat, email,klas) ";
            System.out.println(loadQuery);
            Statement stmt = connection.createStatement();
            stmt.execute(loadQuery);
            System.out.println("Data Successfully Uploaded");
            verwijderEersteRij();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
     private static void verwijderEersteRij() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/media", "root", "root")) {

            String loadQuery = "DELETE FROM GEBRUIKERS WHERE NAAM='NAAM' ";
            
            Statement stmt = connection.createStatement();
            stmt.execute(loadQuery);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
