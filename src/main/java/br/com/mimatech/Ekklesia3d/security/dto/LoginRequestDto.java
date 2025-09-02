package br.com.mimatech.Ekklesia3d.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LoginRequestDto", description = "Objeto usado para login local com email e senha")
public record LoginRequestDto(

        @Schema(description = "Email do usuário", example = "misael@email.com")
        String email,

        @Schema(description = "Senha do usuário", example = "strongPassword123")
        String password
) {}
