package edu.iu.habahram.coffeeorder.repository;

import edu.iu.habahram.coffeeorder.model.*;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private List<Integer> number = new ArrayList<>();
    public Receipt add(OrderData order) throws Exception {
        Beverage beverage = null;
        switch (order.beverage().toLowerCase()) {
            case "dark roast":
                beverage = new DarkRoast();
                break;
        }
        if (beverage == null) {
            throw new Exception("Beverage type '%s' is not valid!".formatted(order.beverage()));
        }
        for(String condiment : order.condiments()) {
            switch (condiment.toLowerCase()) {
                case "milk":
                   beverage = new Milk(beverage);
                   break;
                case "mocha":
                    beverage = new Mocha(beverage);
                    break;
                default:
                    throw new Exception("Condiment type '%s' is not valid".formatted(condiment));
            }
        }
        int receiptNumber = number.size() + 1;
        Receipt receipt = new Receipt(beverage.getDescription(), beverage.cost(), receiptNumber);
        number.add(receiptNumber);
        Path path = Paths.get("coffee-order/db.txt");
        String data = receiptNumber + ", " + beverage.cost() + ", " + beverage.getDescription() + System.lineSeparator();
        Files.writeString(path,
                data,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
        return receipt;
    }
}
