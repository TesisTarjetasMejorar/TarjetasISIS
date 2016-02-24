package servicios;


import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
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

	public ViewModelCliente Cargar (@ParameterLayout (named="Nombre") @Parameter(regexPattern = RegexValidation.ValidaNombres.NOMBRE )final String nombre,
							@ParameterLayout (named="Telefono")@Parameter(regexPattern = RegexValidation.ValidaTel.NUMEROTEL)final String telefono,
							@ParameterLayout (named="E-Mail")@Parameter(regexPattern = RegexValidation.ValidaMail.EMAIL) final String email,
							@ParameterLayout (named="Direccion") @Parameter(regexPattern = RegexValidation.ValidaDireccion.DIRECCION )final String direccion)
							
	{
		final Cliente cliente = container.newTransientInstance(Cliente.class);
		cliente.setNombre(nombre);
		cliente.setTelefono(telefono);
		cliente.setDireccion(direccion);
		cliente.setEmail(email);
		container.persistIfNotAlready(cliente);
		
		
		
		return container.injectServicesInto(new ViewModelCliente());
	}

	public String Eliminar(@ParameterLayout(named="Nombre")final Cliente nom)
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
		
	public List<Equipo> QuitarEquipo(final Cliente c){
		return c.getEquipos();
	}

	public Cliente CargarEquipo(@ParameterLayout (named="Nombre") @Parameter(regexPattern = RegexValidation.ValidaPalabra.PALABRAINICIALMAYUSCULA )final String nombre, final Cliente c)			
	{
		final Equipo equi = container.newTransientInstance(Equipo.class);
		equi.setNombre(nombre);
		if (c.getEquipos()==null){
			c.setEquipos(new ArrayList<Equipo>());
		}
		c.getEquipos().add(equi);
//		container.persistIfNotAlready(c);
		return c;
	}
	public String reporteClientes() throws JRException {
		List<Cliente> datos = container.allInstances(Cliente.class);

		String fecha = "";
		String nombre = "Reporte de Clientes "+fecha;
		
		Reporte.generarReporteCliente("reportes/reporteClientes.jrxml", datos,nombre);		
		
		return "Se genero reporte de clientes";
	}
	
	
	
	public boolean hideQuitarEquipo(final Cliente c){
		if(c== null)
			return true;
		return false;
	}
	public boolean hideCargarEquipo(@ParameterLayout (named="Nombre") final String nombre, final Cliente c)			
{
	if (c != null)
		return false;
	return true;
}

	@javax.inject.Inject 
    DomainObjectContainer container;
	
	@javax.inject.Inject
	MementoService mementoService;
	
	@javax.inject.Inject
	Equipos equipos;
}
