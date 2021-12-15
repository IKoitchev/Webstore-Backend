package com.store.VideoGameStore.controllers;

import com.store.VideoGameStore.VideoGameStoreApplication;

import com.store.VideoGameStore.models.Game;
import com.store.VideoGameStore.repository.GameRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class GameControllerTest {


    @LocalServerPort
    int randomSeverPort;

    @Autowired
    GameRepository gameRepository;

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
        game.setName("testGame 2");
        game.setPrice(15);
        game.setDescription("A mock game used for testing but updated");
        game.setGenre("new genre");

        final String baseURI = "http://localhost:" + randomSeverPort + "/games/";
        URI uri = new URI(baseURI);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        //game.setName(game.getName().replaceAll(" ","%20"));
        HttpEntity<Game> request = new HttpEntity<>(game, httpHeaders);
        //System.out.println(request.getBody().getName() + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);


        Assert.assertEquals(201, result.getStatusCodeValue());


        gameRepository.deleteById(gameRepository.findByName("testGame 2").getId());

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


}
