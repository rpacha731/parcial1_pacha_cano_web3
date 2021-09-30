package pacha.cano.parcial1.modelo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "publicaciones")
public class Publicacion implements Serializable {

	private static final long serialVersionUID = 3236460038607236663L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 100, nullable = false)
	private String titulo;
	
	@Column(length = 200, nullable = false)
	private String descripcion;
	
	@Column(columnDefinition = "DATE", nullable = false)
	private Date fechaPublicacion;
	
	@Column(columnDefinition = "TIME", nullable = false)
	private Time horaPublicacion;

	@Column(nullable = false)
	private long cantidadLikes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public long getCantidadLikes() {
		return cantidadLikes;
	}

	public void setCantidadLikes(long cantidadLikes) {
		this.cantidadLikes = cantidadLikes;
	}

	public Time getHoraPublicacion() {
		return horaPublicacion;
	}

	public void setHoraPublicacion(Time horaPublicacion) {
		this.horaPublicacion = horaPublicacion;
	}
	
	@Override
	public String toString() {
		return "Publicacion [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fechaPublicacion="
				+ fechaPublicacion + ", cantidadLikes=" + cantidadLikes + "]";
	}

	
}
