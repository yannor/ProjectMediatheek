package util;

import items.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

public class test{
    

public static void main(String[] args) {
    
  
    List<String> boeken1 = new ArrayList<>();
    boeken1.add("test");
    boeken1.add("tes2");
    
    
    
   Boek boek1 = new Boek("Ik zoek een paard","", boeken1, "Astrid Panis en Greet Bosschaert", "","");
   Boek boek2 = new Boek("Mama is heel ziek","", boeken1, "", "Bakermat", "" );
   Boek boek3 = new Boek("Indianen","", boeken1, "", "Bakermat", "");
   Boek boek4 = new Boek("Meer weten over het Oude Rome", "",boeken1, "Een Usborne Flap-uitboek", "", "");
   
   Dvd dvd1 = new Dvd("Plop en het kabouterfeest", "", boeken1);
   Dvd dvd2 = new Dvd("Musti, speelse verhaaltjes", "", boeken1);
   Dvd dvd3 = new Dvd("clifford", "", boeken1);
 
   
   Spel spel1 = new Spel("jenga", "6+", boeken1,"" );
   Spel spel2 = new Spel("domino", "", boeken1, "Clementoni");
   Spel spel3 = new Spel("beterweters", "8+", boeken1, "university games");
   Spel spel4 = new Spel("memory", "7-99", boeken1, "ravensburger");
   Spel spel5 = new Spel("zoom", "4+", boeken1, "plantyn");
   Spel spel6 = new Spel("kleurkoffer", "", boeken1, "");
   
   Cd cd1 = new Cd("K3", "", boeken1, "Vakantiehits");
   Cd cd2 = new Cd("Tamtam", "", boeken1, "Hits voor kids");
   
   
        
    
        EntityManager entityManager 
                = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
           entityManager.persist(boek1);
           entityManager.persist(boek2);
           entityManager.persist(boek3);
           entityManager.persist(boek4);
           
           entityManager.persist(dvd1);
           entityManager.persist(dvd2);
           entityManager.persist(dvd3);
           
           entityManager.persist(spel1);
           entityManager.persist(spel2);
           entityManager.persist(spel3);
           entityManager.persist(spel4);
           entityManager.persist(spel5);
           entityManager.persist(spel6);
           
           entityManager.persist(cd1);
           entityManager.persist(cd2);
            
        entityManager.getTransaction().commit();
        entityManager.close();
        JPAUtil.getEntityManagerFactory().close();
}}