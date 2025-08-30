package br.com.mimatech.Ekklesia3d.mapper;

import br.com.mimatech.Ekklesia3d.dto.MemberDto;
import br.com.mimatech.Ekklesia3d.entities.Member;

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
                member.getAddress(),
                member.getPosition(),
                member.getPassword(),
                member.getRole()
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
        member.setAddress(dto.address());
        member.setPosition(dto.position());
        member.setPassword(dto.password());
        member.setRole(dto.role());
        return member;

    }
}
