package dominio.dom.tarjeta;

import java.util.Date;
import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

@DomainServiceLayout(menuOrder = "40")
@DomainService(repositoryFor = TarjetaHSES.class)
public class TarjetasHSES extends AbstractFactoryAndRepository 
{
	@javax.inject.Inject 
    DomainObjectContainer container;
	
	public Tarjeta Cargar(@ParameterLayout (named="Numero de tarjeta") final int numTar,
	   						@ParameterLayout (named="Fecha Reporte") final LocalDate fechaRepo,
	   						@ParameterLayout(named="Fecha Carga") final LocalDate fechaCarga,
	   						@ParameterLayout(named="Lugar de Observacion") final String lugarObs,
	   						@ParameterLayout(named="Linea de Negocio") final String lineaNeg,
	   						@ParameterLayout(named="Accion Realizada") final String accRealizada) 
	{
		final TarjetaHSES tHSES = container.newTransientInstance(TarjetaHSES.class);
		tHSES.setNumTarjetaTesco(String.valueOf(numTar));
		tHSES.setFechaReporte(fechaRepo.toString());
		tHSES.setFechaCarga(fechaCarga.toString());
		tHSES.setLugarObs(lugarObs);
		tHSES.setLineaNegocio(lineaNeg);
		tHSES.setAccionRealizada(accRealizada);
		container.persistIfNotAlready(tHSES);

		return tHSES;

	}
	
	@Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
	public List<TarjetaHSES> Modificar(@ParameterLayout(named="Num")final String num)
	{	
		return container.allMatches(new QueryDefault<>(TarjetaHSES.class,"buscarPorNum","name", num));
	}
	
	public Tarjeta Eliminar()
	{
		return null;
	}
	
	public List<TarjetaHSES> ListarTodo()
	{		
		return container.allInstances(TarjetaHSES.class);
	}
	

}
