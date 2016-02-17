package viewModel;

import javax.jdo.annotations.Column;
import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import dominio.dom.LugarObservacion;

@DomainServiceLayout(menuOrder = "8" ,menuBar = DomainServiceLayout.MenuBar.SECONDARY,named="Vistas Rapidas")
@MemberGroupLayout(columnSpans = { 4, 0, 0, 9 })
public class LugarObservacionViewmodel extends AbstractViewModel {

	
	private String nombre;
	private String memento;
	private String descripcion;
	
	
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getNombre() {
		return nombre;
	}
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public String getDescripcion() {
		return descripcion;
	}

	public LugarObservacion cargaRapida(
			
			final @ParameterLayout(named="Nombre") String nombre,
										final @ParameterLayout(named="Descripcion") String descripcion)
	
	{
		final LugarObservacion obj = container.newTransientInstance(LugarObservacion.class);
		obj.setNombre(nombre);
		obj.setDescripcion(descripcion);
        container.persistIfNotAlready(obj);
		return obj;
	}

//	public List<Tarjeta> buscarTarjeta(){
//		List<LugarObservacion> listaLo = container.allInstances(LugarObservacion.class); 
//		LugarObservacion auxLugarO= null;
//		for(LugarObservacion a : listaLo){
//			if(a.getNombre().equals(this.nombre));
//			auxLugarO= a;
//		}
//		if(auxLugarO == null)
//			return null;
//		
//		List<Tarjeta> tarjetas= container.allInstances(Tarjeta.class);
//		for(Tarjeta auxTarjeta : tarjetas){
//			if(auxTarjeta.getLugarObs().getNombre() != auxLugarO.getNombre()){
//				tarjetas.remove(auxTarjeta);
//			}
//		}
//		return tarjetas;
//	}

	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String memento) 
	{
		this.memento = memento;
		Memento memento2 = mementoService.parse(memento);
		this.nombre= memento2.get("nombre", String.class);
		this.descripcion= memento2.get("descripcion", String.class);
		
	}
	
	
	@javax.inject.Inject
	DomainObjectContainer container;
	
	@javax.inject.Inject
	MementoService mementoService;
}