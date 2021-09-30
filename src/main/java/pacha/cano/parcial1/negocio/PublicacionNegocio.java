package pacha.cano.parcial1.negocio;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pacha.cano.parcial1.modelo.Perfil;
import pacha.cano.parcial1.modelo.Publicacion;
import pacha.cano.parcial1.modelo.persistencia.PublicacionRepository;
import pacha.cano.parcial1.negocio.exceptions.*;

@Service
public class PublicacionNegocio implements IPublicacionNegocio {

	private Logger log = LoggerFactory.getLogger(PublicacionNegocio.class);
	@Autowired
	private PublicacionRepository publicacionDAO;

	@Override
	public List<Publicacion> listado() throws NegocioException {
		try {
			return publicacionDAO.findAll();
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	@Override
	public Publicacion agregar(Publicacion publicacion)
			throws NegocioException, EncontradoException {
		try {
			cargar(publicacion.getId());
			throw new EncontradoException("Ya existe una publicacion con id = " + publicacion.getId());
		} catch (NoEncontradoException e) {
			try {
				return publicacionDAO.save(publicacion);
			} catch (Exception ex) {
				throw new NegocioException(ex);
			}
		}

	}

	@Override
	public Publicacion modificar(Publicacion publicacion)
			throws NegocioException, NoEncontradoException {
		cargar(publicacion.getId());
		try {
			return publicacionDAO.save(publicacion);
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	@Override
	public void eliminar(long id) throws NegocioException, NoEncontradoException {
		cargar(id);

		try {
			publicacionDAO.deleteById(id);
		} catch (Exception e) {
			throw new NegocioException(e);
		}

	}

	@Override
	public Publicacion cargar(long id) throws NegocioException, NoEncontradoException {
		Optional<Publicacion> o;
		try {
			o = publicacionDAO.findById(id);
		} catch (Exception e) {
			throw new NegocioException(e);
		}
		if (!o.isPresent()) {
			throw new NoEncontradoException("No se encuentra la publicacion con id = " + id);
		}
		return o.get();
	}

	@Override
	public Publicacion publicacionMasLikes() throws NegocioException, NoEncontradoException {
		Optional<Publicacion> aux = null;
		try {
			aux = publicacionDAO.findFirstByCantidadLikesIsNotNullOrderByCantidadLikesDesc();
		} catch (Exception e) {
			throw new NegocioException (e);
		}
		if (!aux.isPresent()) {
			throw new NoEncontradoException("No hay ningun publicacion con muchos likes");
		}
		return aux.get();
	}

	@Override
	public List<Publicacion> publicacionesEnUnaHora(int hora) throws NegocioException, NoEncontradoException {
		
		Time horaInicio = new Time (hora, 0, 0);
		Time horaFin = new Time (hora, 59, 59);
		Date fecha = Date.valueOf(LocalDate.now().minusDays(1));
		
		List<Publicacion> aux = null;
		
		try {
			aux = publicacionDAO.findAllByHoraPublicacionBetweenAndFechaPublicacion(horaInicio, horaFin, fecha);
			
		} catch (Exception e){
			throw new NegocioException (e);
		}
		if ( aux.isEmpty()) {
				throw new NoEncontradoException("No hay ninguna publicación de ayer, en esa hora");
			}
		
		return aux;
	}
}
