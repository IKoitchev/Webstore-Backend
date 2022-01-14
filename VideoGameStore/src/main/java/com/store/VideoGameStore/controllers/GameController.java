package com.store.VideoGameStore.controllers;

import com.store.VideoGameStore.models.Game;
import com.store.VideoGameStore.models.Review;
import com.store.VideoGameStore.repository.FakeDataStore;
import com.store.VideoGameStore.repository.GameRepository;
import com.store.VideoGameStore.repository.OrderItemRepository;
import com.store.VideoGameStore.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    FakeDataStore fds = new FakeDataStore();

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

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
    @GetMapping("/ownedBy/{username}")
    public ResponseEntity<?> getAllGamesForUser(@PathVariable(value="username") String username){

        //each review in this list represents an owned game
        List<Review> reviewsByAuthor = reviewRepository.findAllByAuthor(username);
        List<Game> ownedGames = new ArrayList<>();

        reviewsByAuthor.forEach(review -> {
            Optional<Game> game = gameRepository.findById(review.getGameId());
            if(game.isPresent()){
                ownedGames.add(game.get());
            }
        });
        return ResponseEntity.ok().body(ownedGames);

    }
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteGame(@PathVariable(value = "id") long id) {
        if (gameRepository.findById(id).isPresent()) {
            orderItemRepository.deleteAllByGameId(id);
            gameRepository.deleteById(id);
            return ResponseEntity.ok().body(gameRepository.findAll());
        }
        return ResponseEntity.badRequest().body("Error: Game with id " + id + " does not exist");

    }


    @PostMapping( consumes = "application/json", produces = "application/json")
    public ResponseEntity addGame(@RequestBody Game game) {

        if(gameRepository.findByName(game.getName()) != null){

            return ResponseEntity.badRequest().body("game with name " + game.getName() + " already exists!");
        }
        gameRepository.save(game);
        return ResponseEntity.created(URI.create(String.format("games/%s", game.getName().replaceAll(" ", "%20") ))).body("Game created successfully!");
    }

    @PutMapping( consumes = "application/json", produces = "application/json")
    public ResponseEntity updateGame(@RequestBody Game game) {
        //check if more than 1 params are updated in the future

        if(!gameRepository.existsByName(game.getName())){

            return ResponseEntity.badRequest().body("game with name " + game.getName() + " does not exist!");
        }
        gameRepository.save(game);
        return ResponseEntity.created(URI.create(String.format("games/%s", game.getName().
                replace(" ","%20")))).body("Game updated successfully!");
    }



}
