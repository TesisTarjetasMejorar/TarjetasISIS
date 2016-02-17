package dominio.dom.fixture.scenarios;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import dominio.dom.Equipo;


public class RecrearEquipo extends FixtureScript{

	
	 public final List<String> NAMES = Collections.unmodifiableList(Arrays.asList(
	            "C9", "TSM", "FNC", "SKT 1", "OG", "MYM", "CLG", "DIG", "EWG", "RYC"));

	 
	 public RecrearEquipo(){
	        withDiscoverability(Discoverability.DISCOVERABLE);
	 }
	 
	 private int cantidad;
	 
	 
	 public int getCantidad(){
		 return this.cantidad;
	 }
	 
	 public RecrearEquipo setCantidad(final int a){
		 this.cantidad = a;
		 return this;
	 }
	
	private final List< Equipo> equipos = new ArrayList<Equipo>();
	 
	 
	public List<Equipo> getEquipos() {
		return equipos;
	}

	@Override
	protected void execute(ExecutionContext ec) {
        final int number = defaultParam("number", ec, 4);

        // validate
        if(number < 0 || number > NAMES.size()) {
            throw new IllegalArgumentException(String.format("number must be in range [0,%d)", NAMES.size()));
        }
        
//        ec.executeChild(this, new EquiposTearDown());
        
//        for (int i = 0; i < number; i++) 
        {
//            final CreadorEquipos fs = new CreadorEquipos().setNombre(NAMES.get(i));
//            ec.executeChild(this, fs.getNombre(), fs);
//            
//            equipos.add(fs.getEquipo());
        
        	
        }
		
	}

	
}
