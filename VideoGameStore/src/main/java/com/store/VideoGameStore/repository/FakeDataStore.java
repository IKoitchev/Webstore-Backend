package com.store.VideoGameStore.repository;

import com.store.VideoGameStore.models.Game;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FakeDataStore {

    private List<Game> gameList = new ArrayList<>();

    public FakeDataStore(){
        gameList.add(new Game (1, "Among us", "social", "--", 4));
    }

    public List<Game> getAllGames () {
        return gameList;
    }
    private Game getGame(int id) {
        for(Game game : gameList){
            if(game.getId() == id) {
                return game;
            }
        }
        return null;
    }
    public Game getGameByName(String name) {
        for(Game game : gameList){
            if(game.getName().equals(name)) {
                return game;
            }
        }
        return null;
    }

    public boolean deleteGame(int id) {
        Game game = getGame(id);
        if (game == null) {
            return false;
        }
        return gameList.remove(game);
    }

    public boolean addGame(Game game) {
        if(this.getGame(game.getId()) == null) {
            gameList.add(game);
            return true;
        }
        return false;
    }
    public boolean updateGameName(Game game) {
        Game old = getGame(game.getId());

        if(game == null){
            return false;
        }
        old.setName(game.getName());
        return true;
    }


}
