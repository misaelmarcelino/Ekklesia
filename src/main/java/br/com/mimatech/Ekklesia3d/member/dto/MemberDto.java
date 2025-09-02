package br.com.mimatech.Ekklesia3d.dto;

import br.com.mimatech.Ekklesia3d.entities.Address;
import br.com.mimatech.Ekklesia3d.entities.Position;
import br.com.mimatech.Ekklesia3d.enums.Role;


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
