package dominio;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainObject;


@DomainObject(objectType = "ClienteModelo")
public class ClienteModelo
{	
	private String nombre= "error";
	private String email= "error";
	private String telefono= "error";
	private String direccion= "error";
	
	public String getNombre() {
		return nombre;
	}
	public String getEmail() {
		return email;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	@javax.inject.Inject
	DomainObjectContainer container;
}
