package dominio.dom.consulta;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;

import dominio.dom.tarjeta.Tarjeta;
import dominio.dom.tarjeta.TarjetaHSES;
import dominio.dom.tarjeta.TarjetaSQ;

@DomainServiceLayout(menuOrder = "90")
@DomainService(repositoryFor = Tarjeta.class)
public class Consulta
{
	@javax.inject.Inject 
    DomainObjectContainer container;
	
	public List<Tarjeta> listarTarjetasPorEstado(@ParameterLayout (named="Estado")final boolean estado)
	{
		List<Tarjeta> salida = new ArrayList<Tarjeta>();
	
			salida.addAll(container.allMatches(new QueryDefault<>(TarjetaHSES.class,"listarHSESEstado","estado",estado )));
			salida.addAll(container.allMatches(new QueryDefault<>(TarjetaSQ.class,  "listarSQEstado","estado",estado )));
	
		return salida;
	}
	
	public List<Tarjeta> listarTarjetasResueltas(@ParameterLayout (named="Resueltas")final boolean resuelto)
	{
		List<Tarjeta> salida = new ArrayList<Tarjeta>();
		salida.addAll(container.allMatches(new QueryDefault<>(TarjetaHSES.class,"listarHSESResueltas","resuelto",resuelto)));
		salida.addAll(container.allMatches(new QueryDefault<>(TarjetaSQ.class,"listarSQResueltas","resuelto",resuelto)));
	
		return salida;
	}
	
	public List<Tarjeta> listarTarjetasReportadas(@ParameterLayout (named="Reportadas a Supervisor")final boolean reportado)
	{
		List<Tarjeta> salida = new ArrayList<Tarjeta>();
		salida.addAll(container.allMatches(new QueryDefault<>(TarjetaHSES.class,"listarHSESReportado","reportado",reportado)));
		salida.addAll(container.allMatches(new QueryDefault<>(TarjetaSQ.class,"listarSQReportado","reportado",reportado)));
	
		return salida;
	}

}
