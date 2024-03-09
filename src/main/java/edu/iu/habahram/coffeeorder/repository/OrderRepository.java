package edu.iu.habahram.coffeeorder.repository;

import edu.iu.habahram.coffeeorder.model.*;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    public Receipt add(OrderData order) throws Exception {
        Beverage beverage = null;
        switch (order.beverage().toLowerCase()) {
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
        Path path = Paths.get("db.txt");
        List<String> linesOfReceipts = Files.readAllLines(path);
        ArrayList<Receipt> search = new ArrayList<>();
        for(String line : linesOfReceipts){
            String[] temp = line.split(", ");
            search.add(new Receipt(temp[2], Float.parseFloat(temp[1]), Integer.parseInt(temp[0])));
        }
        int receiptNumber = search.size() + 1;
        Receipt receipt = new Receipt(beverage.getDescription(), beverage.cost(), receiptNumber);

        String data = receiptNumber + ", " + beverage.cost() + ", " + beverage.getDescription();
        if(data.charAt(data.length() - 1) == ',') {
            Files.writeString(path,
                    data.substring(0, data.length() - 1) + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        }
        else {
            Files.writeString(path,
                    data.substring(0, data.length() - 6) + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        }
        return receipt;
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
