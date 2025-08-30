package br.com.mimatech.Ekklesia.dto;

public record AddressDto(

        Long id,
        String street,
        String number,
        String complement,
        String city,
        String state,
        String zipCode
) {
}
