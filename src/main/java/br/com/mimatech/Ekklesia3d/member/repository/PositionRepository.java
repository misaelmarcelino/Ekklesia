package br.com.mimatech.Ekklesia3d.member.repository;

import br.com.mimatech.Ekklesia3d.member.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
