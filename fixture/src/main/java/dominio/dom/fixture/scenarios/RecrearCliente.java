package dominio.dom.fixture.scenarios;



import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import servicios.Clientes;
import dominio.dom.Cliente;
import dominio.dom.fixture.modules.simple.ClienteTearDown;

public class RecrearCliente extends FixtureScript{

	 public final List<String> NOMBRES = Collections.unmodifiableList(Arrays.asList(
	            "Nico", "Pablo", "Ezequiel", "German"));
	 
	 public final List<String> CORREO = Collections.unmodifiableList(Arrays.asList(
	            "nico.cuevas91@gmail.com", "defaul@gmail.com", "test@gmail.com", "uncorreo@gmail.com"));
	 
	 public final List<String> TELEFONO = Collections.unmodifiableList(Arrays.asList(
	            "123456", "632541", "147852", "369852"));
	 
	 public final List<String> DIRECCION = Collections.unmodifiableList(Arrays.asList(
	            "Lajas 390", "P. Moreno 858", "Sarmiento 789", "Collon Cura 275"));
	 
	 public final List<String> NOMBRES_EQUIPOS = Collections.unmodifiableList(Arrays.asList(
	            "C9", "TSM", "FNC", "SKT 1", "OG", "MYM", "CLG", "DIG", "EWG", "RYC"));
	
	 public RecrearCliente(){
	        withDiscoverability(Discoverability.DISCOVERABLE);
	 }
	 

	private int cantidad;
	 private Cliente cliente;
	 
	 public int getCantidad(){
		 return this.cantidad;
	 }
	 
	 public RecrearCliente setCantidad(final int a){
		 this.cantidad = a;
		 return this;
	 }
	 
	@Override
	protected void execute(ExecutionContext ec) {
		ec.executeChild(this, new ClienteTearDown());
		
        for (int i = 0; i < 4; i++) {
           cliente = wrap(clietnesFactory.Cargar(NOMBRES.get(i), TELEFONO.get(i), CORREO.get(i), DIRECCION.get(i)));
           ec.addResult(this, cliente);
        }
       
		
	}
	
    @javax.inject.Inject
    private Clientes clietnesFactory;
    
 

}
