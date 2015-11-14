package dominio.dom.utilidades;

import java.io.Serializable;

import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Value;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.Title;


@Value(semanticsProviderClass=GraficoTarjetaSemantica.class)
public class GraficoTarjeta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Options options;

	public GraficoTarjeta(Options o){
		this.options=o;
	}
	
	
	   public String title() {
	        Title title = getOptions().getTitle();
	        return "Tarjetas Resueltas / No Resueltas";
	    }
	
	@Programmatic
	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}
	
	
}
