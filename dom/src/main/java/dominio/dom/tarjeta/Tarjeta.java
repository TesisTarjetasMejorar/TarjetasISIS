package dominio.dom.tarjeta;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.MemberOrder;


@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Tarjeta 
{
	private String numTarjetaTesco;
	private String fechaReporte;
	private String fechaCarga;
	private String lugarObs;
	private String lineaNegocio;
	
	@MemberOrder (sequence = "1")
	@Column(allowsNull = "false",length = 40)
	public String getNumTarjetaTesco() 
	{
		return numTarjetaTesco;
	}
	public void setNumTarjetaTesco(String numTarjetaTesco) 
	{
		this.numTarjetaTesco = numTarjetaTesco;
	}
	
	@MemberOrder (sequence = "2")
	@Column(allowsNull = "false",length = 40)
	public String getFechaReporte() 
	{
		return fechaReporte;
	}
	public void setFechaReporte(String fechaReporte) 
	{
		this.fechaReporte = fechaReporte;
	}
	@MemberOrder (sequence = "3")
	@Column(allowsNull = "false",length = 40)
	public String getFechaCarga() 
	{
		return fechaCarga;
	}
	public void setFechaCarga(String fechaCarga) 
	{
		this.fechaCarga = fechaCarga;
	}

	@MemberOrder (sequence = "4")
	@Column(allowsNull = "false",length = 40)
	public String getLugarObs() 
	{
		return lugarObs;
	}
	public void setLugarObs(String lugarObs) 
	{
		this.lugarObs = lugarObs;
	}
	@MemberOrder (sequence = "5")
	@Column(allowsNull = "false",length = 40)//pera, la bd donde la tenes
	public String getLineaNegocio() 
	{
		return lineaNegocio;
	}
	public void setLineaNegocio(String lineaNegocio) 
	{
		this.lineaNegocio = lineaNegocio;
	}
	
		
}
