package br.com.mimatech.Ekklesia3d.security.service;

import br.com.mimatech.Ekklesia3d.member.entities.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final Key key;
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    public JwtService(@Value("${jwt.secret.key}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // ===== Extract =====
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ===== Generate =====
    public String generateToken(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", member.getId());
        claims.put("role", member.getRole().name());
        claims.put("provider", member.getProvider().name());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(member.getEmail()) // subject continua sendo o username
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ===== Validation =====
    public boolean isTokenValid(String token, String username) {
        final String extractedUser = extractUsername(token);
        return (extractedUser.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
