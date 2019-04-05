package com.example;

import com.example.domain.Review;
import com.example.domain.User;
import com.example.service.IReviewService;
import com.example.service.IUserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class DataLoader implements InitializingBean {

    @Value("classpath:data/reviews.txt")
    private Resource source;

    @Autowired
    DataSource dataSource;

    @Autowired
    private IReviewService reviewService;

    @Autowired
    private IUserService userService;

    @Override
    public void afterPropertiesSet() throws Exception {
        loadReviews(source);
        userService.addUser(new User("David"));
        userService.addUser(new User("Steve"));
    }

    private void loadReviews(Resource source) throws IOException {
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(source.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split("\\|");
                Review review = new Review(Integer.parseInt(parts[0]), parts[1]);
                reviewService.save(review);
            }
        }
    }
}
