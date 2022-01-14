package com.store.VideoGameStore.models;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:8080/orders")
@Entity(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="user_id")
    private long userId;

    @Column(name="order_finished")
    private boolean orderFinished = false;

    @Column(name="purchase_date")
    private Date purchaseDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    public Set<Game> items = new HashSet<>();

    public Order(long userId, Date purchaseDate) {
        this.userId = userId;
        this.purchaseDate = purchaseDate;
        orderFinished = false;
        //default state is false because order is ongoing
    }

    public Order() {

    }

    public Order(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isOrderFinished() {
        return orderFinished;
    }

    public void setOrderFinished(boolean orderFinished) {
        this.orderFinished = orderFinished;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
