package dominio.dom.email;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.services.email.EmailService;

import dominio.dom.cliete.Cliente;




@DomainService
public class Email {

	@Inject
	private EmailService email;
	
	
	public String enviarEmail(final Cliente cliente){
		List<String> to = new ArrayList<String>();
		to.add(cliente.getEmail());
		String body = "Cliente: "+cliente.getNombre()+" direcccion: "+cliente.getDireccion();
		email.send(to, null, null, "Mail de registro a nustra aplicacion", body, (javax.activation.DataSource[])null);
		return "Email enviado!!!.....";
	}
}
