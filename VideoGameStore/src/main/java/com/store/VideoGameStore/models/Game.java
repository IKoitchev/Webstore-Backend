package com.store.VideoGameStore.models;

import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@CrossOrigin(origins = "http://localhost:8080/games")
@Entity(name="game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "genre")
    private String genre = " ";
    @Column(name = "description")
    private String description = " ";
    @Column(name = "price")
    private double price = 0;
    @Column(name="author")
    private String author = " ";
    @Column(name="url")
    private String imageUrl;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Game (){

    }
    public Game(long id, String name, String genre, String description, double price) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.price = price;
    }
    public Game(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return this.id + "\\r\\n" + this.name + "\\r\\n" + this.genre;
    }
}
