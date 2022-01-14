package com.store.VideoGameStore.controllers;

import com.store.VideoGameStore.testConfig.TestSecurityConfig;

import com.store.VideoGameStore.models.Game;
import com.store.VideoGameStore.repository.GameRepository;
import com.store.VideoGameStore.repository.ReviewRepository;
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


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestSecurityConfig.class)

public class GameControllerTest {


    @LocalServerPort
    int randomSeverPort;

    @Autowired
    GameRepository gameRepository;
    @Autowired
    ReviewRepository reviewRepository;

    public Game getTestGame() {
        Game game = new Game();
        game.setName("testGame");
        game.setPrice(4);
        game.setDescription("A mock game used for testing");
        game.setGenre("fictional game");

        return game;
    }




    @Test
    void testGetAllGames() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();

        final String baseURI = "http://localhost:" + randomSeverPort + "/games/all";
        URI uri = new URI(baseURI);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("id"));
        Assert.assertEquals(true, result.getBody().contains("name"));
        Assert.assertEquals(true, result.getBody().contains("description"));
        Assert.assertEquals(true, result.getBody().contains("price"));
        //Assert.assertTrue(false);

    }
    @Test
    void testGetProductByName() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();
        String gameName = "Among us";

        final String baseURI = "http://localhost:" + randomSeverPort + "/games/" + gameName.replaceAll(" ", "%20");
        URI uri = new URI(baseURI);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
    }
    @Test
    void testAddProduct() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();
        Game game = getTestGame();

        final String baseURI = "http://localhost:" + randomSeverPort + "/games/";
        URI uri = new URI(baseURI);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Game> request = new HttpEntity<>(game, httpHeaders);
        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        Assert.assertEquals(201, result.getStatusCodeValue());

        gameRepository.deleteById(gameRepository.findByName("testGame").getId());

    }
    @Test
    void testUpdateGame() throws URISyntaxException {


        RestTemplate restTemplate = new RestTemplate();
        gameRepository.save(getTestGame());
        Game game = gameRepository.findByName("testGame");
        game.setName("testGame");
        game.setPrice(15);
        game.setDescription("A mock game used for testing but updated");
        game.setGenre("new genre");

        final String baseURI = "http://localhost:" + randomSeverPort + "/games/";
        URI uri = new URI(baseURI);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<Game> request = new HttpEntity<>(game, httpHeaders);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);


        Assert.assertEquals(201, result.getStatusCodeValue());


        gameRepository.deleteById(gameRepository.findByName("testGame").getId());

    }
    //unhappy flow !
    @Test
    void TestUpdateGameThatDoesNotExist() throws URISyntaxException {


        RestTemplate restTemplate = new RestTemplate();

        Game game = getTestGame();
        game.setName("testGame 2");
        game.setPrice(15);
        game.setDescription("A mock game used for testing but updated");
        game.setGenre("new genre");

        final String baseURI = "http://localhost:" + randomSeverPort + "/games/";
        URI uri = new URI(baseURI);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Game> request = new HttpEntity<>(game, httpHeaders);

        try {
            ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);
            Assert.assertEquals(400, result.getStatusCodeValue());
             Assert.assertEquals("game with name " + game.getName() + " does not exist!", result.getBody());

        } catch (HttpStatusCodeException e) {
            // if it goes in catch then it has returned a 401 code, which is what i am looking for in this test
            //rest template crashes no 4xx codes by default
                Assert.assertTrue(!e.getMessage().isEmpty());
        }



    }
    @Test
    void testDeleteProductId() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        gameRepository.save(getTestGame());
        Game game = gameRepository.findByName("testGame");

        final String baseURI = "http://localhost:" + randomSeverPort + "/games/delete/" + game.getId();
        URI uri = new URI(baseURI);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.DELETE, null, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
    }
    @Test
    void testGetAllGamesOwnedByUser() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();
        String username = "TestUser";

        final String baseURI = "http://localhost:" + randomSeverPort + "/games/ownedBy/" + username;
        URI uri = new URI(baseURI);

        ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);


        Assert.assertEquals(200, result.getStatusCodeValue());

    }



}
