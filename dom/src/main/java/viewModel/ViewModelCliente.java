package viewModel;

import java.util.List;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import dominio.dom.Equipo;


@DomainServiceLayout(menuOrder = "8" ,menuBar = DomainServiceLayout.MenuBar.SECONDARY,named="Vistas Rapidas")
@MemberGroupLayout(columnSpans = { 4, 0, 0, 9 })
public class ViewModelCliente extends AbstractViewModel{

	private String nombre;
	private String email;
	private String telefono;
	private String direccion;
	private List<Equipo> equipos;
	private String memento;
	
	public List<Equipo> getEquipos() {
		return equipos;
	}
	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Override
	public String viewModelMemento() {
		return memento;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void viewModelInit(String memento)
	{
		this.memento=memento;
		
		Memento mementoAux = mementoService.parse(memento);
		
		this.nombre = mementoAux.get("nombre", String.class);
		this.direccion = mementoAux.get("direccion", String.class);
		this.email = mementoAux.get("email", String.class);
		this.telefono = mementoAux.get("telefono", String.class);
//		this.equipos = mementoAux.get("equipos", List.class);	
		
	}
	
	@javax.inject.Inject
	DomainObjectContainer container;
	
	@javax.inject.Inject
	MementoService mementoService;

}
