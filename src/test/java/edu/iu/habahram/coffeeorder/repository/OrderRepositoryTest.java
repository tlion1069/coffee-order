package edu.iu.habahram.coffeeorder.repository;

import edu.iu.habahram.coffeeorder.model.OrderData;
import edu.iu.habahram.coffeeorder.model.Receipt;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    @Test
    void add() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        ArrayList<String> cond = new ArrayList<>();
        cond.add("milk");
        cond.add("mocha");
        OrderData a = new OrderData("decaf",cond);
        assertEquals(orderRepository.add(a).cost(), 1.98F);
        cond.add("whip");
        a = new OrderData("espresso", cond);
        assertEquals(orderRepository.add(a).description(), "A shot of Espresso with Milk,Mocha,Whipped Cream,");
        cond.add("whip");
        a = new OrderData("espresso", cond);
        assertEquals(orderRepository.add(a).description(), "A shot of Espresso with Milk,Mocha,Whipped Cream,Whipped Cream,");
        a = new OrderData("house blend", cond);
        assertEquals(orderRepository.add(a).description(), "House Blend Drip Coffee with Milk,Mocha,Whipped Cream,Whipped Cream,");
        a = new OrderData("dark roast", cond);
        assertEquals(orderRepository.add(a).description(), "Dark Roast with Milk,Mocha,Whipped Cream,Whipped Cream,");
        a = new OrderData("decaf", cond);
        assertEquals(orderRepository.add(a).description(), "Decaffeinated Coffee with Milk,Mocha,Whipped Cream,Whipped Cream,");
        cond = new ArrayList<>();
        cond.add("soy");
        a = new OrderData("espresso", cond);
        assertEquals(orderRepository.add(a).description(), "A shot of Espresso with Soy Milk,");
        a = new OrderData("house blend", cond);
        assertEquals(orderRepository.add(a).description(), "House Blend Drip Coffee with Soy Milk,");
        a = new OrderData("dark roast", cond);
        assertEquals(orderRepository.add(a).description(), "Dark Roast with Soy Milk,");
        a = new OrderData("decaf", cond);
        assertEquals(orderRepository.add(a).description(), "Decaffeinated Coffee with Soy Milk,");
        cond = new ArrayList<>();
        cond.add("whip");
        cond.add("milk");
        cond.add("soy");
        a = new OrderData("espresso", cond);
        assertEquals(orderRepository.add(a).description(), "A shot of Espresso with Whipped Cream,Milk,Soy Milk,");
        a = new OrderData("house blend", cond);
        assertEquals(orderRepository.add(a).description(), "House Blend Drip Coffee with Whipped Cream,Milk,Soy Milk,");
        a = new OrderData("dark roast", cond);
        assertEquals(orderRepository.add(a).description(), "Dark Roast with Whipped Cream,Milk,Soy Milk,");
        a = new OrderData("decaf", cond);
        assertEquals(orderRepository.add(a).description(), "Decaffeinated Coffee with Whipped Cream,Milk,Soy Milk,");
        cond = new ArrayList<>();
        cond.add("soy");
        cond.add("mocha");
        a = new OrderData("espresso", cond);
        assertEquals(orderRepository.add(a).description(), "A shot of Espresso with Soy Milk,Mocha,");
        a = new OrderData("house blend", cond);
        assertEquals(orderRepository.add(a).description(), "House Blend Drip Coffee with Soy Milk,Mocha,");
        a = new OrderData("dark roast", cond);
        assertEquals(orderRepository.add(a).description(), "Dark Roast with Soy Milk,Mocha,");
        a = new OrderData("decaf", cond);
        assertEquals(orderRepository.add(a).description(), "Decaffeinated Coffee with Soy Milk,Mocha,");
    }
}