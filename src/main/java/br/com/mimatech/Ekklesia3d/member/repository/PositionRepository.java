package br.com.mimatech.Ekklesia3d.repository;

import br.com.mimatech.Ekklesia3d.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
