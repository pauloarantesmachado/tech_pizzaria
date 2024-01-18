package br.com.pizzaria.Model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pizza")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    @ManyToMany
    private List<Ingredient> ingredientList;

    public Pizza(String name, BigDecimal price, List<Ingredient> ingredientList) {
        this.name = name;
        this.price = price;
        this.ingredientList =ingredientList;
    }

    public Pizza() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                " price= " +"R$"+ price +
                " ingredientList=" + ingredientList;
    }
}
