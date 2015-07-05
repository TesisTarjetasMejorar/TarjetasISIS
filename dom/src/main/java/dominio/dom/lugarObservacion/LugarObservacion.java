package dominio.dom.lugarObservacion;

import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

@javax.jdo.annotations.Queries({
    @javax.jdo.annotations.Query(name = "buscarNombre", language = "JDOQL",value = "SELECT "+ "FROM dominio.dom.lugarObservacion "+ "WHERE nombre.indexOf(:name) >= 0 ")
})
@javax.jdo.annotations.Unique(name="LugarObservacion_nombre_key", members = {"nombre"})

@DomainObject(objectType = "LugarObservacion")
@PersistenceCapable
public class LugarObservacion 
{
	private String nombre;
	private String descripcion;
	
	@javax.jdo.annotations.Column(allowsNull = "false",length = 40)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@javax.jdo.annotations.Column(allowsNull = "false",length = 40)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
