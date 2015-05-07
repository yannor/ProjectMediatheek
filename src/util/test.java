package util;

import domein.Gebruiker;
import domein.TypeGebruiker;
import items.Boek;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

public class test{
    

public static void main(String[] args) {
    
   Gebruiker gebr= new Gebruiker( "naam","voorNaam","klas", "email", TypeGebruiker.LEERLING, "straat", "gemeente", "postCode");
   
        
    
        EntityManager entityManager 
                = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
           //entityManager.persist(gebr);
            
        entityManager.getTransaction().commit();
        entityManager.close();
        JPAUtil.getEntityManagerFactory().close();
}}