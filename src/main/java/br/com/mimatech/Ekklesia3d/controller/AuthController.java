package br.com.mimatech.Ekklesia3d.controller;

import br.com.mimatech.Ekklesia3d.config.security.JwtService;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager; // Se usar, crie um @Bean AuthenticationManager
    private final UserDetailsService userDetailsService;
    private final JwtService jwt;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq req) {
        var authentication = new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
        authManager.authenticate(authentication); // dispara 401 se inválido

        var user = userDetailsService.loadUserByUsername(req.getUsername());
        var roles = user.getAuthorities().stream().map(a -> a.getAuthority()).toList();

        var access = jwt.generateAccessToken(user.getUsername(), roles);
        var refresh = jwt.generateRefreshToken(user.getUsername());

        // Dica: salve o refresh (hash) no banco ou Redis para rotação/invalidação
        return ResponseEntity.ok(new TokensRes(access, refresh));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshReq req) {
        var decoded = jwt.verify(req.refreshToken);
        var isRefresh = "refresh".equals(decoded.getClaim("type").asString());
        if (!isRefresh) return ResponseEntity.status(400).body("Token não é refresh");

        var username = decoded.getSubject();
        var user = userDetailsService.loadUserByUsername(username);
        List<String> roles = user.getAuthorities().stream().map(a -> a.getAuthority()).toList();

        // (opcional forte) Rotação: invalida o refresh antigo e emite um novo
        var access = jwt.generateAccessToken(username, roles);
        var newRefresh = jwt.generateRefreshToken(username);

        return ResponseEntity.ok(new TokensRes(access, newRefresh));
    }

    @Data
    static class LoginReq { @NotBlank private String username; @NotBlank private String password; }
    @Data
    static class RefreshReq { @NotBlank String refreshToken; }
    record TokensRes(String accessToken, String refreshToken) {}
}

