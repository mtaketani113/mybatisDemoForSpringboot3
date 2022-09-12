package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.properties.HttpProxyProperties;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

  @Autowired private HttpProxyProperties httpProxyProperties;
  @Autowired private RestTemplate restTemplate;

  public User searchUser(String accessToken) {

    if (!StringUtils.isEmpty(httpProxyProperties.getProxyServerHost())
        && !StringUtils.isEmpty(httpProxyProperties.getProxyServerPort())) {
      Proxy proxy =
          new Proxy(
              Type.HTTP,
              new InetSocketAddress(
                  httpProxyProperties.getProxyServerHost(),
                  Integer.parseInt(httpProxyProperties.getProxyServerPort())));
      SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
      requestFactory.setProxy(proxy);
      restTemplate.setRequestFactory(requestFactory);
    }

    final String url = "https://www.googleapis.com/oauth2/v1/userinfo";

    RequestEntity<?> requestEntity =
        RequestEntity.get(url).header("Authorization", "Bearer " + accessToken).build();
    ResponseEntity<User> response = restTemplate.exchange(requestEntity, User.class);

    return response.getBody();
  }
}
