package com.store.VideoGameStore.controllers;

import com.store.VideoGameStore.testConfig.TestSecurityConfig;
import com.store.VideoGameStore.repository.OrderItemRepository;
import com.store.VideoGameStore.repository.OrderRepository;
import com.store.VideoGameStore.repository.ReviewRepository;
import com.store.VideoGameStore.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestSecurityConfig.class)
public class OrderControllerTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @LocalServerPort
    int randomSeverPort;
    @Test
    void testGetFinishedOrdersPerUser() throws URISyntaxException {

        long userId=1;
        boolean orderFinished=true;

        RestTemplate restTemplate = new RestTemplate();

        final String baseURI = "http://localhost:" + randomSeverPort + "/orders/" + userId + "/" + orderFinished;
        URI uri = new URI(baseURI);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(false, result.getBody().isEmpty());
    }


}
