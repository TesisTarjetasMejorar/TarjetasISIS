package dominio.dom.cliete;


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
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.eclipse.jdt.internal.compiler.ast.Clinit;

import dominio.dom.equipo.Equipo;
import dominio.dom.regex.RegexValidation;
import dominio.dom.reporte.Reporte;
import dominio.dom.tarjeta.Tarjeta;



@DomainServiceLayout(menuOrder = "80")
@DomainService(repositoryFor = Cliente.class)
public class Clientes extends AbstractFactoryAndRepository
{
	@javax.inject.Inject 
    DomainObjectContainer container;
	
	public Cliente Cargar (@ParameterLayout (named="Nombre") @Parameter(regexPattern = RegexValidation.ValidaNombres.NOMBRE )final String nombre,
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
		
		
		return cliente;
	}
	
	public List<Cliente> Eliminar(@ParameterLayout(named="Nombre")final Cliente nom)
	{
		removeIfNotAlready(nom);		
		getContainer().flush();
        return container.allInstances(Cliente.class);
	}
	
	public List<Cliente> ListarTodo()
	{		
		return container.allInstances(Cliente.class);
	}	
	
	
	public List<Equipo> QuitarEquipo(final Cliente c){
		return c.getEquipos();
	}
	public boolean hideQuitarEquipo(final Cliente c){
		if(c== null)
			return true;
		return false;
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


public boolean hideCargarEquipo(@ParameterLayout (named="Nombre") final String nombre, final Cliente c)			
{
	if (c != null)
		return false;
	return true;
}
	
	public String reporteClientes() throws JRException {
		List<Cliente> datos = container.allInstances(Cliente.class);

		String fecha = "";
		String nombre = "Reporte de Clientes "+fecha;
		
		Reporte.generarReporteCliente("reportes/reporteClientes.jrxml", datos,nombre);		
		
		return "Se genero reporte de clientes";
	}
	
	
	
	@javax.inject.Inject
	MementoService mementoService;


}
