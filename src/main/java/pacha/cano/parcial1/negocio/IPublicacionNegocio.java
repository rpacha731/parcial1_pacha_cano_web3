package pacha.cano.parcial1.negocio;

import java.util.List;

import pacha.cano.parcial1.modelo.Publicacion;
import pacha.cano.parcial1.negocio.exceptions.*;

public interface IPublicacionNegocio {
	
	public List<Publicacion> listado () throws NegocioException; //ta
	
	public Publicacion agregar (Publicacion publicacion) throws NegocioException, EncontradoException; //ta
	
	public Publicacion modificar (Publicacion publicacion) throws NegocioException, NoEncontradoException; //ta
	
	public void eliminar (long id) throws NegocioException, NoEncontradoException; //ta
	
	public Publicacion cargar (long id) throws NegocioException, NoEncontradoException; //ta
	
	public Publicacion publicacionMasLikes () throws NegocioException, NoEncontradoException;
}
