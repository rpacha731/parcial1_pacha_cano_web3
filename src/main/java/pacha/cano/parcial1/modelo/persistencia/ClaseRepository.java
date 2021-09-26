package pacha.cano.parcial1.modelo.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pacha.cano.parcial1.modelo.Clase;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {

}
