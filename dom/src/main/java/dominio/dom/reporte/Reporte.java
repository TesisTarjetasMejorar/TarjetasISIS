package dominio.dom.reporte;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.isis.applib.DomainObjectContainer;

import dominio.dom.cliete.Cliente;
import dominio.dom.equipo.Equipo;
import dominio.dom.lugarObservacion.LugarObservacion;
import dominio.dom.tarjeta.Tarjeta;

public class Reporte {

	
	
	public static void generarReporte(String jrxml, List<LugarObservacion> parametros, String nombreArchivo)  throws JRException{
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//Levanta el jrxml
		
		
		File file = new File(jrxml);

		
		//Almacena el array de datos
		JRBeanArrayDataSource jArray= new JRBeanArrayDataSource(parametros.toArray());
		
		InputStream input = null;
		try{
			input = new FileInputStream(file);

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//Levanta el modelo del reporte			
		JasperDesign jd = JRXmlLoader.load(input);
						
		//Compila el reporte
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		
		//Lo llena con los datos del datasource
		JasperPrint print = JasperFillManager.fillReport(reporte, map, jArray);
		
		//Lo muestra con el jasperviewer
		//JasperViewer.viewReport(print, false);
		
		
		JasperExportManager.exportReportToPdfFile(print, nombreArchivo + ".pdf");
		
		//Abre el reporte recien generado
		try {
		     File path = new File (nombreArchivo + ".pdf");
		     Desktop.getDesktop().open(path);
		}catch (IOException ex) {
		     ex.printStackTrace();
		}

							

		
	}	
	
	
	public static void generarReporteTarjetas(String jrxml, List<Tarjeta> parametros, String nombreArchivo)  throws JRException{
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		List<TarjetaReporte> salida = transformarTarjetas(parametros);
		//Levanta el jrxml
		
		
		File file = new File(jrxml);

		
		//Almacena el array de datos
		JRBeanArrayDataSource jArray= new JRBeanArrayDataSource(salida.toArray());
		
		InputStream input = null;
		try{
			input = new FileInputStream(file);

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//Levanta el modelo del reporte			
		JasperDesign jd = JRXmlLoader.load(input);
						
		//Compila el reporte
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		
		//Lo llena con los datos del datasource
		JasperPrint print = JasperFillManager.fillReport(reporte, map, jArray);
		
		//Lo muestra con el jasperviewer
		//JasperViewer.viewReport(print, false);
		
		
		JasperExportManager.exportReportToPdfFile(print, nombreArchivo + ".pdf");
		
		//Abre el reporte recien generado
		try {
		     File path = new File (nombreArchivo + ".pdf");
		     Desktop.getDesktop().open(path);
		}catch (IOException ex) {
		     ex.printStackTrace();
		}

							

		
	}	

	public static void generarReporteCliente(String jrxml, List<Cliente> parametros, String nombreArchivo)  throws JRException{
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//Levanta el jrxml
		List<ClienteReporte> salida = transformarClientes(parametros);
		
		File file = new File(jrxml);

		
		//Almacena el array de datos
		JRBeanArrayDataSource jArray= new JRBeanArrayDataSource(salida.toArray());
		
		InputStream input = null;
		try{
			input = new FileInputStream(file);

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//Levanta el modelo del reporte			
		JasperDesign jd = JRXmlLoader.load(input);
						
		//Compila el reporte
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		
		//Lo llena con los datos del datasource
		JasperPrint print = JasperFillManager.fillReport(reporte, map, jArray);
		
		//Lo muestra con el jasperviewer
		//JasperViewer.viewReport(print, false);
		
		
		JasperExportManager.exportReportToPdfFile(print, nombreArchivo + ".pdf");
		
		//Abre el reporte recien generado
		try {
		     File path = new File (nombreArchivo + ".pdf");
		     Desktop.getDesktop().open(path);
		}catch (IOException ex) {
		     ex.printStackTrace();
		}		
	}	
 
	
	private static List<ClienteReporte> transformarClientes(List<Cliente> parametros) {
		List<ClienteReporte> salida= new ArrayList<ClienteReporte>();
		for (Cliente a : parametros){
			ClienteReporte aux = new ClienteReporte();
			aux.setNombre(a.getNombre());
			aux.setDireccion(a.getDireccion());
			aux.setEmail(a.getEmail());
			aux.setTelefono(a.getTelefono());
			String equipo= "";
			for( Equipo e : a.getEquipos()){
				equipo= equipo+e.getNombre()+", ";
			}
			aux.setEquipo(equipo);
		}
		return salida;
	}


	private static List<TarjetaReporte> transformarTarjetas(
			List<Tarjeta> parametros) {
		List<TarjetaReporte> salida = new ArrayList<TarjetaReporte>();
		for(Tarjeta a : parametros)
		{
			TarjetaReporte aux = new TarjetaReporte();
			aux.setClasifSug(a.getClasifSug().getNombre());
			aux.setNumTarjetaTesco(a.getNumTarjetaTesco());
			aux.setFechaReporte("a completar");
			aux.setFechaCarga("a completar fecha");
			aux.setLugarObs(a.getLugarObs().getNombre());
			aux.setLineaNegocio(a.getLineaNegocio());
			aux.setDecisionTomada(a.getDecisionTomada());
			aux.setEquipo(a.getEquipo().getNombre());
			if(a.isEstado()){
				aux.setEstado("Abierto");	
			}else
				aux.setEstado("Cerrado");
			
			if(a.isReportado()){
				aux.setReportado("Si");	
			}else
				aux.setReportado("No");
			
			if(a.isResuelto()){
				aux.setResuelto("Si");	
			}else
				aux.setResuelto("Si");

			aux.setEvento(a.getEvento().getNombre());
			salida.add(aux);
			
		}
		
		return salida;
	}


	@javax.inject.Inject	
	public ReportContext reportContext;
	
	@javax.inject.Inject
	DomainObjectContainer container;


	
}
