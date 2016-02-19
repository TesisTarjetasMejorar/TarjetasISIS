package servicios.email;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.services.email.EmailService;

import dominio.Cliente;


/*
 * Esta clase de servicio esta implementada para poder enviar mails a los clientes registrados
 * 
 * El mismo se utilizara desde los datos de cada cliente
 * y sera enviado desde el correo:
 * tarjetas.mejorar@gmail.com (contraseña: "apacheIsis")
 */

@DomainService(nature = NatureOfService.VIEW_CONTRIBUTIONS_ONLY)
public class Email {

	@Inject
	private EmailService email;
	
	/*
	 * Este metodo es el encargado de armar el mail con los datos que se deseen agregar
	 *
	 */
	public String enviarEmail(final Cliente cliente,@ParameterLayout (named="Asunto") final String asunto,
													@ParameterLayout (named="Mensaje") final String mensaje){
		List<String> to = new ArrayList<String>();
		to.add(cliente.getEmail());
		String body = mensaje;
		email.send(to, null, null, asunto, body, (javax.activation.DataSource[])null);
		return "Email enviado!!!.....";
	}
}
