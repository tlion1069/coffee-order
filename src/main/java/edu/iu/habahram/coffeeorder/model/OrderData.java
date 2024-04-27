package edu.iu.habahram.coffeeorder.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.aspectj.weaver.ast.Or;

import java.util.List;
@Entity
@Table(schema = "coffee")
public class OrderData {
    @Id
    private String beverage;
    private List<String> condiments;

    public String getBeverage() {
        return beverage;
    }

    public OrderData(String beverage, List<String> condiments) {
        this.beverage = beverage;
        this.condiments = condiments;
    }

    public void setBeverage(String beverage) {
        this.beverage = beverage;
    }

    public List<String> getCondiments() {
        return condiments;
    }

    public void setCondiments(List<String> condiments) {
        this.condiments = condiments;
    }
    public OrderData(){}
}
