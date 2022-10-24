package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.example.demo.model.User;
import com.example.demo.properties.HttpProxyProperties;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class UserServiceTest {

  @Mock private RestTemplate restTemplate;
  @Mock private HttpProxyProperties httpProxyProperties;
  @InjectMocks private UserService userService;

  @Test
  public void ユーザ検証のテスト() {

    User user =
        User.builder()
            .email("email@test")
            .familyName("familyName")
            .givenName("givenName")
            .id("123")
            .locale("locale")
            .name("name")
            .picture("picture")
            .verifiedEmail(true)
            .build();

    Mockito.when(httpProxyProperties.getProxyServerHost()).thenReturn("");
    Mockito.when(restTemplate.exchange(any(), eq(User.class)))
        .thenReturn(new ResponseEntity<>(user, HttpStatus.OK));

    User result = userService.searchUser("token");

    assertEquals(user, result);
  }
}
