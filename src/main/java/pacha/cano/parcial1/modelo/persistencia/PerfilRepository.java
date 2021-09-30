package pacha.cano.parcial1.modelo.persistencia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pacha.cano.parcial1.modelo.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	
	Optional<Perfil> findByNombreUsuario(String nombreUsuario);
	
	Optional<Perfil> findFirstByCantidadSeguidoresIsNotNullOrderByCantidadSeguidoresDesc();
}
