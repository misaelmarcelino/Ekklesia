package br.com.mimatech.Ekklesia.dto;

public record AddressResponseDto(

        String street,
        String number,
        String city,
        String state,
        String zipCode
) {}
