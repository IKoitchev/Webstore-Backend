package com.store.VideoGameStore.controllers;


import com.store.VideoGameStore.Dto.ReviewDto;
import com.store.VideoGameStore.models.Review;
import com.store.VideoGameStore.repository.GameRepository;
import com.store.VideoGameStore.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;



    @GetMapping("/{id}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable(value = "id") long gameId) {

        List<Review> reviews = reviewRepository.findAllByGameId(gameId);

        return ResponseEntity.ok().body(reviews);
    }

    @PostMapping( consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> purchaseGame(@RequestBody List<ReviewDto> reviewDtos) {

        List<Review> reviews = new ArrayList<>();
        reviewDtos.forEach(reviewDto -> {
            Optional<Review> currentReview = reviewRepository.findByAuthorAndGameId(reviewDto.getAuthor(), reviewDto.getGameId());
            if(!currentReview.isPresent()){
                Review review = new Review(reviewDto.getAuthor(), reviewDto.getGameId());
                reviews.add(review);
            }
        });

        reviewRepository.saveAll(reviews);
        return ResponseEntity.ok().body(reviews);

    }
    @PutMapping( consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addReview(@RequestBody ReviewDto reviewDto) {

        Optional<Review> currentReview = reviewRepository.findByAuthorAndGameId(reviewDto.getAuthor(), reviewDto.getGameId());

        if(!currentReview.isPresent()){
            return ResponseEntity.badRequest().body("Game not owned");
        }

        currentReview.get().setText(reviewDto.getText());
        currentReview.get().setDatePosted(new Date());
        reviewRepository.save(currentReview.get());
        return ResponseEntity.ok().body(reviewRepository.findAllByGameId(reviewDto.getGameId()));
    }
    @DeleteMapping(value = "/delete/{gameId}/{username}")
    public ResponseEntity<?> refundProduct(@PathVariable(value = "gameId") long gameId, @PathVariable(value = "username") String username) {

        Optional<Review> currentReview = reviewRepository.findByAuthorAndGameId(username,gameId);
        if(!currentReview.isPresent()){
            return ResponseEntity.badRequest().body("Game not owned");
        }
        reviewRepository.delete(currentReview.get());
        return ResponseEntity.ok().body("Game refunded successfully");
    }

}
