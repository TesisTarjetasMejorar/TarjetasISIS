package dominio.dom.tarjeta;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

import dominio.dom.clasificacionSugerida.ClasificacionSugerida;
import dominio.dom.equipo.Equipo;
import dominio.dom.lugarObservacion.LugarObservacion;


@DomainServiceLayout(menuOrder = "40")
@DomainService(repositoryFor = TarjetaHSES.class)
public class TarjetasHSES extends AbstractFactoryAndRepository 
{
	@javax.inject.Inject 
    DomainObjectContainer container;
	

	public Tarjeta Cargar(@ParameterLayout (named="Numero de tarjeta") final int numTar,
	   						@ParameterLayout (named="Fecha Reporte") final LocalDate fechaRepo,
	   						@ParameterLayout(named="Fecha Carga") final LocalDate fechaCarga,
	   						@ParameterLayout(named="Lugar de Observacion") LugarObservacion lugarObs,
	   						@ParameterLayout(named="Linea de Negocio") final String lineaNeg,
	   						@ParameterLayout(named="Accion Realizada") final String accRealizada,
	   						@ParameterLayout(named="Clasificacion Sugerida") ClasificacionSugerida cs,
	   						@Parameter(optionality = Optionality.OPTIONAL) Equipo equipo,
	   						@ParameterLayout(named="Estado") final boolean estado ) 
	{
		final TarjetaHSES tHSES = container.newTransientInstance(TarjetaHSES.class);
		tHSES.setNumTarjetaTesco(String.valueOf(numTar));
		tHSES.setFechaReporte(fechaRepo);
		tHSES.setFechaCarga(fechaCarga);
		tHSES.setLugarObs(lugarObs);
		tHSES.setLineaNegocio(lineaNeg);
		tHSES.setAccionRealizada(accRealizada);
		tHSES.setClasifSug(cs);
		tHSES.setEquipo(equipo);
		tHSES.setEstado(estado);
		container.persistIfNotAlready(tHSES);

		return tHSES;

	}
	
	@Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
	public List<TarjetaHSES> Modificar(@ParameterLayout(named="Num")final String num)
	{	
		return container.allMatches(new QueryDefault<>(TarjetaHSES.class,"buscarPorNum","name", num));
	}
	
	public String Eliminar(@ParameterLayout(named="Num")final String num)
	{
		TarjetaHSES t = BuscarUna(num);
		removeIfNotAlready(t);		
        getContainer().flush();
        return "Tarjeta Eliminada";
	}
	
	public List<TarjetaHSES> ListarTodo()
	{		
		return container.allInstances(TarjetaHSES.class);
	}
	
	private TarjetaHSES BuscarUna (String num)
	{
		return container.firstMatch(
                new QueryDefault<>(TarjetaHSES.class, "buscarPorNum", "name", num ));
	}


	
	@Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
	public List<TarjetaHSES> listarPorFechas
			(
			@ParameterLayout(named="Fecha Inicial") final LocalDate rangoInicial,
			@ParameterLayout(named="Fecha Final") final LocalDate rangoFinal
			)
	{
		String ini = rangoInicial.toString();
		String fin = rangoFinal.toString();
		return container.allMatches(new QueryDefault<>(TarjetaHSES.class,"buscarPorFecha","rangoInicio", ini,"rangoFinal", fin));
		
	}
}
