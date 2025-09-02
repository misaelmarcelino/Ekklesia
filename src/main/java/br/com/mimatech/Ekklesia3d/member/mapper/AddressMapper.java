package br.com.mimatech.Ekklesia3d.member.mapper;

import br.com.mimatech.Ekklesia3d.member.dto.AddressDto;
import br.com.mimatech.Ekklesia3d.member.entities.Address;

public class AddressMapper {

    public static AddressDto toDto(Address address) {
        return new AddressDto(
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getCity(),
                address.getState(),
                address.getZipCode());
    }
    public static Address toEntity(AddressDto dto) {
        Address address = new Address();
        address.setStreet(dto.street());
        address.setNumber(dto.number());
        address.setComplement(dto.complement());
        address.setCity(dto.city());
        address.setState(dto.state());
        address.setZipCode(dto.zipCode());
        return address;
    }
}
