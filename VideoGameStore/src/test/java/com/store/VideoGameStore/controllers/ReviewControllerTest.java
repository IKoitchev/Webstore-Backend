package com.store.VideoGameStore.controllers;

import com.store.VideoGameStore.models.Game;
import com.store.VideoGameStore.models.Review;
import com.store.VideoGameStore.repository.GameRepository;
import com.store.VideoGameStore.repository.ReviewRepository;
import com.store.VideoGameStore.testConfig.TestSecurityConfig;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestSecurityConfig.class)
public class ReviewControllerTest {


    @LocalServerPort
    int randomSeverPort;

    @Autowired
    GameRepository gameRepository;
    @Autowired
    ReviewRepository reviewRepository;






    @Test
    void getReviews() throws URISyntaxException {
        //get all reviews for a gem with id 1;

        long gameId = 1;
        RestTemplate restTemplate = new RestTemplate();

        final String baseURI = "http://localhost:" + randomSeverPort + "/reviews/" + gameId;
        URI uri = new URI(baseURI);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        List<Review> expected = reviewRepository.findAllByGameId(gameId);

        Assert.assertEquals(200, result.getStatusCodeValue());


    }




}
