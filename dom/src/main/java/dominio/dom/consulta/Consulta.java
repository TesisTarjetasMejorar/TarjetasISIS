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
		if(estado)
		{
			salida.addAll(container.allMatches(new QueryDefault<>(TarjetaHSES.class,"listarHSESAbierto")));
			salida.addAll(container.allMatches(new QueryDefault<>(TarjetaSQ.class,"listarSQAbierto")));
		}
		else{
			salida.addAll(container.allMatches(new QueryDefault<>(TarjetaHSES.class,"listarHSESCerrado")));
			salida.addAll(container.allMatches(new QueryDefault<>(TarjetaSQ.class,"listarSQCerrado")));
		}
		return salida;
	}

}
