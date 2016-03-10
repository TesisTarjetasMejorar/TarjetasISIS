package servicios;


import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.InvokeOn;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.services.memento.MementoService;
import reporte.Reporte;
import servicios.validacion.RegexValidation;
import viewModel.ViewModelCliente;
import dominio.Cliente;
import dominio.Equipo;



@DomainServiceLayout(menuOrder = "80")
@DomainService(repositoryFor = Cliente.class)
public class Clientes extends AbstractFactoryAndRepository
{

	public ViewModelCliente BuscarCliente()
	{			 	
		return container.injectServicesInto(new ViewModelCliente());
	}

	public ViewModelCliente Cargar (
			@ParameterLayout (named="Nombre")final String nombre,
			
			@ParameterLayout (named="Telefono")
			@Parameter
			(
					regexPattern = RegexValidation.ValidaTel.NUMEROTEL,
					regexPatternReplacement= "patron 000-0000000 , 3 caracteres y 7 numeros"
					
			)final String telefono,
			@ParameterLayout (named="E-Mail")
			@Parameter
			(
					regexPattern = RegexValidation.ValidaMail.EMAIL,
					regexPatternReplacement= "xxx@xxxxx.xxx"
			) final String email,
			@ParameterLayout (named="Direccion") final String direccion)
							
	{
		final Cliente cliente = container.newTransientInstance(Cliente.class);
		cliente.setNombre(nombre);
		cliente.setTelefono(telefono);
		cliente.setDireccion(direccion);
		cliente.setEmail(email);
		container.persistIfNotAlready(cliente);
		
		return container.injectServicesInto(new ViewModelCliente());
	}

	public String EliminarCliente(@ParameterLayout(named="Nombre")final Cliente nom)
	{
		String salida ="";
		if(nom.getEquipos().isEmpty()){
			removeIfNotAlready(nom);	
			salida= "Cliente eliminado con exito";
		}else{
			for(Equipo eq: nom.getEquipos()){
				salida= equipos.Eliminar(eq);
			}
		}
		if(salida==""){
			removeIfNotAlready(nom);		
			getContainer().flush();
		}else
			salida= "No se a podido eliminar el cliente, ya que posee un equipo con una tarjeta asociada";
	
        return salida;
	}
	
	public List<Cliente> ListarTodo()
	{		
		return container.allInstances(Cliente.class);
	}	
		
	
	@Action(invokeOn = InvokeOn.OBJECT_AND_COLLECTION)
	public List<Equipo> ModificarEquipos(final Cliente c){
		return c.getEquipos();
	}
	
	
	public Cliente CargarEquipo(@ParameterLayout(named="Nombre") String nombre, Cliente c){
		Equipo eq = new Equipo();
		eq.setNombre(nombre);
		List<Equipo> equiposCliente = c.getEquipos();
		if (equiposCliente == null){
			equiposCliente = new ArrayList<Equipo>();
			c.setEquipos(equiposCliente);
		}
		equiposCliente.add(eq);
		return c;
	}
	
	
	public boolean hideCargarEquipo(@ParameterLayout(named="Nombre") String nombre, Cliente c){
		boolean salida = false;
		if(c==null){
			salida= true;
		}		
		return salida;
	}
	
	
	
	
	public boolean hideModificarEquipos(final Cliente c)
	{
		if (c != null)
			return false;
		return true;
	}


	public String reporteClientes() throws JRException {
		List<Cliente> datos = container.allInstances(Cliente.class);

		String fecha = "";
		String nombre = "Reporte de Clientes "+fecha;
		
		
		Reporte.generarReporteCliente("reporteClientes.jrxml", datos,nombre);		
//		Reporte.generarReporteCliente("reportes/reporteClientes.jrxml", datos,nombre);		
		
		return "Se genero reporte de clientes";
	}
	
	
	
//	public boolean hideCargar(@ParameterLayout (named="Nombre") final String nombre, final Cliente c)			
//{
//	if (c != null)
//		return false;
//	return true;
//}

	@Programmatic
	public void clonar(final Cliente a, Cliente b)
	{
		if(b==null || a!=null)
		{
			b = new Cliente();
			b.setNombre(a.getNombre().toString());
			b.setEmail(a.getEmail().toString());
			b.setTelefono(a.getTelefono().toString());
			b.setDireccion(a.getDireccion().toString());
			b.setEquipos(a.getEquipos());
		}
	}
	
	@Programmatic
	public Cliente perteneceEquipo(final Cliente cliente, final Equipo equipo) {
		Cliente salida= null;
			for(Equipo eq : cliente.getEquipos() )
			{
				if(eq.getNombre() == equipo.getNombre())
				{
					salida = cliente;
				}
			}
		
		return salida;
	}
	
	

	
	
	@javax.inject.Inject 
    DomainObjectContainer container;
	
	@javax.inject.Inject
	MementoService mementoService;
	
	@javax.inject.Inject
	Equipos equipos;
}
