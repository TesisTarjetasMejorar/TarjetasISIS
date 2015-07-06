package dominio.dom.equipo;

import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

@javax.jdo.annotations.Queries({
    @javax.jdo.annotations.Query(name = "buscarNombre", language = "JDOQL",value = "SELECT "+ "FROM dominio.dom.equipo "+ "WHERE nombre.indexOf(:name) >= 0 ")
})
@javax.jdo.annotations.Unique(name="Equipo_nombre_key", members = {"nombre"})

@DomainObject(objectType = "Equipo")
@PersistenceCapable
public class Equipo 
{
	private String nombre;

	@javax.jdo.annotations.Column(allowsNull = "false",length = 40)
	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

}
