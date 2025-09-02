package br.com.mimatech.Ekklesia3d.member.dto;

import br.com.mimatech.Ekklesia3d.shared.enums.AuthProvider;
import br.com.mimatech.Ekklesia3d.shared.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(name = "MemberDto", description = "Dados de exibição de um membro")
public record MemberDto(

        @Schema(description = "Identificador único do membro", example = "1")
        Long id,

        @Schema(description = "Nome completo do membro", example = "Misael Souza Marcelino")
        String name,

        @Schema(description = "Email do membro", example = "misael@email.com")
        String email,

        @Schema(description = "Telefone do membro", example = "+55 11 99999-9999")
        String phone,

        @Schema(description = "Idade do membro", example = "28")
        Integer age,

        @Schema(description = "Data de nascimento", example = "1997-05-15")
        LocalDate dateOfBirth,

        @Schema(description = "Data de batismo", example = "2015-09-01")
        LocalDate dateOfBaptism,

        @Schema(description = "Endereço do membro")
        AddressDto address,

        @Schema(description = "Cargo ou posição ministerial do membro")
        PositionDto position,

        @Schema(description = "Perfil de acesso do membro", example = "MEMBER")
        Role role,

        @Schema(description = "Provedor de autenticação (LOCAL, GOOGLE, FACEBOOK, APPLE)", example = "LOCAL")
        AuthProvider provider,

        @Schema(description = "ID retornado pelo provedor externo (Google, Facebook, Apple)", example = "10923812908312")
        String providerId,

        @Schema(description = "Data de criação do registro", example = "2025-09-01T15:30:00")
        LocalDateTime createdAt,

        @Schema(description = "Data da última atualização do registro", example = "2025-09-01T18:45:00")
        LocalDateTime updatedAt
) {}
