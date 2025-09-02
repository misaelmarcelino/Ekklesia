package br.com.mimatech.Ekklesia3d.member.repository;

import br.com.mimatech.Ekklesia3d.member.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
