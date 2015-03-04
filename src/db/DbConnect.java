package db;

import items.Boek;
import items.Cd;
import items.Lied;
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

            String query = "SELECT * FROM boeken WHERE boekId=" + id;
            rs = st.executeQuery(query);

            rs.next();

            String naam = rs.getString("naam");
            String thema = rs.getString("thema");
            String leeftijd = rs.getString("leeftijd");
            int aantal = rs.getInt("aantal");
            String beschrijving = rs.getString("beschrijving");
            String auteur = rs.getString("auteur");
            int paginas = rs.getInt("paginas");

            boek = new Boek(id, naam, thema, leeftijd, aantal, beschrijving, auteur, paginas);
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return boek;
    }

    public Cd getCd(int id) {

        Cd cd = null;
        try {

            String query = "SELECT * FROM cd WHERE cdId=" + id;
            rs = st.executeQuery(query);

            rs.next();

            String naam = rs.getString("naam");

            String leeftijd = rs.getString("leeftijd");
            int aantal = rs.getInt("aantal");

            cd = new Cd(id, naam, "", leeftijd, aantal, getLiedjes(id));
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return cd;
    }

    public int getAantalBoeken() {

        int aantal = 0;

        try {

            String query = "SELECT COUNT(*) FROM boeken";
            rs = st.executeQuery(query);

            rs.next();

            //schrijven
            aantal = rs.getInt(1);

        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return aantal;
    }

    public List<Boek> getAlleBoeken() {
        List<Boek> boeken = new ArrayList<>();
        // Boek boek;

        try {

            String query = "SELECT * FROM boeken";
            rs = st.executeQuery(query);

            while (rs.next()) {

                int id = rs.getInt("boekId");
                String naam = rs.getString("naam");
                String thema = rs.getString("thema");
                String leeftijd = rs.getString("leeftijd");
                int aantal = rs.getInt("aantal");
                String beschrijving = rs.getString("beschrijving");
                String auteur = rs.getString("auteur");
                int paginas = rs.getInt("paginas");

                boeken.add(new Boek(id, naam, thema, leeftijd, aantal, beschrijving, auteur, paginas));
            }
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return boeken;
    }

    public List<Lied> getLiedjes(int cdId) {
        List<Lied> liedjes = new ArrayList<>();
        // Boek boek;

        try {

            String query = "SELECT * FROM liedjes WHERE cdId=" + cdId;
            rs = st.executeQuery(query);

            while (rs.next()) {

                String naam = rs.getString("naam");
                String zanger = rs.getString("zanger");

                int min = rs.getInt("min");
                int sec = rs.getInt("sec");

                liedjes.add(new Lied(naam, zanger, min, sec));
            }
        } catch (Exception ex) {
            System.out.println("error " + ex);

        }

        return liedjes;
    }

    public List<Cd> getAlleCds() {
        List<Cd> cds = new ArrayList<>();
        // Boek boek;

        try {

            String query = "SELECT * FROM cd";
            rs = st.executeQuery(query);

            while (rs.next()) {

                int id = rs.getInt("cdId");
                String naam = rs.getString("naam");

                String leeftijd = rs.getString("leeftijd");
                int aantal = rs.getInt("aantal");
                List<Lied> l = getLiedjes(id);

                cds.add(new Cd(id, naam, "", leeftijd, aantal, l));
            }
        } catch (Exception ex) {
            System.out.println("error " + ex);

        }

        return cds;
    }
}
