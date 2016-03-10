package viewModel;


import java.util.List;
import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
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
		columnSpans={4,4,0,2},middle="Cliente",left="Tarjeta"
)
public class ViewModelTarjeta extends AbstractViewModel
{	

	
	private Cliente cliente= null;
		
	//----------------------------	Cliente-----------------------------------
		
		
		@MemberOrder (sequence = "20", name = "Cliente")
		@Title
		public String getNombre() {
			return cliente.getNombre();
		}
		@MemberOrder (sequence = "21", name = "Cliente")
		public String getEmail() {
			return cliente.getEmail();
		}
		@MemberOrder (sequence = "22", name = "Cliente")
		public String getTelefono() {
			return cliente.getTelefono();
		}
		@MemberOrder (sequence = "23", name = "Cliente")
		public String getDireccion() {
			return cliente.getDireccion();
		}

		@CollectionLayout(render = RenderType.EAGERLY)
		@MemberOrder (sequence = "24", name = "Cliente")
		public List<Equipo> getEquipos() {
			return cliente.getEquipos();
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
		
		public Tarjeta getTarjeta() {
			return this.tarjeta;
		}		
		public Cliente getCliente() {
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

		
		
	}

	

	
}
