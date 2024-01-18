package br.com.pizzaria.Model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_table")
    private Integer numberTable;

    private LocalDateTime date;

    @ManyToMany
    private List<Pizza> pizzaList;


    public Request(Integer table, List<Pizza> pizzaList) {
        this.numberTable= table;
        this.date = LocalDateTime.now();
        this.pizzaList = pizzaList;
    }

    public Request() {

    }

    public Long getId() {
        return id;
    }

    public Integer getTable() {
        return numberTable;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }
}
