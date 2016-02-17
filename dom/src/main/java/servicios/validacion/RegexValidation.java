
package servicios.validacion;

public final class RegexValidation {
	public static final class ValidaNombres {

		private ValidaNombres() {
		}
		public static final String INICIALES = "[a-z,A-Z,ñ,Ñ]{2}$+";
		public static final String NOMBRE = "[A-Z]+[a-z,ñ]*+[ ]+[A-Z]+[a-z,ñ]*$+";
	}

	public static final class ValidaTel {
		private ValidaTel() {

		}
		public static final String NUMEROTEL = "[+]?[0-9]{3}+[-]+[0-9]{7}";
	}

	public static final class ValidaMail {
		private ValidaMail() {

		}
		public static final String EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	}
	public static final class ValidaDireccion{
		private ValidaDireccion() {

		}
		public static final String DIRECCION = "^[_A-Za-z-\\+]+(\\ [0-9-]+)$";
	}


	public static final class ValidaPalabra{
		private ValidaPalabra() {

		}
		public static final String PALABRA = "[a-zA-Z]*$";
		public static final String PALABRAINICIALMAYUSCULA = "[A-Z]+[a-z]*$";
	}

}