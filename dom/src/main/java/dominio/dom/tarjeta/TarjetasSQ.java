package dominio.dom.tarjeta;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;




@DomainServiceLayout(menuOrder = "30")
@DomainService(repositoryFor = TarjetaSQ.class)
public class TarjetasSQ extends AbstractFactoryAndRepository 
{
	@javax.inject.Inject 
    DomainObjectContainer container;
	
	public TarjetaSQ Cargar(@ParameterLayout (named="Numero de tarjeta") final int numTar,
						@ParameterLayout (named="Fecha Reporte") final LocalDate fechaRepo,
						@ParameterLayout(named="Fecha Carga") final LocalDate fechaCarga,
						@ParameterLayout(named="Lugar de Observacion") final String lugarObs,
						@ParameterLayout(named="Linea de Negocio") final String lineaNeg,
						@ParameterLayout(named="Decicion Tomada") final String loquesea) 
	{
		final TarjetaSQ tSQ = container.newTransientInstance(TarjetaSQ.class);
		tSQ.setNumTarjetaTesco(String.valueOf(numTar));
		tSQ.setFechaReporte(fechaRepo.toString());
		tSQ.setFechaCarga(fechaCarga.toString());
		tSQ.setLugarObs(lugarObs);
		tSQ.setLineaNegocio(lineaNeg);
        tSQ.setDecicionTomada(loquesea);
        container.persistIfNotAlready(tSQ);
		
		return tSQ;
		
	}
	
	@Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
	public List<TarjetaSQ> Modificar(@ParameterLayout(named="Num")final String num)
	{	
		return container.allMatches(new QueryDefault<>(TarjetaSQ.class,"buscarPorNum","name", num));
	}
	
	public String Eliminar(@ParameterLayout(named="Num")final String num)
	{
		TarjetaSQ t = BuscarUna(num);
		removeIfNotAlready(t);		
        getContainer().flush();
        return "Tarjeta Eliminada";
	}

	public List<TarjetaSQ> ListarTodo()
	{		
		return container.allInstances(TarjetaSQ.class);
	}
	
	private TarjetaSQ BuscarUna (String num)
	{
		return container.firstMatch(
                new QueryDefault<>(TarjetaSQ.class, "buscarPorNum", "name", num ));
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
