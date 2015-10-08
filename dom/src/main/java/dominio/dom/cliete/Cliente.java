package dominio.dom.cliete;

import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.services.i18n.TranslatableString;



@javax.jdo.annotations.Queries
({
    @javax.jdo.annotations.Query(name = "buscarNombre", language = "JDOQL",value = "SELECT "+ "FROM dominio.dom.cliente "+ "WHERE nombre.indexOf(:name) >= 0 ")
})
@javax.jdo.annotations.Unique(name="Cliente_nombre_key", members = {"nombre"})


@DomainObject(objectType = "Cliente", bounded = true)
@PersistenceCapable
public class Cliente {

	
	private String nombre;
	private String email;
	private String telefono;
	private String direccion;
	
	
	@org.apache.isis.applib.annotation.Property(editing = Editing.ENABLED)
	@javax.jdo.annotations.Column(allowsNull = "false",length = 100)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public TranslatableString title()
	{
		return TranslatableString.tr("{name}", "name", getNombre());
	}
	
	
	
}