package dominio.dom.lugarObservacion;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;


@DomainServiceLayout(menuOrder = "60")
@DomainService(repositoryFor = LugaresObservaciones.class)
public class LugaresObservaciones extends AbstractFactoryAndRepository
{
	@javax.inject.Inject 
    DomainObjectContainer container;
	
	public LugarObservacion Cargar(@ParameterLayout (named="Nombre") final String nombre,
			@ParameterLayout (named="Descripcion") final String descripcion)				
		{
			final LugarObservacion lObs = container.newTransientInstance(LugarObservacion.class);
			lObs.setNombre(nombre);
			lObs.setDescripcion(descripcion);
			container.persistIfNotAlready(lObs);

			return lObs;

		}
	public String Eliminar(@ParameterLayout(named="Nombre")final String nom)
	{
		LugarObservacion lo = BuscarUna(nom);
		removeIfNotAlready(lo);		
        getContainer().flush();
        return "Lugar Eliminada";
	}
	private LugarObservacion BuscarUna (String nom)
	{
		return container.firstMatch(
                new QueryDefault<>(LugarObservacion.class, "buscarNombre", "name", nom ));
	}
	public List<LugarObservacion> ListarTodo()
	{		
		return container.allInstances(LugarObservacion.class);
	}

}
