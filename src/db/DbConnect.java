package db;

import items.Boek;
import items.Cd;
import items.Dvd;
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
            String uitgeverij = rs.getString("uitgeverij");

            boek = new Boek(id, naam, thema, beschrijving, leeftijd, aantal, auteur, uitgeverij);
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
                String beschrijving = rs.getString("beschrijving");
                String leeftijd = rs.getString("leeftijd");
                int aantal = rs.getInt("aantal");
                String auteur = rs.getString("auteur");
                String uitgeverij = rs.getString("uitgeverij");

                boeken.add(new Boek(id, naam, thema, beschrijving, leeftijd, aantal, auteur, uitgeverij));
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
                String thema = rs.getString("thema");
                String leeftijd = rs.getString("leeftijd");
                int aantal = rs.getInt("aantal");
                String beschrijving = rs.getString("beschrijving");

                cd = new Cd(id, naam, thema, beschrijving, leeftijd, aantal);
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
                String thema = rs.getString("thema");
                String beschrijving = rs.getString("beschrijving");
                String leeftijd = rs.getString("leeftijd");
                int aantal = rs.getInt("aantal");
                
                cds.add(new Cd(id, naam, thema, beschrijving, leeftijd, aantal));
            }
        } catch (Exception ex) {
            System.out.println("error " + ex);
            
        }

        return cds;
    }
    
    public void addNieuweCd(int id, String naam, String thema, String beschrijving, String leeftijd, int aantal){
        try{
            
            String query = "INSERT INTO cd VALUES(" + id + ","+ naam + "," + leeftijd  + ","+ aantal + ","+ thema + ","   + beschrijving + ")";
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
            String beschrijving = rs.getString("beschrijving");
            String leeftijd = rs.getString("leeftijd");
            int aantal = rs.getInt("aantal");
            

            dvd = new Dvd(id, naam, thema, beschrijving, leeftijd, aantal);
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
                String beschrijving = rs.getString("beschrijving");
                String leeftijd = rs.getString("leeftijd");
                int aantal = rs.getInt("aantal");

                dvds.add(new Dvd(id, naam, thema, beschrijving, leeftijd, aantal));
            }
        } catch (Exception ex) {
            System.out.println("error " + ex);
            
        }

        return dvds;
    }
    
    public void addNieuwDvds(int id, String naam, String thema, String leeftijd, int aantal, String beschrijving){
        try{
            
            String query = "INSERT INTO dvd VALUES(" + id + ","+ naam + ","+ thema + "," + leeftijd + "," + aantal + "," + beschrijving + ")";
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
                String beschrijving = rs.getString("beschrijving");
                String leeftijd = rs.getString("leeftijd");
                int aantal = rs.getInt("aantal");
                String uitgeverij = rs.getString("uitgeverij");
            

            spel = new Spel(id, naam, thema, beschrijving, leeftijd, aantal, uitgeverij);
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
                String beschrijving = rs.getString("beschrijving");
                String leeftijd = rs.getString("leeftijd");
                int aantal = rs.getInt("aantal");
                String uitgeverij = rs.getString("uitgeverij");
                

                spelen.add(new Spel(id, naam, thema, beschrijving, leeftijd, aantal, uitgeverij));
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
                String naam = rs.getString("naam");
                String thema = rs.getString("thema");
                String beschrijving = rs.getString("beschrijving");
                String leeftijd = rs.getString("leeftijd");
                int aantal = rs.getInt("aantal");
                

                verteltas = new Verteltas(id, naam, thema, beschrijving, leeftijd, aantal, null);
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
                String beschrijving = rs.getString("beschrijving");
                String leeftijd = rs.getString("leeftijd");
                int aantal = rs.getInt("aantal");
                

                verteltassen.add(new Verteltas(id, naam, thema, beschrijving, leeftijd, aantal, null));
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