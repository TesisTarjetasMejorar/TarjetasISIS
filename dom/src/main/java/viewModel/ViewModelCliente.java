package viewModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.RenderType;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import dominio.Cliente;
import dominio.Equipo;

@MemberGroupLayout(columnSpans = { 4, 0, 0, 1 })
public class ViewModelCliente extends AbstractViewModel{

	public String title()
	{
	      return "Cliente";
	}	
	
	private String memento;
	private String nombre= "error";
	private String email= "error";
	private String telefono= "error";
	private String direccion= "error";
	private List<Equipo> equipos = new ArrayList<Equipo>();
	
	private Cliente clienteOriginal= new Cliente();
	
	
	@CollectionLayout(render = RenderType.EAGERLY)
 	public List<Equipo> getEquipos() {
		return equipos;
	}
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
	
	

	
	@Override
	public String viewModelMemento() {
		return memento;
	}
	@Override
	public void viewModelInit(String memento)
	{
		this.memento=memento;
		Memento mementoAux = mementoService.parse(memento);	
		Cliente auxCliente = new Cliente();
		auxCliente.setNombre(mementoAux.get("nombre", String.class));
		List<Cliente> clientes= container.allInstances(Cliente.class);
		clienteOriginal= new Cliente();
		clienteOriginal.setNombre(auxCliente.getNombre());
		for(Cliente c : clientes){
			if(c.compareTo(clienteOriginal)== 0)
				clienteOriginal=c;
		}
		asignarCamos(clienteOriginal);
	}
	
	
	private void asignarCamos(Cliente cl) {
//		cliente.setNombre(cl.getNombre());
//		cliente.setDireccion( cl.getDireccion());
//		cliente.setTelefono( cl.getTelefono());
//		cliente.setEmail( cl.getEmail());
//		equipo.setEquipos( cl.getEquipos());	
		
		
		this.setNombre(cl.getNombre());
		this.setDireccion( cl.getDireccion());
		this.setTelefono( cl.getTelefono());
		this.setEmail( cl.getEmail());
		this.equipos = cl.getEquipos();
	}
	
	

	
		
	@javax.inject.Inject
	DomainObjectContainer container;
	
	@javax.inject.Inject
	MementoService mementoService;

}
