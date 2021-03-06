package pacha.cano.parcial1.negocio;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pacha.cano.parcial1.modelo.Perfil;
import pacha.cano.parcial1.modelo.Publicacion;
import pacha.cano.parcial1.modelo.persistencia.PerfilRepository;
import pacha.cano.parcial1.modelo.persistencia.PublicacionRepository;
import pacha.cano.parcial1.negocio.exceptions.DuplicadoException;
import pacha.cano.parcial1.negocio.exceptions.EncontradoException;
import pacha.cano.parcial1.negocio.exceptions.NegocioException;
import pacha.cano.parcial1.negocio.exceptions.NoEncontradoException;
import pacha.cano.parcial1.negocio.exceptions.PublicacionAsignadaException;

@Service
public class PerfilNegocio implements IPerfilNegocio {
	
	private Logger log = LoggerFactory.getLogger(PerfilNegocio.class);
	
	@Autowired
	private PerfilRepository perfilDAO;
	@Autowired
	private PublicacionRepository publicacionDAO;

	@Override
	public List<Perfil> listado() throws NegocioException {
		try {
			return perfilDAO.findAll();
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	@Override
	public Perfil agregar(Perfil perfil) throws NegocioException, EncontradoException, DuplicadoException {
		Optional<Perfil> o;
		try {
			cargar(perfil.getId());
			throw new EncontradoException("Ya existe un perfil con id = " + perfil.getId());
		} catch (NoEncontradoException e) {
			o = perfilDAO.findByNombreUsuario(perfil.getNombreUsuario());
			if (o.isPresent())
				throw new DuplicadoException("Ya existe un perfil con el nombre de usuario = " + perfil.getNombreUsuario());

		}
		try {
			return perfilDAO.save(perfil);
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	@Override
	public Perfil modificar(Perfil perfil) throws NegocioException, NoEncontradoException, DuplicadoException {
		Perfil temp = cargar(perfil.getId()); 
		
		Optional<Perfil> o = perfilDAO.findByNombreUsuario(perfil.getNombreUsuario()); 
		
		if (o.isPresent() && temp.getId() != o.get().getId()) {
			throw new DuplicadoException("Ya existe otro perfil con el nombre de usuario = " + perfil.getNombreUsuario());
		}
		try {
			return perfilDAO.save(perfil);
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	@Override
	public void eliminar(long id) throws NegocioException, NoEncontradoException {
		cargar(id);

		try {
			perfilDAO.deleteById(id);
		} catch (Exception e) {
			throw new NegocioException(e);
		}

	}

	@Override
	public Perfil cargar(long id) throws NegocioException, NoEncontradoException {
		Optional<Perfil> o;
		try {
			o = perfilDAO.findById(id);
		} catch (Exception e) {
			throw new NegocioException(e);
		}
		if (!o.isPresent()) {
			throw new NoEncontradoException("No se encuentra el perfil con id = " + id);
		}
		return o.get();
	}

	@Override
	public Perfil perfilMasSeguidores() throws NegocioException, NoEncontradoException {
		Optional<Perfil> aux = null;
		try {
			aux = perfilDAO.findFirstByCantidadSeguidoresIsNotNullOrderByCantidadSeguidoresDesc();
		} catch (Exception e) {
			throw new NegocioException (e);
		}
		if (!aux.isPresent()) {
			throw new NoEncontradoException("No hay ningun perfil con m??s seguidores");
		}
		return aux.get();
	}

	@Override
	public List<Perfil> perfilesMesYAnioNacimiento(int mes, int anio) throws NegocioException {

		Date fechaInicio = Date.valueOf(LocalDate.of(anio, mes, 1));
		Date fechaFin = Date.valueOf(LocalDate.of(anio, mes, YearMonth.of(anio, mes).lengthOfMonth()));
				
		try {
			return perfilDAO.findAllByFechaNacimientoBetween(fechaInicio, fechaFin);
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	@Override
	public Perfil tituloPublicacionDesdePerfil(String tituloPublicacion)
			throws NegocioException, NoEncontradoException {
		Optional<Perfil> aux = null;
		try {
			aux = perfilDAO.findByPublicacionTitulo(tituloPublicacion);
		} catch (Exception e) {
			throw new NegocioException (e);
		}
		if (!aux.isPresent()) {
			throw new NoEncontradoException("No hay ninguna publicaci??n con el titulo = " + tituloPublicacion);
		}
		return aux.get();
	}

	@Override
	public List<Perfil> perfilesDesdeDireccion(String direccion) throws NegocioException, NoEncontradoException {
		
		try {
			List <Perfil> aux = perfilDAO.findAllByDireccion(direccion);
			if (aux.isEmpty())
				throw new NoEncontradoException("No hay ningun perfil con la direccion = " + direccion);
			return aux;
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	@Override
	public Publicacion publicacionDeUnPerfil(String nombre) throws NegocioException, NoEncontradoException {
		Optional<Perfil> aux = null;
		try {
			aux = perfilDAO.findByNombreUsuario(nombre);
		} catch (Exception e) {
			throw new NegocioException (e);
		}
		if (!aux.isPresent()) {
			throw new NoEncontradoException("No hay ning??n usuario con el nombre de usuario '" + nombre +"'");
		}
		
		if (aux.get().getPublicacion() == null) {
			throw new NoEncontradoException("Este usuario no tiene ninguna publicaci??n");
		}
		return aux.get().getPublicacion();
	}
	
	
	@Override
	public void asignarPublicacion(Long idPerfil, Long idPublicacion) throws PublicacionAsignadaException, NoEncontradoException, NegocioException {
		Optional <Perfil> temp = perfilDAO.findById(idPerfil); 
		Optional <Publicacion> temp2 = publicacionDAO.findById(idPublicacion);
				
		if (!temp.isPresent()) {
			throw new NoEncontradoException("No existe un perfil con id = " + idPerfil);
		}
		
		if (!temp2.isPresent()) {
			throw new NoEncontradoException("No existe una publicaci??n con id = " + idPublicacion);
		}
		

		if (temp.get().getPublicacion() != null) {
			
			throw new PublicacionAsignadaException("Este perfil ya tiene una publicaci??n asignada");
		}
		
		try {
			(temp.get()).setPublicacion(temp2.get());
			perfilDAO.save(temp.get());
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	@Override
	public Perfil publicacionMasLikesQueSeguidores() throws NegocioException, NoEncontradoException {
		List<Perfil> list = null;
		List<Perfil> aux = new ArrayList<Perfil>();
		
		long diferenciaMAX = 0;
		Perfil perfilMAX = null;
		
		try {
			list = perfilDAO.findAllByPublicacionIsNotNull();
		} catch (Exception e) {
			throw new NegocioException(e);
		}
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCantidadSeguidores() < list.get(i).getPublicacion().getCantidadLikes()) {
				aux.add(list.get(i));
			}
		}
		
		if (aux.isEmpty()) {throw new NoEncontradoException("No hay ningun perfil que su publicaci??n tenga m??s likes que seguidores");}
		
		
		for (int i = 0; i < aux.size(); i++) {

			if (aux.get(i).getPublicacion().getCantidadLikes() - aux.get(i).getCantidadSeguidores() > diferenciaMAX) {
				diferenciaMAX = aux.get(i).getPublicacion().getCantidadLikes() - aux.get(i).getCantidadSeguidores();
				perfilMAX = aux.get(i);
			}
		}
		
		return perfilMAX;
		
	}
}
