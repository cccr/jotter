package com.halfofpoint.jotter.dbc;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfigDynamic implements AuthConfig {
    String userName;

    public AuthConfigDynamic() { }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
