package dominio.dom.clasificacionSugerida;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.ParameterLayout;


@DomainServiceLayout(menuOrder = "50")
@DomainService(repositoryFor = ClasificacionSugerida.class)
public class ClasificacionSugeridaServ extends AbstractFactoryAndRepository 
{
	@javax.inject.Inject 
    DomainObjectContainer container;
	
	public ClasificacionSugerida Cargar(@ParameterLayout (named="Nombre") final String nombre,
				@ParameterLayout (named="Descripcion") final String descripcion)				
			{
				final ClasificacionSugerida clasi = container.newTransientInstance(ClasificacionSugerida.class);
				clasi.setNombre(nombre);
				clasi.setDescripcion(descripcion);
				container.persistIfNotAlready(clasi);

				return clasi;

			}
	
	public String Eliminar(@ParameterLayout(named="Nombre")final ClasificacionSugerida nom)
	{
		removeIfNotAlready(nom);		
		getContainer().flush();
        return "Clasificacion Sugerida eliminada";
	}
		
	public List<ClasificacionSugerida> ListarTodo()
	{		
		return container.allInstances(ClasificacionSugerida.class);
	}

}
