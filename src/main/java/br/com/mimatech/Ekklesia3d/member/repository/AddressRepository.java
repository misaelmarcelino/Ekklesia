package br.com.mimatech.Ekklesia3d.member.repository;

import br.com.mimatech.Ekklesia3d.member.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
