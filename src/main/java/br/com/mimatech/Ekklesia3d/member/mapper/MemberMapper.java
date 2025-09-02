package br.com.mimatech.Ekklesia3d.member.mapper;

import br.com.mimatech.Ekklesia3d.member.dto.MemberDto;
import br.com.mimatech.Ekklesia3d.member.dto.MemberRegisterDto;
import br.com.mimatech.Ekklesia3d.member.entities.Member;

public class MemberMapper {

    public static MemberDto toMemberDto(Member member) {
        return new MemberDto(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.getAge(),
                member.getDateOfBirth(),
                member.getDateOfBaptism(),
                member.getAddress() != null ? AddressMapper.toDto(member.getAddress()) : null,
                member.getPosition() != null ? PositionMapper.toDto(member.getPosition()) : null,
                member.getRole(),
                member.getProvider(),
                member.getProviderId(),
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }

    public static Member toMember(MemberDto dto) {
        Member member = new Member();

        member.setId(dto.id());
        member.setName(dto.name());
        member.setEmail(dto.email());
        member.setPhone(dto.phone());
        member.setAge(dto.age());
        member.setDateOfBirth(dto.dateOfBirth());
        member.setDateOfBaptism(dto.dateOfBaptism());
        member.setAddress(dto.address() != null ? AddressMapper.toEntity(dto.address()) : null);
        member.setPosition(dto.position() != null ? PositionMapper.toEntity(dto.position()) : null);
        member.setRole(dto.role());
        member.setProvider(dto.provider());
        member.setProviderId(dto.providerId());
        member.setCreatedAt(dto.createdAt());
        member.setUpdatedAt(dto.updatedAt());

        return member;
    }

    public static Member toMember(MemberRegisterDto dto) {
        Member member = new Member();
        member.setName(dto.name());
        member.setEmail(dto.email());
        member.setPhone(dto.phone());
        member.setAge(dto.age());
        member.setDateOfBirth(dto.dateOfBirth());
        member.setDateOfBaptism(dto.dateOfBaptism());
        member.setAddress(dto.address() != null ? AddressMapper.toEntity(dto.address()) : null);
        member.setPosition(dto.position() != null ? PositionMapper.toEntity(dto.position()) : null);
        member.setPassword(dto.password()); // será criptografada no service
        member.setRole(dto.role());
        member.setProvider(br.com.mimatech.Ekklesia3d.shared.enums.AuthProvider.LOCAL);
        return member;
    }
}
