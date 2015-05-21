/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Calendar;
import javax.persistence.EntityManager;

import domein.*;
import items.*;

/**
 *
 * @author Matthias
 */
public class UitleningRepository {
   
    private EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    
    //private final EntityManager em = getEntityManagerFactory().createEntityManager();
    
    public void uitleningToevoegen(Uitlening uitlening){
        em.getTransaction().begin();
            em.persist(uitlening);
        em.getTransaction().commit();
        em.close();
    }
    
    
    public void uitleningAanpassen(Gebruiker gebruiker, Item exemplaar, int id, boolean terug, Calendar uitleenDatum ){
        em.getTransaction().begin();
            Uitlening uitlening = em.find(Uitlening.class, gebruiker);
            if (uitlening != null){
                uitlening.setExemplaar(exemplaar);
                uitlening.setGebruiker(gebruiker);
                uitlening.setId(id);
                uitlening.setTerug(terug);
                uitlening.setUitleenDatum(uitleenDatum);
            }
        em.getTransaction().commit();
        em.close();
    }
    
    public void uitleningVerwijderen(int id){
        em.getTransaction().begin();
            Uitlening uitlening = em.find(Uitlening.class, id);
            if (uitlening != null){
                em.remove(uitlening);
            }
        em.getTransaction().commit();
        em.close();
    }
    
    public void datumAanpassen(int id, Calendar uitleenDatum){
        em.getTransaction().begin();
        Uitlening uitlening = em.find(Uitlening.class, id);
        if(uitlening != null){
            uitlening.setUitleenDatum(uitleenDatum);
        }
    }
}
