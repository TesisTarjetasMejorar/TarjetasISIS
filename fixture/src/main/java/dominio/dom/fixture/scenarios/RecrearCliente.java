package dominio.dom.fixture.scenarios;



import java.util.ArrayList;
import java.util.List;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import servicios.Clientes;
import viewModel.ViewModelCliente;
import dominio.Cliente;
import dominio.Equipo;
import dominio.dom.fixture.modules.simple.ClienteTearDown;
import dominio.dom.fixture.modules.simple.GenericData;

public class RecrearCliente extends FixtureScript{
	
	 public RecrearCliente(){
	        withDiscoverability(Discoverability.DISCOVERABLE);
	 }
	 

	private int cantidad;
	 @SuppressWarnings("unused")
	private ViewModelCliente cliente;
	 
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
		
        int Cantidad=GenericData.ObtenerCantidad();
		
        List<Cliente> clientes = new ArrayList<Cliente>();
		
        for(int x=0; x<=Cantidad;x++)
        {
        	Cliente cli = new Cliente();
        	cli.setNombre(GenericData.ObtenerNombre() + " "+GenericData.ObtenerApellido());
        	cli.setEmail(GenericData.ObtenerMail());
        	cli.setTelefono(GenericData.ObtenerTelefono());
        	cli.setDireccion(GenericData.ObtenerCalle()+" "+GenericData.Random(1, 999));
        	List<Equipo> equipos = new ArrayList<Equipo>();
        	cli.setEquipos(equipos);
            for(int c=0; c<=3;c++)
            {
            	Equipo eq = new Equipo();
            	eq.setNombre(GenericData.ObtenerLocal());
            }
            clientes.add(cli);         
            for(Cliente client : clientes)
            {
            	create(client.getNombre(), client.getEmail(),client.getTelefono(),client.getDireccion(),client.getEquipos(),ec);
            }         
     	
        }

       
		
	}
	
    @SuppressWarnings("deprecation")
	private ViewModelCliente create(String nombre, String email, String telefono,
			String direccion, List<Equipo> equipos, ExecutionContext ec) {
		return  ec.add(this, clietnesFactory.Cargar(nombre, telefono, email, direccion));
		
	}


	@javax.inject.Inject
    private Clientes clietnesFactory;
    
 

}
