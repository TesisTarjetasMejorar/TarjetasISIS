package dominio.dom.tarjeta;


//import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
//import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;

@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "listarSQAbierto", language = "JDOQL",value = "SELECT "+ "FROM dominio.dom.TarjetaSQ "+"WHERE estado == true"),
	@javax.jdo.annotations.Query(name = "listarSQCerrado", language = "JDOQL",value = "SELECT "+ "FROM dominio.dom.TarjetaSQ "+"WHERE estado == false"),
    @javax.jdo.annotations.Query(name = "buscarPorNum", language = "JDOQL",value = "SELECT "+ "FROM dominio.dom.TarjetaSQ "+ "WHERE numTarjetaTesco.indexOf(:name) >= 0"),
    @javax.jdo.annotations.Query(name = "buscarPorFecha", language = "JDOQL",value = "SELECT "+"FROM dominio.dom.TarjetaSQ "+"WHERE fechaCarga >= :rangoInicio && fechaCarga <= :rangoFinal")
    })


@javax.jdo.annotations.Unique(name="TarjetaSQ_numTarjetaTesco_key", members = {"numTarjetaTesco"})

@DomainObject(objectType = "SQ")
@PersistenceCapable
public class TarjetaSQ extends Tarjeta
{
	
		
	
	private String decisionTomada;

	@MemberOrder (sequence = "6")
	@Column(allowsNull = "false",length = 40)
	public String getDecicionTomada() 
	{
		return decisionTomada;
	}

	public void setDecicionTomada(String loquesea) 
	{
		this.decisionTomada = loquesea;
	}
	
	
	/*
	@Inject
	private TarjetasSQ tarjetasSQ;
	@Inject
	private DomainObjectContainer container;
*/

}
