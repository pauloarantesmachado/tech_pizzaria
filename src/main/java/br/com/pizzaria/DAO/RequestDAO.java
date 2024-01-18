package br.com.pizzaria.DAO;

import br.com.pizzaria.Model.Request;

import javax.persistence.EntityManager;

public class RequestDAO {

    private EntityManager em;


    public RequestDAO(EntityManager em){
        this.em = em;
    }

    public void register(Request request){
        this.em.persist(request);
    }

    public void update(Request request){
        this.em.merge(request);
    }

    public void remove(Request request){
        request = em.merge(request);
        this.em.remove(request);
    }

}
