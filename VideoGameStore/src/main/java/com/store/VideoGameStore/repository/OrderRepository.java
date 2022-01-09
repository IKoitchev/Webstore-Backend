package com.store.VideoGameStore.repository;

import com.store.VideoGameStore.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllById(long userId);

    List<Order> findAllByUserIdAndOrderFinished(long userId, boolean orderFinished);



}
