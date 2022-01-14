package com.store.VideoGameStore.models;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:8080/reviews")
@Entity(name = "reviews")
public class Review {

    // this model represents the relation between user and game
    // each user can write 1 review per game they own
    // review entity is saved upon product purchase with an empty text
    // its text is updated when the user decides to "write a review"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "text")
    private String text = "";
    @Column(name = "author")
    private String author;
    @Column(name = "game_id")
    private long gameId;
    @Column(name="date_posted")
    private Date datePosted ;

    public Review(String author, long gameId, Date datePosted) {
        this.author = author;
        this.gameId = gameId;
        this.datePosted = datePosted;
    }
    public Review(String author, long gameId) {
        this.author = author;
        this.gameId = gameId;
    }

    public Review() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }
}
