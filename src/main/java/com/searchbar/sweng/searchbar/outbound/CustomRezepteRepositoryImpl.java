package com.searchbar.sweng.searchbar.outbound;

import com.searchbar.sweng.searchbar.model.Rezepte;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomRezepteRepositoryImpl implements CustomRezepteRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Rezepte> findAll(String name){
        return (List<Rezepte>) em.createNativeQuery("SELECT r FROM Rezepte r WHERE r.name LIKE :rezeptName ORDER BY r.name")
               .setParameter("rezeptName",name)
              .getResultList();
   }
}
