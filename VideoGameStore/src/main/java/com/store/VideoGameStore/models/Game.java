package com.store.VideoGameStore.models;

public class Game {

    private int id;
    private String name;
    private String genre = " ";
    private String description = " ";
    private double price = 0;

    public Game (){

    }
    public Game(int id, String name, String genre, String description, double price) {
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

    public int getId() {
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
