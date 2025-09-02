package br.com.mimatech.Ekklesia3d.member.dto;

import br.com.mimatech.Ekklesia3d.shared.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(name = "MemberRegisterDto", description = "Objeto usado para registro local de um membro")
public record MemberRegisterDto(

        @Schema(description = "Nome completo do membro", example = "Misael Souza Marcelino")
        String name,

        @Schema(description = "Email do membro (único no sistema)", example = "misael@email.com")
        String email,

        @Schema(description = "Telefone de contato", example = "+55 11 99999-9999")
        String phone,

        @Schema(description = "Idade do membro", example = "28")
        int age,

        @Schema(description = "Data de nascimento", example = "1997-05-15")
        LocalDate dateOfBirth,

        @Schema(description = "Data de batismo", example = "2015-09-01")
        LocalDate dateOfBaptism,

        @Schema(description = "Endereço do membro")
        AddressDto address,

        @Schema(description = "Cargo ou posição ministerial")
        PositionDto position,

        @Schema(description = "Senha de acesso (será criptografada no backend)", example = "strongPassword123")
        String password,

        @Schema(description = "Perfil de acesso do membro", example = "MEMBER")
        Role role
) {}
