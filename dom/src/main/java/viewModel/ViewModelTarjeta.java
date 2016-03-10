package viewModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.RenderType;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;

import servicios.Clientes;
import servicios.Tarjetas;
import servicios.utilidades.Evento;
import dominio.ClasificacionSugerida;
import dominio.Cliente;
import dominio.Equipo;
import dominio.LugarObservacion;
import dominio.Tarjeta;

@MemberGroupLayout 
( 
		columnSpans={3,3,3,2}
)
public class ViewModelTarjeta extends AbstractViewModel
{	
	


	private Cliente cliente= null;
		
	//----------------------------	Cliente-----------------------------------
		
		
		@MemberOrder (sequence = "1", name = "Cliente")
		@Title(sequence="1", append="Cliente")
		public String getNombre() {
			return cliente.getNombre();
		}
		@MemberOrder (sequence = "2", name = "Cliente")
		public String getEmail() {
			return cliente.getEmail();
		}
		@MemberOrder (sequence = "3", name = "Cliente")
		public String getTelefono() {
			return cliente.getTelefono();
		}
		@MemberOrder (sequence = "4", name = "Cliente")
		public String getDireccion() {
			return cliente.getDireccion();
		}

		@CollectionLayout(render = RenderType.EAGERLY)
		@MemberOrder (sequence = "5", name = "Cliente")
		public List<Equipo> getEquipos() {
			return new ArrayList<Equipo>();
		}	
				
		//----------------------------	Tarjeta-----------------------------------
		
		
		
		private Tarjeta tarjeta= null;
		
		@MemberOrder (sequence = "1", name = "Tarjeta")
		public String getNumTarjetaTesco() {
			return tarjeta.getNumTarjetaTesco();
		}
		@MemberOrder (sequence = "2", name = "Tarjeta")
		public LocalDate getFechaReporte() {
			return tarjeta.getFechaReporte();
		}
		@MemberOrder (sequence = "3", name = "Tarjeta")
		public LocalDate getFechaCarga() {
			return tarjeta.getFechaCarga();
		}
		@MemberOrder (sequence = "4", name = "Tarjeta")
		public LugarObservacion getLugarObs() {
			return tarjeta.getLugarObs();
		}
		@MemberOrder (sequence = "5", name = "Tarjeta")
		public String getLineaNegocio() {
			return tarjeta.getLineaNegocio();
		}
		@MemberOrder (sequence = "6", name = "Tarjeta")
		public ClasificacionSugerida getClasifSug() {
			return tarjeta.getClasifSug();
		}
		@MemberOrder (sequence = "7", name = "Tarjeta")
		public Equipo getEquipo() {
			return tarjeta.getEquipo();
		}
		@MemberOrder (sequence = "8", name = "Tarjeta")
		public Evento getEvento() {
			return tarjeta.getEvento();
		}
		@MemberOrder (sequence = "9", name = "Tarjeta")
		public String getDecisionTomada() {
			return tarjeta.getDecisionTomada();
		}
		@MemberOrder (sequence = "10", name = "Tarjeta")
		public boolean isEstado() {
			return tarjeta.isEstado();
		}
		@MemberOrder (sequence = "11", name = "Tarjeta")
		public boolean isResuelto() {
			return tarjeta.isResuelto();
		}
		@MemberOrder (sequence = "12", name = "Tarjeta")
		public boolean isReportado() {
			return tarjeta.isReportado();
		}
		


		//---------------------------	Cargas------------------------------------
//		private void cargarTarjeta(final Tarjeta t)
//		{			
//			this.tarjeta= t;
//			this.tarjeta.setNumTarjetaTesco("No tarjeta");
//			this.tarjeta.setFechaReporte(null);
//			this.tarjeta.setFechaCarga(null);
//			this.tarjeta.setLugarObs(null);
//			this.tarjeta.setLineaNegocio(null);
//			this.tarjeta.setDecisionTomada(null);
//			this.tarjeta.setClasifSug(null);
//			this.tarjeta.setEquipo(null);
//			this.tarjeta.setEstado(false);
//			this.tarjeta.setEvento(null);
//			this.tarjeta.setResuelto(false);
//			this.tarjeta.setReportado(false);		
//		}		
		private void cargarClienteNull() {
			this.cliente = new Cliente();
			this.cliente.setNombre("Cliente no encontrado");
			this.cliente.setEmail("Cliente no encontrado");
			this.cliente.setTelefono("Cliente no encontrado");
			this.cliente.setDireccion("Cliente no encontrado");
			this.cliente.setEquipos(new ArrayList<Equipo>());
			
		}			

		//----------------------------	General-----------------------------------
		
		@Programmatic
		public Tarjeta getTarjeta() {
			return this.tarjeta;
		}
		@Programmatic
		public Cliente getCliente() {
			cargarClienteNull();
			return this.cliente;
		}



	@javax.inject.Inject 
    DomainObjectContainer container;
	
	@javax.inject.Inject
	Tarjetas servTarjeta;
	
	@javax.inject.Inject
	Clientes servCliente;
	
	@javax.inject.Inject
	MementoService mementoService;

	
	private String memento;
	@Override
	public String viewModelMemento() {
		
		return this.memento;
	}
	@Override
	public void viewModelInit(String memento) {
		this.memento = memento;
		Memento salida = mementoService.parse(memento);
		String numero = salida.get("numero", String.class);
		List<Tarjeta> tarjetas = container.allInstances(Tarjeta.class);
		
		for(Tarjeta tarjeta : tarjetas){
			if (tarjeta.getNumTarjetaTesco().equals(numero)){
				this.tarjeta = tarjeta;
			}
		}
		if(tarjeta!= null){
			this.cliente=  servTarjeta.clienteDeTarjeta(this.tarjeta);
		}
//		cargarClienteNull();
		
		
	}

	

	
}
