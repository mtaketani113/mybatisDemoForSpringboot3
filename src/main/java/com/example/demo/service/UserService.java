package com.example.demo.service;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.User;

@Service
public class UserService {

    public User searchUser(String accessToken){

        RestTemplate rest = new RestTemplate();

        final String url = "https://www.googleapis.com/oauth2/v1/userinfo";

        // ResponseEntity<User> response = rest.getForEntity(url, User.class, HttpMethod.GET, header);
        RequestEntity requestEntity = RequestEntity
            .get(url)
            .header("Authorization", "Bearer " + accessToken)
            .build();
        ResponseEntity<User> response = rest.exchange(requestEntity, User.class);

        return response.getBody();
    }
    
}
