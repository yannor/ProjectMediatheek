
package jpaControllers;

import items.Exemplaar;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import items.Item;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistence.exceptions.NonexistentEntityException;

public class ExemplaarJpaController implements Serializable {

    public ExemplaarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Exemplaar exemplaar) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Item item = exemplaar.getItem();
            if (item != null) {
                item = em.getReference(item.getClass(), item.getId());
                exemplaar.setItem(item);
            }
            em.persist(exemplaar);
            if (item != null) {
                item.getExemplaren().add(exemplaar);
                item = em.merge(item);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Exemplaar exemplaar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exemplaar persistentExemplaar = em.find(Exemplaar.class, exemplaar.getId());
            Item itemOld = persistentExemplaar.getItem();
            Item itemNew = exemplaar.getItem();
            if (itemNew != null) {
                itemNew = em.getReference(itemNew.getClass(), itemNew.getId());
                exemplaar.setItem(itemNew);
            }
            exemplaar = em.merge(exemplaar);
            if (itemOld != null && !itemOld.equals(itemNew)) {
                itemOld.getExemplaren().remove(exemplaar);
                itemOld = em.merge(itemOld);
            }
            if (itemNew != null && !itemNew.equals(itemOld)) {
                itemNew.getExemplaren().add(exemplaar);
                itemNew = em.merge(itemNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = exemplaar.getId();
                if (findExemplaar(id) == null) {
                    throw new NonexistentEntityException("The exemplaar with id " + id + " no longer exists.");
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
            Exemplaar exemplaar;
            try {
                exemplaar = em.getReference(Exemplaar.class, id);
                exemplaar.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The exemplaar with id " + id + " no longer exists.", enfe);
            }
            Item item = exemplaar.getItem();
            if (item != null) {
                item.getExemplaren().remove(exemplaar);
                item = em.merge(item);
            }
            em.remove(exemplaar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Exemplaar> findExemplaarEntities() {
        return findExemplaarEntities(true, -1, -1);
    }

    public List<Exemplaar> findExemplaarEntities(int maxResults, int firstResult) {
        return findExemplaarEntities(false, maxResults, firstResult);
    }

    private List<Exemplaar> findExemplaarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Exemplaar.class));
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

    public Exemplaar findExemplaar(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Exemplaar.class, id);
        } finally {
            em.close();
        }
    }

    public int getExemplaarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Exemplaar> rt = cq.from(Exemplaar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }


}
