package dominio.dom.equipo;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;


@DomainServiceLayout(menuOrder = "70")
@DomainService(repositoryFor = Equipo.class)
public class Equipos extends AbstractFactoryAndRepository
{
	@javax.inject.Inject 
    DomainObjectContainer container;
	
	public String Cargar(@ParameterLayout (named="Nombre") final String nombre)			
		{
			final Equipo equi = container.newTransientInstance(Equipo.class);
			equi.setNombre(nombre);
			container.persistIfNotAlready(equi);

			return "Equipo cargado correctamente";
		}
	
	public String Eliminar(@ParameterLayout(named="Nombre")final String nom)
	{
		String mensaje="";
		Equipo e = BuscarUna(nom);
		if(e!=null)
		{
			removeIfNotAlready(e);		
			getContainer().flush();
			mensaje = "Equipo eliminado";
		}
		else
			mensaje = "No se encontro equipo "+nom;
		
        return mensaje;
	}
	
	private Equipo BuscarUna (String nom)
	{
		return container.firstMatch(
                new QueryDefault<>(Equipo.class, "buscarNombre", "name", nom ));
	}
	
	public List<Equipo> ListarTodo()
	{		
		return container.allInstances(Equipo.class);
	}

}
