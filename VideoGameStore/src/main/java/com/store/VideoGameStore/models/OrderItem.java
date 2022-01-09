package com.store.VideoGameStore.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name="order_items")
public class OrderItem {

    @Id
    @Column(name="order_id")
    private long order_id;
    @Column(name="game_id")
    private long game_id;

    public OrderItem(long order_id, long game_id) {
        this.order_id = order_id;
        this.game_id = game_id;
    }

    public OrderItem() {

    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public long getGame_id() {
        return game_id;
    }

    public void setGame_id(long game_id) {
        this.game_id = game_id;
    }
}
