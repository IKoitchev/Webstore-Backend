package com.store.VideoGameStore.controllers;

import com.store.VideoGameStore.Dto.OrderDto;
import com.store.VideoGameStore.models.Order;
import com.store.VideoGameStore.models.OrderItem;
import com.store.VideoGameStore.repository.OrderItemRepository;
import com.store.VideoGameStore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> getOrders(@RequestBody OrderDto order){
        //if orderFinished is false, order is the current cart
        List<Order> orders = orderRepository.findAllByUserIdAndOrderFinished(order.getUserId(), order.isOrderFinished());

        return ResponseEntity.ok().body(orders);
    }
    @PutMapping(value = ("/add"), consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addToOrder(@RequestBody OrderDto dto){

        Order currentOrder = orderRepository.findAllByUserIdAndOrderFinished(dto.getUserId(), dto.isOrderFinished()).get(0);

        if(currentOrder == null){
            currentOrder = new Order(dto.getUserId(), new Date());
            orderRepository.save(currentOrder);
        }
        OrderItem orderItem = new OrderItem(currentOrder.getId(), dto.getGameId());
        orderItemRepository.save(orderItem);


        return ResponseEntity.ok().body(currentOrder.items);
    }
    @PutMapping(value = ("/finish"), consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> finishOrder (@RequestBody OrderDto orderDTO){

        Order newOrder = orderRepository.findAllByUserIdAndOrderFinished(orderDTO.getUserId(), orderDTO.isOrderFinished()).get(0);

        if (newOrder != null){
            if (newOrder.items.isEmpty()) {
                orderRepository.delete(newOrder);
            } else {
                newOrder.setOrderFinished(true);
                orderRepository.save(newOrder);
            }

            return ResponseEntity.ok().body(newOrder);
        }
        return ResponseEntity.badRequest().body("order is not active");

    }
    @PutMapping(value = ("/remove"), consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> removeFromOrder(@RequestBody OrderDto orderDTO){

        Order currentOrder = orderRepository.findAllByUserIdAndOrderFinished(orderDTO.getUserId(), orderDTO.isOrderFinished()).get(0);

        if(currentOrder == null){
            return ResponseEntity.badRequest().body("order is not active");
        }
        OrderItem orderItem = new OrderItem(currentOrder.getId(), orderDTO.getGameId());
        orderItemRepository.delete(orderItem);


        return ResponseEntity.ok().body(currentOrder.items);

    }

}
