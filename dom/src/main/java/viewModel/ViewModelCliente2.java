package viewModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import dominio.Cliente;
import dominio.Equipo;
import dominio.EquiposModelo;



@MemberGroupLayout(columnSpans = { 4, 0, 0, 1 })
@SuppressWarnings("unused")
public class ViewModelCliente2 extends AbstractViewModel{

	public String title()
	{
	      return "Cliente";
	}
	
	private String memento;
	private Cliente clienteOriginal;
	private ClienteModelo2 cliente = new ClienteModelo2();
	private EquipoModelo2 equipos = new EquipoModelo2();
	
	
	
	public ClienteModelo2 getCliente() {
		return cliente;
	}
	public void setCliente(ClienteModelo2 cliente) {
		this.cliente = cliente;
	}
	public EquipoModelo2 getEquipos() {
		return equipos;
	}
	public void setEquipos(EquipoModelo2 equipos) {
		this.equipos = equipos;
	}

	
	protected class ClienteModelo2
	{	
		private String nombre= "error";
		private String email= "error";
		private String telefono= "error";
		private String direccion= "error";		
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
	
	}
	protected class EquipoModelo2{		
		List<Equipo> equipos;
		
		public EquipoModelo2() {
			this.equipos= new ArrayList<Equipo>();
		}
		
		public List<Equipo> getEquipos() {
			return equipos;
		}	
		
		public void agregarEquipo(String nombre)
		{
			Equipo aux = container.newTransientInstance(Equipo.class);
			aux.setNombre(nombre);
			container.persist(aux);
			clienteOriginal.getEquipos().add(aux);	
			refrescarDatos(clienteOriginal);
			
		}	
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
		for(Cliente c : clientes){
			if(c.compareTo(auxCliente)== 0)
				clienteOriginal=c;
		}
		refrescarDatos(clienteOriginal);
	}
	
	
	private void refrescarDatos(Cliente cl) {
		cliente.setNombre(cl.getNombre());
		cliente.setDireccion( cl.getDireccion());
		cliente.setTelefono( cl.getTelefono());
		cliente.setEmail( cl.getEmail());
		equipos.equipos= cl.getEquipos();		
	}	
	


		
	@javax.inject.Inject
	DomainObjectContainer container;
	
	@javax.inject.Inject
	MementoService mementoService;

}
