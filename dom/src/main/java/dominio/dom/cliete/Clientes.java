package dominio.dom.cliete;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.ParameterLayout;



@DomainServiceLayout(menuOrder = "80")
@DomainService(repositoryFor = Cliente.class)
public class Clientes extends AbstractFactoryAndRepository
{
	@javax.inject.Inject 
    DomainObjectContainer container;
	
	public Cliente Cargar (@ParameterLayout (named="Nombre") final String nombre,
							@ParameterLayout (named="Telefono") final String telefono,
							@ParameterLayout (named="E-Mail") final String email,
							@ParameterLayout (named="Direccion") final String direccion)
	{
		final Cliente cliente = container.newTransientInstance(Cliente.class);
		cliente.setNombre(nombre);
		cliente.setTelefono(telefono);
		cliente.setDireccion(direccion);
		cliente.setEmail(email);
		
		container.persistIfNotAlready(cliente);
		
		
		return cliente;
	}
	
	public String Eliminar(@ParameterLayout(named="Nombre")final Cliente nom)
	{
		removeIfNotAlready(nom);		
		getContainer().flush();
        return "No se encontro equipo "+nom;
	}
	
	public List<Cliente> ListarTodo()
	{		
		return container.allInstances(Cliente.class);
	}
	
	
	

}
