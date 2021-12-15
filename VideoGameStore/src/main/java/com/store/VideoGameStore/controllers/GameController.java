package com.store.VideoGameStore.controllers;

import com.store.VideoGameStore.models.Game;
import com.store.VideoGameStore.repository.FakeDataStore;
import com.store.VideoGameStore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    FakeDataStore fds = new FakeDataStore();

    @Autowired
    GameRepository gameRepository;

    @GetMapping("/all")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("{name}")
    public ResponseEntity<Game> getGame(@PathVariable(value = "name") String name) {
        //Game game = fds.getGameByName(name);
        Game game = gameRepository.findByName(name);

        if (game != null) {
            return ResponseEntity.ok().body(game);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteGame(@PathVariable(value = "id") long id) {
        if (gameRepository.findById(id).isPresent()) {
            gameRepository.deleteById(id);
            return ResponseEntity.ok().body("Game removed successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Game with id " + id + " does not exist");

    }


    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity addGame(@RequestBody Game game) {

        if(gameRepository.findByName(game.getName()) != null){

            return ResponseEntity.badRequest().body("game with id " + game.getId() + " already exists!");
        }
        gameRepository.save(game);
        return ResponseEntity.created(URI.create(String.format("games/%s", game.getName()))).body("Game created successfully!");
    }

    @PutMapping(value = "/")
    public ResponseEntity updateGame(@RequestBody Game game) {
        //check if more than 1 params are updated in the future

        if(gameRepository.findById(game.getId()) == null){

            return ResponseEntity.badRequest().body("game with id " + game.getId() + " does not exist!");
        }
        gameRepository.save(game);
        return ResponseEntity.created(URI.create(String.format("games/%s", game.getName().
                replaceAll(" ","%20")))).body("Game updated successfully!");
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<Game> getGameByGenre(@PathVariable(value="genre") String genre) {

        return ResponseEntity.ok().body(gameRepository.findByGenre(genre));

    }

}
