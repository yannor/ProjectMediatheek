package db;

import item.Boek;
import item.Spel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DbConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheek", "root", "root");
            st = con.createStatement();

        } catch (Exception ex) {
            System.out.println("error " + ex);
        }
    }

    
    
      public List<Boek> getAlleBoeken() {
        List<Boek> boeken = new ArrayList<>();
        Boek boek;
        try {

            String query = "SELECT * FROM Boeken";
            rs = st.executeQuery(query);

            while (rs.next()) {

                int id = rs.getInt("id");
                String titel= rs.getString("titel");
                int aantal= rs.getInt("aantalExemplaren");
                String bes= rs.getString("beschrijving");
                String thema= rs.getString("thema");
                int aantalBlz= rs.getInt("aantalBlz");
                int isbn= rs.getInt("isbn");
                String auteur= rs.getString("auteur");
                String niveau= rs.getString("niveau");
                
               

                boeken.add(new Boek(titel,id,aantal,bes,thema,aantalBlz,isbn,auteur,niveau));
            }
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return boeken;
    }
      
      
        public List<Spel> getAlleSpellen() {
        List<Spel> spelen = new ArrayList<>();
        Spel spel;
        try {

            String query = "SELECT * FROM gezelschapsspellen";
            rs = st.executeQuery(query);

            while (rs.next()) {

                int id = rs.getInt("id");
               
                String titel= rs.getString("naam");
                int aantal= rs.getInt("aantal");
                String bes= rs.getString("beschrijving");
                String thema= rs.getString("thema");
                

                spelen.add(new Spel(titel,id, aantal, bes, thema));
                
                
            }
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return spelen;
    }

        
        
        
    public void addItem(String tit, String bes)
    {

        try {

            //query opnieuw schrijven
            String query = "INSERT INTO items(Titel, Beschrijving)\n"
                    + "VALUES (\"" + tit + "\",\"" + bes + "\")";
            st.executeUpdate(query);

        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

    }
    
    public void deleteItem(int id)
    {

        try {

            String query = "DELETE FROM Items WHERE ID="+id;
            st.executeUpdate(query);

        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

    }
    
     public void updateItem(int id,String tit, String bes)
    {

        try {

            //query opnieuw schrijven
            String query = "UPDATE items\n" +
"SET Titel=\""+tit+"\",Beschrijving=\"" +bes+
"\"WHERE ID="+id;
            st.executeUpdate(query);

        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

    }

}
