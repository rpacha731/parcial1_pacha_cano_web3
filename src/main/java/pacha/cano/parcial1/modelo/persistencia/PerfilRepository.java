package pacha.cano.parcial1.modelo.persistencia;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pacha.cano.parcial1.modelo.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	
	Optional<Perfil> findByNombreUsuario(String nombreUsuario);
	
	Optional<Perfil> findFirstByCantidadSeguidoresIsNotNullOrderByCantidadSeguidoresDesc();
	
	List<Perfil> findAllByFechaNacimientoBetween (Date fechaInicio, Date fechaFin); // fechaInicio y fechaFin es una fecha creada a 
																					//	partir del mes y el año traidos desde la web
	Optional<Perfil> findByPublicacionTitulo (String tituloPublicacion);
	
	List<Perfil> findAllByDireccion (String direccion);
	
	List<Perfil> findAllByPublicacionIsNotNull ();
	
}
