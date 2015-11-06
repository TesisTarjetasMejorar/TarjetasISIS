package dominio.dom.tarjeta.templates;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import javax.annotation.PostConstruct;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.value.Blob;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import com.google.common.io.Resources;
import dominio.dom.tarjeta.Tarjeta;
import org.apache.isis.applib.annotation.NatureOfService;



@DomainService(nature = NatureOfService.VIEW_CONTRIBUTIONS_ONLY)
public class ConfigPDF
{
    private byte[] pdfAsBytes;
	
    @PostConstruct
    public void init() throws IOException {
        pdfAsBytes = Resources.toByteArray(Resources.getResource(this.getClass(), "newPDF.pdf"));
    }
    
    
    @MemberOrder(sequence = "10")
    public Blob descargarPDF(
            final Tarjeta order) throws Exception {

        try (PDDocument pdfDocument = loadAndPopulateTemplate(order)) {

            final ByteArrayOutputStream target = new ByteArrayOutputStream();
            pdfDocument.save(target);
            final String name = "Tarjeta-" + order.getNumTarjetaTesco() + ".pdf"; //nombre del archivo a descargar
            final String mimeType = "application/pdf";
            final byte[] bytes = target.toByteArray();

            return new Blob(name, mimeType, bytes);
        }
    }
    private PDDocument loadAndPopulateTemplate(Tarjeta order) throws Exception {
        PDDocument pdfDocument = PDDocument.load(new ByteArrayInputStream(pdfAsBytes));
        PDAcroForm pdfForm = pdfDocument.getDocumentCatalog().getAcroForm();
        List<PDField> fields = pdfForm.getFields();
        for (PDField field : fields)
        {

  
      
			switch (field.getFullyQualifiedName())
				{
			case "txtNumTar": field.setValue(order.getNumTarjetaTesco());break;
			case "txtFechaReporte": field.setValue(order.getFechaReporte().toString());break;
			case "txtFechaCarga": field.setValue(order.getFechaCarga().toString());break;
			case "txtLugarObs" : field.setValue(order.getLugarObs().getNombre());break;
			case "txtClasiSuge" : field.setValue(order.getClasifSug().getNombre());break;
			case "txtEquipo" : field.setValue(order.getEquipo().getNombre());break;
			case "txtEvento" : field.setValue(order.getEvento().getNombre());break;
			case "txtDecisionTomada" : field.setValue(order.getDecisionTomada());break;
			case "txtEstado" : if (order.isEstado())
								{
									field.setValue("Abierto");break;
								}else
								{
									field.setValue("Cerrado");break;
								}
			case "txtResuelto" : if (order.isResuelto())
								{
									field.setValue("Resuelto");break;
								}else
								{
									field.setValue("No Resuelto");break;
								}
			case "txtReportado" : if (order.isReportado())
								{
									field.setValue("Reportado");break;
								}else
								{
									field.setValue("No Reportado");break;
								}
			
				}

            				
        }



        return pdfDocument;
    }
}

    

