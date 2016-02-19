package dominio;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainObject;



@DomainObject(objectType = "EquipoModelo")
public class EquiposModelo 
{
	
	List<Equipo> equipos = new ArrayList<Equipo>();

	
	public List<Equipo> getEquipos() {
		return equipos;
	}	
	
	
	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}


	public void agregarEquipo(Cliente cliente, String nombre)
	{
		Equipo aux = container.newTransientInstance(Equipo.class);
		aux.setNombre(nombre);
		container.persist(aux);
		cliente.getEquipos().add(aux);
		
	}
	

	
	
	
	@javax.inject.Inject
	DomainObjectContainer container;
}
