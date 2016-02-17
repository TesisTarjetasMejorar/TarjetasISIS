package viewModel;

import javax.inject.Named;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import dominio.dom.LugarObservacion;





@DomainServiceLayout(menuOrder = "90",named="Accesos rapidos")
@DomainService
public class ViewModel {
	//titulo de la pantalla de carga
	public String title() {
		return "Lugar de observacion";
	}

	
	public LugarObservacionViewmodel lugaresDeObservacion(
			@Named("Lugar de observaciones") LugarObservacion lugar)
	{
		Memento memento = mementoService.create();
		memento.set("nombre", lugar.getNombre());
		memento.set("descripcion", lugar.getDescripcion());

		return container.newViewModelInstance(
				LugarObservacionViewmodel.class, memento.asString());
	}

	
	
	
	
	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	MementoService mementoService;

}
