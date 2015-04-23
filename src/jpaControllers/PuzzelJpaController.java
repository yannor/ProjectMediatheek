
package jpaControllers;

import items.Puzzel;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistence.exceptions.NonexistentEntityException;

public class PuzzelJpaController implements Serializable {

    public PuzzelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Puzzel puzzel) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(puzzel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Puzzel puzzel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            puzzel = em.merge(puzzel);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = puzzel.getId();
                if (findPuzzel(id) == null) {
                    throw new NonexistentEntityException("The puzzel with id " + id + " no longer exists.");
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
            Puzzel puzzel;
            try {
                puzzel = em.getReference(Puzzel.class, id);
                puzzel.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The puzzel with id " + id + " no longer exists.", enfe);
            }
            em.remove(puzzel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Puzzel> findPuzzelEntities() {
        return findPuzzelEntities(true, -1, -1);
    }

    public List<Puzzel> findPuzzelEntities(int maxResults, int firstResult) {
        return findPuzzelEntities(false, maxResults, firstResult);
    }

    private List<Puzzel> findPuzzelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Puzzel.class));
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

    public Puzzel findPuzzel(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Puzzel.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuzzelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Puzzel> rt = cq.from(Puzzel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }


}
