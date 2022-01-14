package com.store.VideoGameStore.Dto;

public class OrderDto {

    private long userId;
    private boolean orderFinished=false;
    private long gameId;
    //private long orderId;

    public OrderDto(long userId, boolean orderFinished) {
        this.userId = userId;
        this.orderFinished = orderFinished;
    }

    public OrderDto() {
    }

    public OrderDto(long userId, boolean orderFinished, long gameId) {
        this.userId = userId;
        this.orderFinished = orderFinished;
        this.gameId = gameId;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }
//    public long getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(long orderId) {
//        this.orderId = orderId;
//    }

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
}
