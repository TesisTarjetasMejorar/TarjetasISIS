package dominio.dom.tarjeta;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.MemberOrder;
import org.joda.time.LocalDate;

import javax.jdo.annotations.InheritanceStrategy;

import dominio.dom.clasificacionSugerida.ClasificacionSugerida;
import dominio.dom.equipo.Equipo;
import dominio.dom.lugarObservacion.LugarObservacion;


@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Tarjeta 
{
	private String numTarjetaTesco;
	private LocalDate fechaReporte;
	private LocalDate fechaCarga;
	private String lineaNegocio;
	private ClasificacionSugerida clasifSug;
	private LugarObservacion lugarObs;
	private Equipo equipo;
	private boolean estado;
	
	
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
	@Column(allowsNull = "false")
	public LocalDate getFechaReporte() 
	{
		return fechaReporte;
	}
	public void setFechaReporte(LocalDate fechaReporte) 
	{
		this.fechaReporte = fechaReporte;
	}
	@MemberOrder (sequence = "3")
	@Column(allowsNull = "false")
	public LocalDate getFechaCarga() 
	{
		return fechaCarga;
	}
	public void setFechaCarga(LocalDate fechaCarga) 
	{
		this.fechaCarga = fechaCarga;
	}

	@MemberOrder (sequence = "4")
	@Column(allowsNull = "false")
	public LugarObservacion getLugarObs() 
	{
		return lugarObs;
	}
	public void setLugarObs(LugarObservacion lugarObs) 
	{
		this.lugarObs = lugarObs;
	}
	@MemberOrder (sequence = "5")
	@Column(allowsNull = "true")
	public String getLineaNegocio() 
	{
		return lineaNegocio;
	}
	public void setLineaNegocio(String lineaNegocio) 
	{
		this.lineaNegocio = lineaNegocio;
	}
	
	@MemberOrder (sequence = "5")
	@Column(allowsNull = "true")
	public ClasificacionSugerida getClasifSug() {
		return clasifSug;
	}
	public void setClasifSug(ClasificacionSugerida clasifSug) {
		this.clasifSug = clasifSug;
	}
	
	@MemberOrder (sequence = "6")
    @javax.jdo.annotations.Column(name = "equipoId", allowsNull = "true")
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	@MemberOrder (sequence = "7")
	@Column(allowsNull = "true")
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
		
}
