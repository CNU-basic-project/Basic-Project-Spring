package com.basic.Basic_Project_Spring.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.basic.Basic_Project_Spring.common.exception.UnAuthorizeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.MICROSECONDS;

@Service
public class JwtService {

    private final ObjectMapper objectMapper;
    private final long accessToeknExpirationDayToMills;
    private final Algorithm algorithm;

    public JwtService(JwtProperty jwtProperty) {
        this.objectMapper = new ObjectMapper();
        this.accessToeknExpirationDayToMills = MICROSECONDS.convert(jwtProperty.accessTokenExpirationDay(), DAYS);
        this.algorithm = Algorithm.HMAC512(jwtProperty.secretKey());
    }
    public String createToken(Long memberId) {
        return JWT.create()
                .withExpiresAt(new Date(
                        accessToeknExpirationDayToMills + System.currentTimeMillis()
                ))
                .withIssuedAt(new Date())
                .withClaim("memberId", memberId)
                .sign(algorithm);
    }

    public Long extractMemberId(String token) {
        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getClaim("memberId")
                    .asLong();
        } catch (JWTVerificationException e) {
            throw new UnAuthorizeException("유효하지 않은 토큰입니다.");
        }
    }
}
