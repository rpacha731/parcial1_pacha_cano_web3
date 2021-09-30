package pacha.cano.parcial1.modelo.persistencia;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pacha.cano.parcial1.modelo.Perfil;
import pacha.cano.parcial1.modelo.Publicacion;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
	
	Optional <Publicacion> findFirstByCantidadLikesIsNotNullOrderByCantidadLikesDesc();
	
	List<Publicacion> findAllByHoraPublicacionBetweenAndFechaPublicacion (Time horaInicio, Time horaFin, Date fecha);
}
