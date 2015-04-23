
package jpaControllers;

import domein.Uitlening;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistence.exceptions.NonexistentEntityException;

public class UitleningJpaController implements Serializable {

    public UitleningJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Uitlening uitlening) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uitlening);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Uitlening uitlening) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uitlening = em.merge(uitlening);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = uitlening.getId();
                if (findUitlening(id) == null) {
                    throw new NonexistentEntityException("The uitlening with id " + id + " no longer exists.");
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
            Uitlening uitlening;
            try {
                uitlening = em.getReference(Uitlening.class, id);
                uitlening.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uitlening with id " + id + " no longer exists.", enfe);
            }
            em.remove(uitlening);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Uitlening> findUitleningEntities() {
        return findUitleningEntities(true, -1, -1);
    }

    public List<Uitlening> findUitleningEntities(int maxResults, int firstResult) {
        return findUitleningEntities(false, maxResults, firstResult);
    }

    private List<Uitlening> findUitleningEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Uitlening.class));
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

    public Uitlening findUitlening(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Uitlening.class, id);
        } finally {
            em.close();
        }
    }

    public int getUitleningCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Uitlening> rt = cq.from(Uitlening.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }


}
