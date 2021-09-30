package pacha.cano.parcial1.modelo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "perfiles")
public class Perfil implements Serializable {

	private static final long serialVersionUID = 5480775831454715185L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 100, unique = true, nullable = false)
	private String nombreUsuario;
	
	@Column(length = 100, nullable = false)
	private String direccion;
	
	@Column(columnDefinition = "DATE", nullable = false)
	private Date fechaNacimiento;
	
	@Column(nullable = false)
	private long cantidadSeguidores;
	
	@OneToOne(cascade =  CascadeType.ALL)
	@JoinColumn(name = "id_publicacion")
	private Publicacion publicacion;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public long getCantidadSeguidores() {
		return cantidadSeguidores;
	}

	public void setCantidadSeguidores(long cantidadSeguidores) {
		this.cantidadSeguidores = cantidadSeguidores;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}
	
}
