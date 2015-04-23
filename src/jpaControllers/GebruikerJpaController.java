
package jpaControllers;

import domein.Gebruiker;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistence.exceptions.NonexistentEntityException;

public class GebruikerJpaController implements Serializable {

    public GebruikerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Gebruiker gebruiker) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(gebruiker);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Gebruiker gebruiker) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            gebruiker = em.merge(gebruiker);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = gebruiker.getId();
                if (findGebruiker(id) == null) {
                    throw new NonexistentEntityException("The gebruiker with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Gebruiker gebruiker;
            try {
                gebruiker = em.getReference(Gebruiker.class, id);
                gebruiker.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gebruiker with id " + id + " no longer exists.", enfe);
            }
            em.remove(gebruiker);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Gebruiker> findGebruikerEntities() {
        return findGebruikerEntities(true, -1, -1);
    }

    public List<Gebruiker> findGebruikerEntities(int maxResults, int firstResult) {
        return findGebruikerEntities(false, maxResults, firstResult);
    }

    private List<Gebruiker> findGebruikerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Gebruiker.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Gebruiker findGebruiker(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Gebruiker.class, id);
        } finally {
            em.close();
        }
    }

    public int getGebruikerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Gebruiker> rt = cq.from(Gebruiker.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }


}
