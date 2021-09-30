package pacha.cano.parcial1.negocio;

import java.util.List;

import pacha.cano.parcial1.modelo.Perfil;
import pacha.cano.parcial1.negocio.exceptions.*;

public interface IPerfilNegocio {
	
	public List<Perfil> listado () throws NegocioException; //ta
	
	public Perfil agregar (Perfil perfil) throws NegocioException, EncontradoException, DuplicadoException; //ta
	
	public Perfil modificar (Perfil perfil) throws NegocioException, NoEncontradoException, DuplicadoException; //ta
	
	public void eliminar (long id) throws NegocioException, NoEncontradoException; //ta
	
	public Perfil cargar (long id) throws NegocioException, NoEncontradoException; //ta
	
	public Perfil perfilMasSeguidores () throws NegocioException, NoEncontradoException;
	
	public List<Perfil> perfilesMesYAnioNacimiento (int mes, int anio) throws NegocioException;
}
