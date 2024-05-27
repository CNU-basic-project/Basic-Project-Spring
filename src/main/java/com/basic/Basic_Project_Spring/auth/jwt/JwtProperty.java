package com.basic.Basic_Project_Spring.auth.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtProperty (
    String secretKey,
    Long accessTokenExpirationDay
) {
}
