
package util;

import items.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import persistence.exceptions.NonexistentEntityException;
/**
 *
 * @author Yannick
 */
public class ItemManagement{
    
     EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    
    private ObservableList<Item> items = FXCollections.observableArrayList();
    private ObservableList<Exemplaar> exemplaren = FXCollections.observableArrayList();
    private ObservableList<Boek> boeken = FXCollections.observableArrayList();
    
    
    public ItemManagement()
    {
        start();
    }


public void addItem(Item item)
{
    items.add(item);
    save();
    
}

    public void removeItem(Item item) {
	items.remove(item);
        save();
    }

    public void addExemplaar(Exemplaar ex) {

	exemplaren.add(ex);
        save();
    }

    public void removeExemplaar(Exemplaar ex) {
	exemplaren.remove(ex);
        save();
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

    public ObservableList<Exemplaar> getExemplaren() {
	return exemplaren;
    }

    public void save ()
    {

         
        em.getTransaction().begin();
            em.persist(exemplaren);
            em.persist(items);
        em.getTransaction().commit();
        em.close();
        
    }
    
    public void start()
    {
        items.setAll(JPAUtil.getInstance().getEntityManager().createNamedQuery("Item.findAll", Item.class).getResultList());
	exemplaren.setAll(JPAUtil.getInstance().getEntityManager().createNamedQuery("Exemplaar.findAll", Exemplaar.class).getResultList());
        
    }

    
     public void create(Item item) {
       
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
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
    

    
   
