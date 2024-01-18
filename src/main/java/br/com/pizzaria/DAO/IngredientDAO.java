package br.com.pizzaria.DAO;

import br.com.pizzaria.Model.Ingredient;
import javax.persistence.EntityManager;
import java.util.List;

public class IngredientDAO {
    private EntityManager em;


    public IngredientDAO(EntityManager em){
        this.em = em;
    }

    public void register(Ingredient ingredient){
        this.em.persist(ingredient);
    }

    public void update(Ingredient ingredient){
        this.em.merge(ingredient);
    }

    public void remove(Ingredient ingredient){
        ingredient = em.merge(ingredient);
        this.em.remove(ingredient);
    }

    public Ingredient ingredientForName(String name){
        String jpql = "SELECT i FROM Ingredient i WHERE i.name = :name";
        var response =  em.createQuery(jpql, Ingredient.class)
                .setParameter("name", name)
                .getSingleResult();
        return response;
    }

    public List<Ingredient> listIngredient(){
        String jpql = "SELECT i FROM Ingredient i";
        return em.createQuery(jpql, Ingredient.class)
                .getResultList();
    }

    public List<Ingredient> ingredientExist(String name){
        String jpql = "SELECT i FROM Ingredient i WHERE i.name = :name";
        return em.createQuery(jpql, Ingredient.class)
                .setParameter("name", name)
                .getResultList();
    }

}
