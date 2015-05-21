
package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import persistence.exceptions.NonexistentEntityException;

import items.*;

public class ItemManagement{
    
     EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    
    private ObservableList<Item> items = FXCollections.observableArrayList();
    private ObservableList<Boek> boeken = FXCollections.observableArrayList();
    private ObservableList<Cd> cds = FXCollections.observableArrayList();
    private ObservableList<Dvd> dvds = FXCollections.observableArrayList();
    private ObservableList<Spel> spellen = FXCollections.observableArrayList();
    private ObservableList<Verteltas> tassen = FXCollections.observableArrayList();
    
    public ItemManagement()
    {
        start();
    }




   

    public ObservableList<Item> getItems() {
	return items;
    }
    
    
    
    public ObservableList<Boek> getBoeken() {
        for(int x=0;x<items.size();x++)
        {
            if(items.get(x)instanceof Boek)
            {
                boeken.add((Boek)items.get(x));
            }
        }
        
	return boeken;
    }
    
    public ObservableList<Cd> getCds() {
        for(int x=0;x<items.size();x++)
        {
            if(items.get(x)instanceof Cd)
            {
                cds.add((Cd)items.get(x));
            }
        }
        
	return cds;
    }
    
    public ObservableList<Dvd> getDvds() {
        for(int x=0;x<items.size();x++)
        {
            if(items.get(x)instanceof Dvd)
            {
                dvds.add((Dvd)items.get(x));
            }
        }
        
	return dvds;
    }
    
    public ObservableList<Spel> getSpellen() {
        for(int x=0;x<items.size();x++)
        {
            if(items.get(x)instanceof Spel)
            {
                spellen.add((Spel)items.get(x));
            }
        }
        
	return spellen;
    }
    
    public ObservableList<Verteltas> getTassen() {
        for(int x=0;x<items.size();x++)
        {
            if(items.get(x)instanceof Verteltas)
            {
                tassen.add((Verteltas)items.get(x));
            }
        }
        
	return tassen;
    }

   

    public void save ()
    {

         
        em.getTransaction().begin();
           
            em.persist(items);
        em.getTransaction().commit();
        em.close();
        
    }
    
    public void start()
    {
        items.setAll(JPAUtil.getInstance().getEntityManager().createNamedQuery("Item.findAll", Item.class).getResultList());

        
    }

    
     public void create(Item item) {
       
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } finally {
            
               em.close();
            
        }
        
    }

    public void edit(Item item) throws NonexistentEntityException, Exception {
       
        try {
            em.getTransaction().begin();
            item = em.merge(item);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = item.getId();
//                if (findItem(id) == null) {
//                    throw new NonexistentEntityException("The item with id " + id + " no longer exists.");
//                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void destroy(int id)  {
       em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
           
            em.getTransaction().begin();
            Item item= em.getReference(Item.class, id);
            try {
                item = em.getReference(Item.class, id);
                item.getId();
            } catch (EntityNotFoundException enfe) {
                enfe.printStackTrace();
            }
            em.remove(item);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
    

    
   
