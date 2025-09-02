package br.com.mimatech.Ekklesia3d.repository;

import br.com.mimatech.Ekklesia3d.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
