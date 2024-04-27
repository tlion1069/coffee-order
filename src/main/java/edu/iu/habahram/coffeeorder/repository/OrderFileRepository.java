package edu.iu.habahram.coffeeorder.repository;

import edu.iu.habahram.coffeeorder.model.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;
import static java.lang.Math.random;

@Component
public class OrderFileRepository {
    public Receipt add(OrderData order) throws Exception {
        Beverage beverage = null;
        switch (order.getBeverage().toLowerCase()) {
            case "dark roast":
                beverage = new DarkRoast();
                break;
            case "espresso":
                beverage = new Espresso();
                break;
            case "house blend":
                beverage = new HouseBlend();
                break;
            case "decaf":
                beverage = new Decaf();
                break;
        }
        if (beverage == null) {
            throw new Exception("Beverage type '%s' is not valid!".formatted(order.getBeverage()));
        }
        for(String condiment : order.getCondiments()) {
            switch (condiment.toLowerCase()) {
                case "milk":
                   beverage = new Milk(beverage);
                   break;
                case "mocha":
                    beverage = new Mocha(beverage);
                    break;
                case "whip":
                    beverage = new Whip(beverage);
                    break;
                case "soy":
                    beverage = new Soy(beverage);
                    break;
                default:
                    throw new Exception("Condiment type '%s' is not valid".formatted(condiment));
            }
        }
        int receiptNumber = (int)(random() * 100);
        return new Receipt(beverage.getDescription(), beverage.cost(), receiptNumber);
    }
    public Receipt getOrder() throws IOException {
        List<String> linesOfReceipts = Files.readAllLines(Paths.get("db.txt"));
        ArrayList<Receipt> search = new ArrayList<>();
        for(String line : linesOfReceipts){
            String[] temp = line.split(", ");
            search.add(new Receipt(temp[2], Float.parseFloat(temp[1]), Integer.parseInt(temp[0])));
        }
        return search.get(search.size() - 1);
    }
}
