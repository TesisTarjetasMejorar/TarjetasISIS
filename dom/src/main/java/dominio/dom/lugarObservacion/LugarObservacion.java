package dominio.dom.lugarObservacion;

import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.services.i18n.TranslatableString;

@javax.jdo.annotations.Queries({
    @javax.jdo.annotations.Query(name = "buscarNombre", language = "JDOQL",value = "SELECT "+ "FROM dominio.dom.lugarObservacion "+ "WHERE nombre.indexOf(:name) >= 0 ")
})
@javax.jdo.annotations.Unique(name="LugarObservacion_nombre_key", members = {"nombre"})

@DomainObject(objectType = "LugarObservacion", bounded = true)
@PersistenceCapable
public class LugarObservacion
{
	private String nombre;
	private String descripcion;
	
    @javax.jdo.annotations.Column(allowsNull = "false", length = 40  )
    @Title
	@MemberOrder (sequence = "1")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@MemberOrder (sequence = "2")
	@javax.jdo.annotations.Column(allowsNull = "false",length = 40)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	   public TranslatableString title() {
	        return TranslatableString.tr("{name}", "name", getNombre());
	    }

}
