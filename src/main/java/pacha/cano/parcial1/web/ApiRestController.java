package pacha.cano.parcial1.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pacha.cano.parcial1.modelo.Perfil;
import pacha.cano.parcial1.modelo.Publicacion;
import pacha.cano.parcial1.negocio.IPerfilNegocio;
import pacha.cano.parcial1.negocio.IPublicacionNegocio;
import pacha.cano.parcial1.negocio.exceptions.*;

@RestController
public class ApiRestController {
	
	@Autowired
	private IPerfilNegocio perfilNegocio;
	
	@Autowired
	private IPublicacionNegocio publicacionNegocio;
	
	@GetMapping(value = "/perfil")
	public ResponseEntity<List<Perfil>> listadoPerfiles () {
		try {
			return new ResponseEntity<List<Perfil>>(perfilNegocio.listado(), HttpStatus.OK);
		} catch (NegocioException e) {
			return new ResponseEntity<List<Perfil>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/perfil")
	public ResponseEntity<String> agregarPerfil(@RequestBody Perfil perfil) {
		try {
			Perfil respuesta = perfilNegocio.agregar(perfil);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", "/perfil/" + respuesta.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (NegocioException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (EncontradoException e) {
			return new ResponseEntity<String>(HttpStatus.FOUND);
		} catch (DuplicadoException e) {
			return new ResponseEntity<String>("Ya se encuentra un perfil con ese nombre", HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping(value="/perfil")
	public ResponseEntity<String> modificarPerfil(@RequestBody Perfil perfil) {
		try {
			perfilNegocio.modificar(perfil);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (NegocioException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NoEncontradoException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} catch (DuplicadoException e) {
			return new ResponseEntity<String>("Ya se encuentra un perfil con ese nombre de ususario", HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping(value="/perfil/{id}")
	public ResponseEntity<String> eliminarPerfil(@PathVariable("id") long id) {
		try {
			perfilNegocio.eliminar(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (NegocioException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NoEncontradoException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/perfilMasSeguidores")
	public ResponseEntity<Perfil> perfilMasSeguidores () {
		try {
			return new ResponseEntity<Perfil>(perfilNegocio.perfilMasSeguidores(), HttpStatus.OK);
		} catch (NegocioException e) {
			return new ResponseEntity<Perfil>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NoEncontradoException e) {
			return new ResponseEntity<Perfil>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	
	
	
	
	
	@GetMapping(value = "/publicacion")
	public ResponseEntity<List<Publicacion>> listadoPublicaciones () {
		try {
			return new ResponseEntity<List<Publicacion>>(publicacionNegocio.listado(), HttpStatus.OK);
		} catch (NegocioException e) {
			return new ResponseEntity<List<Publicacion>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/publicacion")
	public ResponseEntity<String> agregarPublicacion(@RequestBody Publicacion publicacion) {
		try {
			Publicacion respuesta = publicacionNegocio.agregar(publicacion);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", "/publicacion/" + respuesta.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (NegocioException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (EncontradoException e) {
			return new ResponseEntity<String>(HttpStatus.FOUND);
		} 
	}
	
	@PutMapping(value="/publicacion")
	public ResponseEntity<String> modificarPublicacion(@RequestBody Publicacion publicacion) {
		try {
			publicacionNegocio.modificar(publicacion);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (NegocioException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NoEncontradoException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value="/publicacion/{id}")
	public ResponseEntity<String> eliminarPublicacion(@PathVariable("id") long id) {
		try {
			publicacionNegocio.eliminar(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (NegocioException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NoEncontradoException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/publicacionMasLikes")
	public ResponseEntity<Publicacion> publicacionMasLikes () {
		try {
			return new ResponseEntity<Publicacion>(publicacionNegocio.publicacionMasLikes(), HttpStatus.OK);
		} catch (NegocioException e) {
			return new ResponseEntity<Publicacion>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NoEncontradoException e) {
			return new ResponseEntity<Publicacion>(HttpStatus.NOT_FOUND);
		}
	}
}
