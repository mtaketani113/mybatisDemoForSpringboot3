package com.example.demo.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

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
