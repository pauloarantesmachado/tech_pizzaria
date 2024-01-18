package br.com.pizzaria.DAO;

import br.com.pizzaria.Model.Ingredient;
import br.com.pizzaria.Model.Pizza;

import javax.persistence.EntityManager;
import java.util.List;

public class PizzaDAO {

    private EntityManager em;


    public PizzaDAO(EntityManager em){
        this.em = em;
    }

    public void register(Pizza pizza){
        this.em.persist(pizza);
    }

    public void update(Pizza pizza){
        this.em.merge(pizza);
    }

    public void remove(Pizza pizza){
        pizza = em.merge(pizza);
        this.em.remove(pizza);
    }

    public List<Pizza> menu(){
        String jpql = "SELECT p FROM Pizza p";
        return em.createQuery(jpql, Pizza.class)
                .getResultList();
    }

    public Pizza pizzaForName(String name){
        String jpql = "SELECT i FROM Pizza i WHERE i.name = :name";
        return   em.createQuery(jpql, Pizza.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
