package util;

import items.Boek;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Yannick
 */
public class test{
    

public static void main(String[] args) {
    
   
    List<String> testThemas= new ArrayList<>();
        testThemas.add("thema");
        Boek boek= new Boek(" titel", " leeftijd",testThemas, " auteur", " uitgever", " beschrijving", " aantal");
        
    
        EntityManager entityManager 
                = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
           entityManager.persist(boek);
            
        entityManager.getTransaction().commit();
        entityManager.close();
        JPAUtil.getEntityManagerFactory().close();
}}