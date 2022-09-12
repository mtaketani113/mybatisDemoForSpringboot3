package com.example.demo.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class HttpProxyProperties implements EnvironmentAware {

  private String proxyServerHost;

  private String proxyServerPort;

  @Override
  public void setEnvironment(Environment env) {
    this.proxyServerHost = env.getProperty("http.proxy.host");
    this.proxyServerPort = env.getProperty("http.proxy.port");
  }
}
