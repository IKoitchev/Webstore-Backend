package com.store.VideoGameStore.Dto;

import java.util.Date;

public class ReviewDto {

    private String author="";
    private long gameId=0;
    private String text="";

    public ReviewDto(String author, long gameId) {
        this.author = author;
        this.gameId = gameId;
    }

    public ReviewDto(String text) {
        this.text = text;
    }

    public ReviewDto(String author, long gameId, String text) {
        this.author = author;
        this.gameId = gameId;
        this.text = text;
    }

    public ReviewDto() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
