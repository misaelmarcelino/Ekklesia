package br.com.mimatech.Ekklesia3d.repository;

import br.com.mimatech.Ekklesia3d.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
