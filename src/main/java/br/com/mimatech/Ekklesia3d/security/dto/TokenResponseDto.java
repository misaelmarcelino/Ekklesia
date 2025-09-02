package br.com.mimatech.Ekklesia3d.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TokenResponseDto", description = "Resposta do login, contendo o token JWT")
public record TokenResponseDto(

        @Schema(description = "Token JWT de autenticação", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String token
) {}
