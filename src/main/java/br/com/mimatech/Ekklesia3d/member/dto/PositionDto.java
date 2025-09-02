package br.com.mimatech.Ekklesia3d.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PositionDto", description = "Cargo ou posição ministerial do membro")
public record PositionDto(

        @Schema(description = "Nome do cargo ou posição", example = "Diácono")
        String position,

        @Schema(description = "Descrição ou detalhes sobre o cargo", example = "Responsável por auxiliar no serviço da igreja")
        String description
) {}
