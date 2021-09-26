package pacha.cano.parcial1.negocio;

import java.util.List;

import pacha.cano.parcial1.modelo.Clase;
import pacha.cano.parcial1.negocio.exceptions.*;

public interface IClaseNegocio {
	
	public List<Clase> listado () throws NegocioException;
	
	public Clase agregar (Clase clase) throws NegocioException, EncontradoException, DuplicadoException;
	
	public Clase modificar (Clase clase) throws NegocioException, NoEncontradoException, DuplicadoException;
	
	public void eliminar (long id) throws NegocioException, NoEncontradoException;
	
	public Clase cargar (long id) throws NegocioException, NoEncontradoException;
}
