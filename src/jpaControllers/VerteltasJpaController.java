
package jpaControllers;

import items.Verteltas;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistence.exceptions.NonexistentEntityException;

public class VerteltasJpaController implements Serializable {

    public VerteltasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Verteltas verteltas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(verteltas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Verteltas verteltas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            verteltas = em.merge(verteltas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = verteltas.getId();
                if (findVerteltas(id) == null) {
                    throw new NonexistentEntityException("The verteltas with id " + id + " no longer exists.");
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
            Verteltas verteltas;
            try {
                verteltas = em.getReference(Verteltas.class, id);
                verteltas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The verteltas with id " + id + " no longer exists.", enfe);
            }
            em.remove(verteltas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Verteltas> findVerteltasEntities() {
        return findVerteltasEntities(true, -1, -1);
    }

    public List<Verteltas> findVerteltasEntities(int maxResults, int firstResult) {
        return findVerteltasEntities(false, maxResults, firstResult);
    }

    private List<Verteltas> findVerteltasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Verteltas.class));
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

    public Verteltas findVerteltas(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Verteltas.class, id);
        } finally {
            em.close();
        }
    }

    public int getVerteltasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Verteltas> rt = cq.from(Verteltas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }


}
