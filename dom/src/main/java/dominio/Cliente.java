package dominio;


import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.RenderType;
import org.apache.isis.applib.services.i18n.TranslatableString;

import servicios.Equipos;



@javax.jdo.annotations.Queries
({
    @javax.jdo.annotations.Query(name = "buscarNombre", language = "JDOQL",value = "SELECT "+ "FROM dominio.cliente "+ "WHERE nombre.indexOf(:name) >= 0 ")
})
@javax.jdo.annotations.Unique(name="Cliente_nombre_key", members = {"nombre"})


@DomainObject(objectType = "Cliente", bounded = true)
@PersistenceCapable
@DomainObjectLayout(named = "Cliente")
@MemberGroupLayout( columnSpans= {4,0,0,3})
public class Cliente implements Comparable<Cliente>, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String email;
	private String telefono;
	private String direccion;
	
	
	@Persistent(table="CLIENTE_EQUIPO")
	@Join(column="CLIENTE_ID_OID")
	@Element(column="EQUIPO_ID_EID")
	private List<Equipo> equipos;
	
	
	@MemberOrder (sequence = "1",name= "Cliente")
	@org.apache.isis.applib.annotation.Property(editing = Editing.ENABLED)
	@javax.jdo.annotations.Column(allowsNull = "false",length = 100)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@MemberOrder (sequence = "3",name= "Cliente")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@MemberOrder (sequence = "2",name= "Cliente")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@MemberOrder (sequence = "4",name= "Cliente")
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
	

	@Column(allowsNull = "true")
	@CollectionLayout(render = RenderType.EAGERLY,named= "Equipo")
	public List<Equipo> getEquipos() {		
		return equipos;
	}		
	
	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}
	
	
	@Override
	public int compareTo(Cliente o) {
		int salida = this.nombre.compareTo(o.getNombre());
		return salida;
	}
	
	
	@Programmatic
	public Cliente perteneceEquipo(final Equipo equipo) 
	{
		Cliente salida= null;
			for(Equipo eq : this.equipos){
				if(eq.getNombre() == equipo.getNombre().toString())
				{
					salida = this;
				}
			}
		
		return salida;
	}
	
	

		
	@javax.inject.Inject
	Equipos servEquipo;
    
}
