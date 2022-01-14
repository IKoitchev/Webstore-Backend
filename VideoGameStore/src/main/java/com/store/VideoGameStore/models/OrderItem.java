package com.store.VideoGameStore.models;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;


@Entity(name="order_items")
public class OrderItem {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="order_id")
    private long orderId;
    @Column(name="game_id")
    private long gameId;

    public OrderItem(long order_id, long game_id) {
        this.orderId = order_id;
        this.gameId = game_id;
    }

    public OrderItem() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long order_id) {
        this.orderId = order_id;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long game_id) {
        this.gameId = game_id;
    }

//    @Override
//    public Long getId() {
//        return null;
//    }
//
//    @Override
//    public boolean isNew() {
//        return true;
//    }
}
