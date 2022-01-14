package com.store.VideoGameStore.repository;

import com.store.VideoGameStore.models.Order;
import com.store.VideoGameStore.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    boolean existsByOrderIdAndGameId(long orderId, long gameId);

    Optional<OrderItem> findOrderItemByOrderIdAndGameId(long orderId, long gameId);

    void deleteAllByGameId(long gameId);


}
