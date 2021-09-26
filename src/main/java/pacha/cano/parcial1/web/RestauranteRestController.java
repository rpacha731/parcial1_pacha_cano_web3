package pacha.cano.parcial1.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pacha.cano.parcial1.modelo.Clase;
import pacha.cano.parcial1.negocio.IClaseNegocio;
import pacha.cano.parcial1.negocio.exceptions.*;

@RestController
public class RestauranteRestController {
	
	@Autowired
	private IClaseNegocio claseNegocio;
	
	@GetMapping(value = "/clase")
	public ResponseEntity<List<Clase>> listadoRestaurantes () {
		try {
			return new ResponseEntity<List<Clase>>(claseNegocio.listado(), HttpStatus.OK);
		} catch (NegocioException e) {
			return new ResponseEntity<List<Clase>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
