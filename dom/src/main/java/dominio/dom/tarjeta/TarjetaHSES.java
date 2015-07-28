package dominio.dom.tarjeta;


//import javax.inject.Inject;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
//import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.i18n.TranslatableString;

@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "listarHSESResueltas", language = "JDOQL",value = "SELECT "+ "FROM dominio.dom.TarjetaHSES "+"WHERE resuelto == :resuelto"),
		@javax.jdo.annotations.Query(name = "listarHSESReportado", language = "JDOQL",value = "SELECT "+ "FROM dominio.dom.TarjetaHSES "+"WHERE reportado == :reportado"),
		@javax.jdo.annotations.Query(name = "listarHSESEstado", language = "JDOQL",value = "SELECT "+ "FROM dominio.dom.TarjetaHSES "+"WHERE estado == :estado"),
	    @javax.jdo.annotations.Query(name = "buscarPorNum", language = "JDOQL",value = "SELECT "+ "FROM dominio.dom.TarjetaHSES "+ "WHERE numTarjetaTesco.indexOf(:name) >= 0"),
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

	public TranslatableString title()
	{
		return TranslatableString.tr("{name}", "name", "TarjetaHSES");
	}
}
