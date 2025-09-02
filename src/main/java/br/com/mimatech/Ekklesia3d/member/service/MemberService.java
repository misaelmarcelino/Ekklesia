package br.com.mimatech.Ekklesia3d.member.service;

import br.com.mimatech.Ekklesia3d.member.dto.MemberDto;
import br.com.mimatech.Ekklesia3d.member.dto.MemberRegisterDto;
import br.com.mimatech.Ekklesia3d.member.entities.Member;
import br.com.mimatech.Ekklesia3d.member.mapper.AddressMapper;
import br.com.mimatech.Ekklesia3d.member.mapper.MemberMapper;
import br.com.mimatech.Ekklesia3d.member.mapper.PositionMapper;
import br.com.mimatech.Ekklesia3d.member.repository.MemberRepository;
import br.com.mimatech.Ekklesia3d.shared.enums.AuthProvider;
import br.com.mimatech.Ekklesia3d.shared.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public MemberDto registerLocal(MemberRegisterDto dto) {

        System.out.println("DEBUG DTO = " + dto);


        if (dto.password() == null || dto.password().isBlank()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia");
        }

        Member member = MemberMapper.toMemberRegister(dto);
        member.setPassword(passwordEncoder.encode(dto.password()));
        member.setProvider(AuthProvider.LOCAL);
        Member saved = memberRepository.save(member);
        return MemberMapper.toMemberDto(saved);
    }

    @Transactional
    public MemberDto registerOAuth(String name, String email, String providerId, AuthProvider provider) {
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setPassword(null); // social não precisa de senha
        member.setProvider(provider);
        member.setProviderId(providerId);
        member.setRole(Role.MEMBER); // default role
        Member saved = memberRepository.save(member);
        return MemberMapper.toMemberDto(saved);
    }

    @Transactional(readOnly = true)
    public MemberDto findById(Long id){
        return memberRepository.findById(id)
                .map(MemberMapper::toMemberDto)
                .orElseThrow(() -> new RuntimeException("Membro não encontrado!"));
    }

    @Transactional(readOnly = true)
    public List<MemberDto> findAll(){
        return memberRepository.findAll()
                .stream()
                .map(MemberMapper::toMemberDto)
                .toList();
    }

    @Transactional
    public MemberDto update(Long id, MemberDto dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membro não encontrado"));

        // Atualiza os campos (sem senha e provider, que são tratados em outros fluxos)
        member.setName(dto.name());
        member.setEmail(dto.email());
        member.setPhone(dto.phone());
        member.setAge(dto.age());
        member.setDateOfBirth(dto.dateOfBirth());
        member.setDateOfBaptism(dto.dateOfBaptism());
        member.setAddress(dto.address() != null ? AddressMapper.toEntity(dto.address()) : null);
        member.setPosition(dto.position() != null ? PositionMapper.toEntity(dto.position()) : null);
        member.setRole(dto.role());

        Member saved = memberRepository.save(member);
        return MemberMapper.toMemberDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Membro não encontrado");
        }
        memberRepository.deleteById(id);
    }

}
