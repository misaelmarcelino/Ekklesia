package br.com.mimatech.Ekklesia3d.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AddressDto", description = "Endereço do membro")
public record AddressDto(

        @Schema(description = "Nome da rua ou logradouro", example = "Rua das Flores")
        String street,

        @Schema(description = "Número do imóvel", example = "123")
        String number,

        @Schema(description = "Complemento (apto, bloco, sala, etc.)", example = "Apto 45")
        String complement,

        @Schema(description = "Cidade", example = "São Paulo")
        String city,

        @Schema(description = "Estado (UF)", example = "SP")
        String state,

        @Schema(description = "CEP do endereço", example = "01000-000")
        String zipCode
) {}
