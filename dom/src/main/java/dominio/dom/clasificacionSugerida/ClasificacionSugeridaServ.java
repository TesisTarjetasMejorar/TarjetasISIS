package dominio.dom.clasificacionSugerida;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

import dominio.dom.tarjeta.Tarjeta;
import dominio.dom.tarjeta.TarjetaHSES;


@DomainServiceLayout(menuOrder = "60")
@DomainService(repositoryFor = TarjetaHSES.class)
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
	
	public String Eliminar(@ParameterLayout(named="Nombre")final String nom)
	{
		ClasificacionSugerida cs = BuscarUna(nom);
		removeIfNotAlready(cs);		
        getContainer().flush();
        return "Clasificacion Eliminada";
	}
	
	private ClasificacionSugerida BuscarUna (String nom)
	{
		return container.firstMatch(
                new QueryDefault<>(ClasificacionSugerida.class, "buscarNombre", "name", nom ));
	}
	
	public List<ClasificacionSugerida> ListarTodo()
	{		
		return container.allInstances(ClasificacionSugerida.class);
	}

}
