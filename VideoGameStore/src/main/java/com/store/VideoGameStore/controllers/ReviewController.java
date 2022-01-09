package com.store.VideoGameStore.controllers;


import com.store.VideoGameStore.models.Review;
import com.store.VideoGameStore.repository.ReviewRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;


    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReviews() {

        List<Review> reviews = reviewRepository.findAll();
        return ResponseEntity.ok(reviews);
    }

}
