package pacha.cano.parcial1.modelo.persistencia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pacha.cano.parcial1.modelo.Publicacion;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
	
	Optional <Publicacion> findFirstByCantidadLikesIsNotNullOrderByCantidadLikesDesc();
}
