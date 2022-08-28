package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.User;
import com.example.demo.properties.HttpProxyProperties;

@SpringBootTest
public class UserServiceTest {

    @Mock private RestTemplate restTemplate;
    @Mock private HttpProxyProperties httpProxyProperties;
    @InjectMocks private UserService userService;

	@Test
	public void ユーザ検証のテスト() {

        User user = new User();
        user.setEmail("email@test");
        user.setFamilyName("familyName");
        user.setGivenName("givenName");
        user.setId("123");
        user.setLocale("locale");
        user.setName("name");
        user.setPicture("picture");
        user.setVerifiedEmail(true);

        Mockito.when(httpProxyProperties.getProxyServerHost())
            .thenReturn("");
        Mockito.when(restTemplate.exchange(
              any(), eq(User.class)))
            .thenReturn(new ResponseEntity(user, HttpStatus.OK));

        User result = userService.searchUser("token");
        
        assertEquals(result.getId(), "123");
        assertEquals(result.getEmail(), "email@test");
        assertTrue(result.getVerifiedEmail());
        assertEquals(result.getName(), "name");
        assertEquals(result.getGivenName(), "givenName");
        assertEquals(result.getFamilyName(), "familyName");
        assertEquals(result.getPicture(), "picture");
        assertEquals(result.getLocale(), "locale");
    }
}
