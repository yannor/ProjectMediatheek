package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Yannick
 */
public class JPAUtil {
    private final static EntityManagerFactory entityManagerFactory = 
            Persistence.createEntityManagerFactory("EigenProjectKrekelPU");

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
   
    private JPAUtil() {
    }
    
    public static JPAUtil getInstance() {
        return JPAUtilHolder.INSTANCE;
    }
    
    private static class JPAUtilHolder {

        private static final JPAUtil INSTANCE = new JPAUtil();
    }
    
     public EntityManager getEntityManager() {
	return getEntityManagerFactory().createEntityManager();
    }
}
