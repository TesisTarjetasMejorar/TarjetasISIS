package viewModel;


import java.util.List;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Nature;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.RenderType;
import servicios.Clientes;
import servicios.validacion.RegexValidation;
import dominio.Cliente;



/*	columnSpans={12,3,3,6},
 * 	los primeros 3 valores son de las propiedades del viewModel
 *  y el ultimo de una coleccion final. Si se le da el valor 12 
 *  significa que ocupa toda la pantalla el primer valor (esto se trabaja con boostrap)
 *  
 * 
 */

@MemberGroupLayout
( 
		columnSpans={1,1,1,12}
)
@DomainObject(nature = Nature.VIEW_MODEL)
public class ViewModelCliente{
	public String title()
	{
	      return "Cliente";
	}
	
	private List<Cliente> clientes;
	
	
	@CollectionLayout(render = RenderType.EAGERLY)
	public List<Cliente> getClientes(){
		clientes= (List<Cliente>) container.allInstances(Cliente.class);
		return clientes;
	}	
	
	public void setClientes(List<Cliente> clientes){
		this.clientes = clientes;
	}
	
	
	@MemberOrder (sequence = "4")
	public ViewModelCliente agregarCliente (@ParameterLayout (named="Nombre") @Parameter(regexPattern = RegexValidation.ValidaNombres.NOMBRE )final String nombre,
			@ParameterLayout (named="Telefono")@Parameter(regexPattern = RegexValidation.ValidaTel.NUMEROTEL)final String telefono,
			@ParameterLayout (named="E-Mail")@Parameter(regexPattern = RegexValidation.ValidaMail.EMAIL) final String email,
			@ParameterLayout (named="Direccion") @Parameter(regexPattern = RegexValidation.ValidaDireccion.DIRECCION )final String direccion){	
		return factoriaCliente.Cargar(nombre, telefono, email, direccion);
	}
	
	
	public String eliminarCliente(Cliente c) {
		String salida="";
		salida = factoriaCliente.EliminarCliente(c);	
		return salida;
	}
	
	
		

	@javax.inject.Inject
	DomainObjectContainer container;

	@javax.inject.Inject
	Clientes factoriaCliente;
	
	@javax.inject.Inject
	Cliente cliente;
	
	
	

}
