package br.com.mimatech.Ekklesia3d.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String key;

    @Value("${app.jwt.access-exp-min}")
    private long accessExpMin;

    @Value("${app.jwt.refresh-exp-days}")
    private long refreshExpDays;

    private Algorithm algorithm(){
        return Algorithm.HMAC256(key);
    }

    public String generateToken(String username, Collection<String> roles){
        Instant now = Instant.now();
        return JWT.create()
                .withIssuer("sua-api")
                .withSubject(username)
                .withClaim("roles", roles)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(Duration.ofMinutes(accessExpMin))))
                .sign(algorithm());
    }

    public String generateRefreshToken(String username) {
        Instant now = Instant.now();
        return JWT.create()
                .withIssuer("sua-api")
                .withSubject(username)
                .withClaim("type", "refresh")
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(Duration.ofDays(refreshExpDays))))
                .sign(algorithm());
    }

    public DecodedJWT verify(String token) throws JWTVerificationException {
        return JWT.require(algorithm())
                .withIssuer("sua-api")
                .build()
                .verify(token);
    }

}
