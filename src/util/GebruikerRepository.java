
package util;

import domein.Gebruiker;
import items.Item;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class GebruikerRepository {
    
 private EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
 private ObservableList<Gebruiker> gebruikers = FXCollections.observableArrayList();
 
  public void create(Gebruiker gebruiker) {
       
        try {
            em.getTransaction().begin();
            em.persist(gebruiker);
            em.getTransaction().commit();
        } finally {
            
               em.close();
            
        }
        
    }
  
  public ObservableList<Gebruiker> getAlleGebruikers()
  {
      gebruikers.setAll(JPAUtil.getInstance().getEntityManager().createNamedQuery("Gebruiker.findAll", Gebruiker.class).getResultList());
      return gebruikers;
  }
  
  
   public void destroy(int id)  {
       em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
           
            em.getTransaction().begin();
            Gebruiker gebr= em.getReference(Gebruiker.class, id);
            try {
                gebr = em.getReference(Gebruiker.class, id);
                gebr.getId();
            } catch (EntityNotFoundException enfe) {
                enfe.printStackTrace();
            }
             em.remove(gebr);
            em.getTransaction().commit();
           
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
   
    public void deletAll()  {
       
  
    
     try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/media", "root", "root")) {

              String deleteAll = "DELETE FROM gebruikers ";
            //System.out.println(loadQuery);
            Statement stmt = connection.createStatement();
            stmt.execute(deleteAll);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}