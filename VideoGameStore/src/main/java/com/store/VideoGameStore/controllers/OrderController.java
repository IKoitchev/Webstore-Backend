package com.store.VideoGameStore.controllers;

import com.store.VideoGameStore.Dto.OrderDto;
import com.store.VideoGameStore.models.Order;
import com.store.VideoGameStore.models.OrderItem;
import com.store.VideoGameStore.models.Review;
import com.store.VideoGameStore.models.User;
import com.store.VideoGameStore.repository.OrderItemRepository;
import com.store.VideoGameStore.repository.OrderRepository;
import com.store.VideoGameStore.repository.ReviewRepository;
import com.store.VideoGameStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping(value = ("/{userId}/{orderFinished}")/*, consumes = "application/json", produces = "application/json"*/)
    public ResponseEntity<?> getOrders(@PathVariable(value = "userId") long userId, @PathVariable(value = "orderFinished") boolean orderFinished ){
        //if orderFinished is false, order is the current cart
        List<Order> orders = orderRepository.findAllByUserIdAndOrderFinished(userId, orderFinished);

        return ResponseEntity.ok().body(orders);
    }

    @PutMapping(value = ("/add"), consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addToOrder(@RequestBody OrderDto dto){

        //check if user owns the product before adding to cart

        Optional<User> user = userRepository.findById(dto.getUserId());

        if(!user.isPresent()){
           return ResponseEntity.badRequest().body("error finding user with this id");
        }
        Optional<Review> review = reviewRepository.findByAuthorAndGameId(user.get().getUsername(), dto.getGameId());

        if(review.isPresent()) {
            return ResponseEntity.badRequest().body("Cannot add game because it is already owned by this user");
        }

        //shouldnt be more than 1 "current" order at a time per user, this is why we get the first one if any returned
        List<Order> currentOrder = orderRepository.findAllByUserIdAndOrderFinished(dto.getUserId(), dto.isOrderFinished());

        if(currentOrder.isEmpty()){

            currentOrder.add(new Order(dto.getUserId()));
            orderRepository.save(currentOrder.get(0));
        }

        Optional<OrderItem> orderItem = orderItemRepository.findOrderItemByOrderIdAndGameId(currentOrder.get(0).getId(), dto.getGameId());

        if(orderItem.isPresent()){
            return ResponseEntity.ok().body("Item already in cart");
        }
        orderItemRepository.save(new OrderItem(currentOrder.get(0).getId(), dto.getGameId()));

        return ResponseEntity.ok().body(currentOrder.get(0));
    }
    @PutMapping(value = ("/finish"), consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> finishOrder (@RequestBody OrderDto dto){

        List<Order> currentOrder = orderRepository.findAllByUserIdAndOrderFinished(dto.getUserId(), dto.isOrderFinished());

        if(currentOrder.isEmpty()){

           return ResponseEntity.badRequest().body("Order has expired!");
        }

            if (currentOrder.get(0).items.isEmpty()) {
                orderRepository.delete(currentOrder.get(0));
            } else {
                currentOrder.get(0).setOrderFinished(true);
                currentOrder.get(0).setPurchaseDate(new Date());
                orderRepository.save(currentOrder.get(0));

            return ResponseEntity.ok().body(currentOrder.get(0).items);
        }

        return ResponseEntity.ok().body("");

    }

    @PutMapping(value = ("/remove"), consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> removeFromOrder(@RequestBody OrderDto orderDTO){

        //to get ihe order id
        Order currentOrder = orderRepository.findAllByUserIdAndOrderFinished(orderDTO.getUserId(), orderDTO.isOrderFinished()).get(0);

        if(currentOrder == null){
            return ResponseEntity.badRequest().body("order is not active");
        }
        Optional<OrderItem> orderItem = orderItemRepository.findOrderItemByOrderIdAndGameId(currentOrder.getId(), orderDTO.getGameId());

        if(orderItem.isPresent()){
            orderItemRepository.deleteById(orderItem.get().getId());
        }
        currentOrder.items.remove(orderItem);

        return ResponseEntity.ok().body(currentOrder.items);

    }

}
