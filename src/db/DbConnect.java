package db;

import items.Boek;
import items.Cd;
import items.Dvd;
import items.Lied;
import items.Spel;
import items.Verteltas;
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

            
            
            con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/mediatheek6", "yannor", "DeKrekel");
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
    
    public void addNieuwBoek(int boekId, String naam, String thema, String leeftijd, int aantal, 
            String beschrijving, String auteur, int paginas){
        try{
            
            String query = "INSERT INTO boeken VALUES(" + boekId + ","+ naam + ","+ thema + "," + leeftijd + "," + aantal + "," + beschrijving + "," 
                    + auteur + "," + paginas + ")";
            st.executeQuery(query);
            
        } catch(Exception ex){
            System.out.println("error " + ex);
        }
    }
    
    public void verwijderBoek(String naam){
        try{
            String query = "DELETE FROM boeken WHERE naam='" + naam + "'";
            st.executeQuery(query);
        }catch(Exception ex){
            System.out.println("error " + ex);
        }
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
                //List<Lied> l= getLiedjes(id);

                cds.add(new Cd(id, naam, "", leeftijd, aantal, null));
            }
        } catch (Exception ex) {
            System.out.println("error " + ex);
            
        }

        return cds;
    }
    
    public void addNieuweCd(int id, String naam, String thema, String leeftijd, int aantal, List<Lied> liedjes){
        try{
            
            String query = "INSERT INTO cd VALUES(" + id + ","+ naam + ","+ thema + "," + leeftijd + "," + aantal + "," + liedjes + ")";
            st.executeQuery(query);
            
        } catch(Exception ex){
            System.out.println("error " + ex);
        }
    }
    
    public void verwijderCd(String naam){
        try{
            String query = "DELETE FROM cd WHERE naam='" + naam + "'";
            st.executeQuery(query);
        }catch(Exception ex){
            System.out.println("error " + ex);
        }
    }

    public List<Lied> getLiedjes(int cdId) {
        List<Lied> liedjes = new ArrayList<>();
        // Boek boek;

        try {

            String query = "SELECT * FROM liedjes WHERE cdId="+cdId;
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
    
    public Dvd getDvd(int id) {
//        public Dvd(int id, String naam, String thema, String leeftijd, int aantal, int min) {
//        super(id, naam, thema, leeftijd, aantal);
//        this.min=min;
//    }
        Dvd dvd = null;
        try {

            String query = "SELECT * FROM dvd WHERE dvdId=" + id;
            rs = st.executeQuery(query);

            rs.next();

            String naam = rs.getString("naam");
            String thema = rs.getString("thema");
            String leeftijd = rs.getString("leeftijd");
            int aantal = rs.getInt("aantal");
            int min = rs.getInt("min");
            

            dvd = new Dvd(id, naam, thema, leeftijd, aantal, min);
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return dvd;
    }
    
    public List<Dvd> getAlleDvds() {
        List<Dvd> dvds = new ArrayList<>();
        // Boek boek;

        try {

            String query = "SELECT * FROM dvd";
            rs = st.executeQuery(query);

            while (rs.next()) {

                int id = rs.getInt("dvdId");
                String naam = rs.getString("naam");
                String thema = rs.getString("thema");
                String leeftijd = rs.getString("leeftijd");
                int aantal = rs.getInt("aantal");
                int min = rs.getInt("min");

                dvds.add(new Dvd(id, naam, thema, leeftijd, aantal, min));
            }
        } catch (Exception ex) {
            System.out.println("error " + ex);
            
        }

        return dvds;
    }
    
    public void addNieuwDvds(int id, String naam, String thema, String leeftijd, int aantal, int min){
        try{
            
            String query = "INSERT INTO dvd VALUES(" + id + ","+ naam + ","+ thema + "," + leeftijd + "," + aantal + "," + min + ")";
            st.executeQuery(query);
            
        } catch(Exception ex){
            System.out.println("error " + ex);
        }
    }
    
    public void verwijderDvd(String naam){
        try{
            String query = "DELETE FROM dvd WHERE naam='" + naam + "'";
            st.executeQuery(query);
        }catch(Exception ex){
            System.out.println("error " + ex);
        }
    }
    
    public Spel getSpel(int id) {

        Spel spel = null;
        try {

            String query = "SELECT * FROM spellen WHERE spelId=" + id;
            rs = st.executeQuery(query);

            rs.next();

            String naam = rs.getString("naam");
            String thema = rs.getString("thema");
            String leeftijd = rs.getString("leeftijd");
            int aantal = rs.getInt("aantal");
            

            spel = new Spel(id, naam, thema, leeftijd, aantal);
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return spel;
    }
    
    public List<Spel> getAlleSpelen() {
        List<Spel> spelen = new ArrayList<>();
        // Boek boek;

        try {

            String query = "SELECT * FROM spellen";
            rs = st.executeQuery(query);

            while (rs.next()) {

                int id = rs.getInt("spelId");
                String naam = rs.getString("naam");
                String thema = rs.getString("thema");
                String leeftijd = rs.getString("leeftijd");
                int aantal = rs.getInt("aantal");
                

                spelen.add(new Spel(id, naam, thema, leeftijd, aantal));
            }
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return spelen;
    }
    
    public void addNieuwSpel(int id, String naam, String thema, String leeftijd, int aantal){
        try{
            
            String query = "INSERT INTO spel VALUES(" + id + ","+ naam + ","+ thema + "," + leeftijd + "," + ")";
            st.executeQuery(query);
            
        } catch(Exception ex){
            System.out.println("error " + ex);
        }
    }
    
    public void verwijderSpel(String naam){
        try{
            String query = "DELETE FROM spel WHERE naam='" + naam + "'";
            st.executeQuery(query);
        }catch(Exception ex){
            System.out.println("error " + ex);
        }
    }
    
    public Verteltas getVerteltas(int id) {

        Verteltas verteltas = null;
        try {

            String query = "SELECT * FROM verteltas WHERE vertelId=" + id;
            rs = st.executeQuery(query);

            rs.next();
            //int id, String naam, String thema, String leeftijd, int aantal, List<Item> items
            String naam = rs.getString("naam");
            String thema = rs.getString("thema");
            String leeftijd = rs.getString("leeftijd");
            int aantal = rs.getInt("aantal");
            

            verteltas = new Verteltas(id, naam, thema, leeftijd, aantal, null);
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return verteltas;
    }
    
    public List<Verteltas> getAlleVerteltassen() {
        List<Verteltas> verteltassen = new ArrayList<>();

        try {

            String query = "SELECT * FROM verteltas";
            rs = st.executeQuery(query);

            while (rs.next()) {

                int id = rs.getInt("vertelId");
                String naam = rs.getString("naam");
                String thema = rs.getString("thema");
                String leeftijd = rs.getString("leeftijd");
                int aantal = rs.getInt("aantal");
                

                verteltassen.add(new Verteltas(id, naam, thema, leeftijd, aantal, null));
            }
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

        return verteltassen;
    }
    
//    public void addNieuwSpel(int id, String naam, String thema, String leeftijd, int aantal){
//        try{
//            
//            String query = "INSERT INTO spel VALUES(" + id + ","+ naam + ","+ thema + "," + leeftijd + "," + ")";
//            st.executeQuery(query);
//            
//        } catch(Exception ex){
//            System.out.println("error " + ex);
//        }
//    }
//    
//    public void verwijderSpel(String naam){
//        try{
//            String query = "DELETE FROM spel WHERE naam='" + naam + "'";
//            st.executeQuery(query);
//        }catch(Exception ex){
//            System.out.println("error " + ex);
//        }
//    }
}