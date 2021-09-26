package pacha.cano.parcial1.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pacha.cano.parcial1.modelo.Clase;
import pacha.cano.parcial1.modelo.persistencia.ClaseRepository;
import pacha.cano.parcial1.negocio.exceptions.DuplicadoException;
import pacha.cano.parcial1.negocio.exceptions.EncontradoException;
import pacha.cano.parcial1.negocio.exceptions.NegocioException;
import pacha.cano.parcial1.negocio.exceptions.NoEncontradoException;

public class ClaseNegocio implements IClaseNegocio {
	
	@Autowired
	private ClaseRepository claseDAO;

	@Override
	public List<Clase> listado() throws NegocioException {
		try {
			return claseDAO.findAll();
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	@Override
	public Clase agregar(Clase clase) throws NegocioException, EncontradoException, DuplicadoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clase modificar(Clase clase) throws NegocioException, NoEncontradoException, DuplicadoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(long id) throws NegocioException, NoEncontradoException {
		// TODO Auto-generated method stub

	}

	@Override
	public Clase cargar(long id) throws NegocioException, NoEncontradoException {
		// TODO Auto-generated method stub
		return null;
	}

}
