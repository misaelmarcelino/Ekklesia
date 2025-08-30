package br.com.mimatech.Ekklesia.dto;

import br.com.mimatech.Ekklesia.entities.Address;
import br.com.mimatech.Ekklesia.entities.Position;
import br.com.mimatech.Ekklesia.enums.Role;


import java.time.LocalDate;

public record MemberDto(

        Long id,
        String name,
        String email,
        String phone,
        int age,
        LocalDate dateOfBirth,
        LocalDate dateOfBaptism,
        Address address,
        Position position,
        String password,
        Role role
) {
}
