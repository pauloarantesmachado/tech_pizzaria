package br.com.pizzaria;

import br.com.pizzaria.DAO.IngredientDAO;
import br.com.pizzaria.DAO.PizzaDAO;
import br.com.pizzaria.DAO.RequestDAO;
import br.com.pizzaria.Model.Ingredient;
import br.com.pizzaria.Model.Pizza;
import br.com.pizzaria.Model.Request;
import br.com.pizzaria.util.JpaUtil;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var option  = -1;
        while ( option  != 0) {
            dialogue1();
            option = sc.nextInt();
            switch (option){
                case 1:
                    registerPizza(sc);
                    break;
                case 2:
                    registerIngredient();
                    break;
                case 3:
                    registerRequest();
                    break;
                case 4:
                    viewMenu();
                    break;
                default:
                    break;
            }
        }
    }

    private static void viewMenu() {
        EntityManager em = JpaUtil.getEntityManager();
        PizzaDAO pizzaDAO = new PizzaDAO(em);
        em.getTransaction().begin();
        pizzaDAO.menu().forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }

    private static void registerRequest() {
        Scanner sc = new Scanner(System.in);
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        System.out.println("What is number of table?");
        var numberOfTable = sc.nextInt();
        var list = realizeRequest(em);
        RequestDAO rq = new RequestDAO(em);
        rq.register(new Request(numberOfTable, list));
        em.getTransaction().commit();
        em.close();
    }

    private static List<Pizza> realizeRequest(EntityManager em) {
        Scanner sc = new Scanner(System.in);
        var listPizza = new ArrayList<Pizza>();
        var option = -1;
        while (option != 0){
            viewMenu();
            System.out.println("choose a pizza of menu");
            var namePizza = sc.next();
            PizzaDAO pizzaDAO = new PizzaDAO(em);
            listPizza.add(pizzaDAO.pizzaForName(namePizza));
            System.out.println("Add more pizza [1]");
            System.out.println("Exit [0]");
            option = sc.nextInt();
        }

        return listPizza;
    }

    public static void registerIngredient(){
        EntityManager em = JpaUtil.getEntityManager();
        IngredientDAO ingredientDAO1 = new IngredientDAO(em);
        em.getTransaction().begin();
        for(Ingredient i : createIngredient(ingredientDAO1)){
            ingredientDAO1.register(i);
        }
        em.getTransaction().commit();
        em.close();
    }

    public static List<Ingredient> createIngredient( IngredientDAO ingredientDAO1){
        Scanner sn = new Scanner(System.in);
        var list = new ArrayList<Ingredient>();
        var opt = -1;
        while(opt != 0) {
            System.out.println("Choose option:");
            System.out.println("Add new Ingredient [1] ");
            System.out.println("Exit [0] ");
            opt = sn.nextInt();
            switch (opt) {
                case 1:
                    System.out.println("What's name of ingredient:");
                    String name = sn.next();
                    var Ingredient = ingredientDAO1.ingredientExist(name);
                    if( !Ingredient.isEmpty()){
                        System.out.println("ingredient already registered");
                        break;
                    }
                    list.add(new Ingredient(name));
                    break;
                case 2:
                    break;
            }

        }
        return list;
    }

    public static void registerPizza( Scanner scanner){
        EntityManager em = JpaUtil.getEntityManager();
        IngredientDAO ingredientDAO1 = new IngredientDAO(em);
        em.getTransaction().begin();
        System.out.println("Choose name to pizza:");
        var namePizza = scanner.next();
        System.out.println("Choose ingredients");
        var listOfIngredient = chooseIngredient(ingredientDAO1);
        System.out.println("How much");
        var value = scanner.nextInt();
        Pizza pizza = new Pizza(namePizza, new BigDecimal(value), listOfIngredient);
        PizzaDAO pizzaDAO = new PizzaDAO(em);
        pizzaDAO.register(pizza);
        em.getTransaction().commit();
        em.close();
    }

    private static List<Ingredient> chooseIngredient(IngredientDAO ingredientDAO1) {
        var listIng = new ArrayList<Ingredient>();
        Scanner sc = new Scanner(System.in);
        var opt = -1;
        while(opt !=0) {
            dialogue2();
            opt = sc.nextInt();
            switch (opt){
                case 1:
                    if(ingredientDAO1.listIngredient().isEmpty()){
                        System.out.println("no registered ingredients, please register ingredients");
                        break;
                    }
                    System.out.println("This is list of ingredient");
                    ingredientDAO1.listIngredient().forEach(System.out::println);
                    System.out.println("Choose a ingredient");
                    var nameIngredient = sc.next();
                    listIng.add(ingredientDAO1.ingredientForName(nameIngredient));
                    break;
                case 2:
                    break;
            }

        }
        return listIng;
    }

    public static void dialogue1(){
        System.out.println("Tech Pizza");
        System.out.println("**********************************************");
        System.out.println("Choose option:");
        System.out.println("Register Pizza [1]");
        System.out.println("Register Ingredient [2]");
        System.out.println("Register a request [3]");
        System.out.println("Menu [4]");
        System.out.println("Exit [0]");
        System.out.println("**********************************************");
    }

    public static void dialogue2(){
        System.out.println("Choose Option:");
        System.out.println("choose ingredient [1]");
        System.out.println("Exit [0]");
    }

}
