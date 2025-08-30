package br.com.mimatech.Ekklesia3d.dto;

public record AddressResponseDto(

        String street,
        String number,
        String city,
        String state,
        String zipCode
) {}
