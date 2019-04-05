package com.example;

import com.example.domain.Review;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.*;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void testGetReviews() throws Exception {
        ResponseEntity<Review[]> entity = this.restTemplate.getForEntity("http://localhost:" + port + "/reviews", Review[].class);
        Review[] reviews = entity.getBody();
        assertTrue(reviews != null && reviews.length > 0);
    }
}
