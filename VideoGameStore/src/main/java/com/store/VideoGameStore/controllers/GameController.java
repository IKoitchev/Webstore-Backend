package com.store.VideoGameStore.controllers;

import com.store.VideoGameStore.models.Game;
import com.store.VideoGameStore.repository.FakeDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    FakeDataStore fds = new FakeDataStore();


    @GetMapping("/all")
    public List<Game> getAllGames(){
        return fds.getAllGames();
    }

    @GetMapping("{name}")
    public ResponseEntity<Game> getGame(@PathVariable(value = "name") String name) {
        Game game = fds.getGameByName(name);

        if(game != null) {
            return ResponseEntity.ok().body(game);
        }
        return ResponseEntity.notFound().build();

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteGame(@PathVariable(value = "id") int id) {
        if (fds.deleteGame(id)){
            return ResponseEntity.ok().build();
        }
        String entity = "game with id " + id + " doesn't exist";
        return new ResponseEntity(entity, HttpStatus.CONFLICT);

    }
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Game> addGame(@RequestBody Game game) {

        if(!fds.addGame(game)){
            String entity = "game with id " + game.getId() + " already exists";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }

        return new ResponseEntity(game, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {
        //check if more than 1 params are updated in the future

        if(fds.updateGameName(game)){
            return new ResponseEntity(game, HttpStatus.CREATED);

        }
        return new ResponseEntity( "Please provide a valid game name", HttpStatus.NOT_FOUND);
    }

}
