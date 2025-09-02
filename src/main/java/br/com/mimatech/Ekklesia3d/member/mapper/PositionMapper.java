package br.com.mimatech.Ekklesia3d.member.mapper;

import br.com.mimatech.Ekklesia3d.member.dto.PositionDto;
import br.com.mimatech.Ekklesia3d.member.entities.Position;

public class PositionMapper {

    public static PositionDto toDto(Position position) {
        return new PositionDto(
                position.getPosition(),
                position.getDescription()
        );
    }

    public static Position toEntity(PositionDto dto) {
        Position position = new Position();
        position.setPosition(dto.position());
        position.setDescription(dto.description());
        return position;
    }
}
