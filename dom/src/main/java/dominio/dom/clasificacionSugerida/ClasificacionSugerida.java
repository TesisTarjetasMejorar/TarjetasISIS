package dominio.dom.clasificacionSugerida;

import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

@javax.jdo.annotations.Queries({
    @javax.jdo.annotations.Query(name = "buscarNombre", language = "JDOQL",value = "SELECT "+ "FROM dominio.dom.clasificacionSugerida "+ "WHERE nombre.indexOf(:name) >= 0 ")
})
@javax.jdo.annotations.Unique(name="ClasificacionSugerida_nombre_key", members = {"nombre"})

@DomainObject(objectType = "ClasificacionSugerida")
@PersistenceCapable
public class ClasificacionSugerida 
{
	private String nombre;
	private String descripcion;

	@javax.jdo.annotations.Column(allowsNull = "false",length = 40)
	public String getDescripcion() 
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}
	@javax.jdo.annotations.Column(allowsNull = "false",length = 40)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
