package edu.iu.habahram.coffeeorder.controllers;

import edu.iu.habahram.coffeeorder.model.OrderData;
import edu.iu.habahram.coffeeorder.model.Receipt;
import edu.iu.habahram.coffeeorder.repository.OrderFileRepository;
import edu.iu.habahram.coffeeorder.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {
    private OrderFileRepository orderFileRepository;
    private OrderRepository orderRepository;

    public OrderController(OrderFileRepository orderFileRepository, OrderRepository orderRepository) {
        this.orderFileRepository = orderFileRepository;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody OrderData order) {
        try {
            Receipt receipt = orderFileRepository.add(order);
            orderRepository.save(receipt);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(receipt);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
    @GetMapping("/get")
    public Receipt getOrder() throws IOException {
        return orderFileRepository.getOrder();
    }
}
