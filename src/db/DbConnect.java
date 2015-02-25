package db;

import item.Boek;
import item.Item;
import item.Spel;
import item.Verteltas;
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

    public Boek getBoek(int id) {

        Boek boek = null;
        try {

            String query = "SELECT * FROM boeken WHERE id=" + id;
            rs = st.executeQuery(query);

            rs.next();

            String titel = rs.getString("titel");
            int aantal = rs.getInt("aantalExemplaren");
            String bes = rs.getString("beschrijving");
            String thema = rs.getString("thema");
            int aantalBlz = rs.getInt("aantalBlz");
            String isbn = rs.getString("isbn");
            String auteur = rs.getString("auteur");
            String niveau = rs.getString("niveau");

            boek = new Boek(titel, id, aantal, bes, thema, aantalBlz, isbn, auteur, niveau);
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return boek;
    }

    public Spel getSpel(int id) {

        Spel spel = null;
        try {

            String query = "SELECT * FROM gezelschapsspellen WHERE id=" + id;
            rs = st.executeQuery(query);

            rs.next();

            String titel = rs.getString("naam");
            int aantal = rs.getInt("aantal");
            String bes = rs.getString("beschrijving");
            String thema = rs.getString("thema");

            spel = new Spel(titel, id, aantal, bes, thema);
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return spel;
    }

    public Verteltas getVerteltas(int id) {

        Verteltas tas = null;
        try {

            String query = "SELECT * FROM verteltas WHERE id=" + id;
            rs = st.executeQuery(query);

            rs.next();

            String titel = rs.getString("naam");
            int aantal = rs.getInt("aantal");
            String bes = rs.getString("beschrijving");
            String thema = rs.getString("thema");
            String leesniveau = rs.getString("leesniveau");
            Item[] items = {};

            tas = new Verteltas(titel, id, aantal, bes, thema, items, leesniveau);
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return tas;
    }

    public List<Boek> getAlleBoeken() {
        List<Boek> boeken = new ArrayList<>();

        try {

            String query = "SELECT * FROM boeken";
            rs = st.executeQuery(query);

            while (rs.next()) {

                int id = rs.getInt("id");
                String titel = rs.getString("titel");
                int aantal = rs.getInt("aantalExemplaren");
                String bes = rs.getString("beschrijving");
                String thema = rs.getString("thema");
                int aantalBlz = rs.getInt("aantalBlz");
                String isbn = rs.getString("isbn");
                String auteur = rs.getString("auteur");
                String niveau = rs.getString("niveau");

                boeken.add(new Boek(titel, id, aantal, bes, thema, aantalBlz, isbn, auteur, niveau));
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

                String titel = rs.getString("naam");
                int aantal = rs.getInt("aantal");
                String bes = rs.getString("beschrijving");
                String thema = rs.getString("thema");

                spelen.add(new Spel(titel, id, aantal, bes, thema));

            }
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return spelen;
    }

    public List<Verteltas> getAlleVertelTassen() {
        List<Verteltas> tassen = new ArrayList<>();

        try {

            String query = "SELECT * FROM verteltas";
            rs = st.executeQuery(query);

            while (rs.next()) {

                int id = rs.getInt("id");

                String titel = rs.getString("naam");
                int aantal = rs.getInt("aantal");
                String bes = rs.getString("beschrijving");
                String thema = rs.getString("thema");
                String leesniveau = rs.getString("leesniveau");
                Item[] items = {};

                tassen.add(new Verteltas(titel, id, aantal, bes, thema, items, leesniveau));

            }
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return tassen;
    }

    public void addBoek(String naam, String isbn, String thema, int aantalExemplaren, String auteur, int aantalBlz,String leesniveau, String bes) {

        try {

            //query opnieuw schrijven
            String query = "INSERT INTO boeken(isbn, titel,thema,beschrijving,auteur,aantalBlz,niveau,aantalExemplaren)"
                    + "VALUES (\"" + isbn + "\",\"" + naam +"\","+"\""+thema+"\","
                    +"\""+bes+"\","
                    +"\""+auteur+"\","
                    +""+aantalBlz+","
                    +"\""+leesniveau+"\","
                    +""+aantalExemplaren+")";
            st.executeUpdate(query);

        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

    }

    public void deleteItem(int id) {

        try {

            String query = "DELETE FROM Items WHERE ID=" + id;
            st.executeUpdate(query);

        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

    }

    public void updateBoek(int id, String naam, String isbn, String thema, int aantalExemplaren, String auteur, int aantalBlz, String leesniveau, String bes) {

        try {

            //query opnieuw schrijven
            String query = "UPDATE boeken\n"
                    + "SET titel=\"" + naam + "\",isbn=\"" + isbn + "\",thema=\"" + thema + "\""
                    + ",aantalExemplaren=" + aantalExemplaren + ",auteur=\"" + auteur + "\",aantalBlz=" + aantalBlz + "\n"
                    + ",niveau=\"" + leesniveau + "\",beschrijving=\"" + bes + "\""
                    + "WHERE id=" + id;
            st.executeUpdate(query);

        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

    }

}
