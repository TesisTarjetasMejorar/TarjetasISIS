package dominio.dom.tarjeta;


//import javax.inject.Inject;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
//import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;

@javax.jdo.annotations.Queries({
	    @javax.jdo.annotations.Query(name = "buscarPorNum", language = "JDOQL",value = "SELECT "+ "FROM dominio.dom.TarjetaHSES "+ "WHERE numTarjetaTesco.indexOf(:name) >= 0 "),
	    @javax.jdo.annotations.Query(name = "buscarPorFecha", language = "JDOQL",value = "SELECT "+"FROM dominio.dom.TarjetaHSES "+"WHERE fechaCarga >= :rangoInicio && fechaCarga <= :rangoFinal")
	    })
@javax.jdo.annotations.Unique(name="TarjetaHSES_numTarjetaTesco_key", members = {"numTarjetaTesco"})

@DomainObject(objectType = "HSES")
@PersistenceCapable
public class TarjetaHSES extends Tarjeta 
{
	private String AccionRealizada;


	@MemberOrder (sequence = "7")
	@javax.jdo.annotations.Column(allowsNull = "false",length = 40)
	public String getAccionRealizada() 
	{
		return AccionRealizada;
	}


	public void setAccionRealizada(String accionRealizada) 
	{
		AccionRealizada = accionRealizada;
	}

}
