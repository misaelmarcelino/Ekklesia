package br.com.mimatech.Ekklesia3d.dto;

import br.com.mimatech.Ekklesia3d.entities.Address;
import br.com.mimatech.Ekklesia3d.entities.Position;

import java.time.LocalDate;

public record MemberResponseDto(
        String name,
        String email,
        String phone,
        int age,
        LocalDate dateOfBirth,
        LocalDate dateOfBaptism,
        Address address,
        Position position
) {
}
