package dominio.dom.fixture.modules.simple;

import java.util.Random;

public class GenericData {
	
	private static int	Cantidad=4;
	
	private static String Nombre="Pablo,Armando,Emiliano,Don Nacho,Samuel,Alejandro,Mateo,Carlos,Benjamín,Daniel,Joaquín,Tomás,Gabriel,Lucas,Martín,Emmanuel,Alexander,David,Emiliano,Carlos,JuanJosé,Andrés,Felipe,Ignacio,Leonardo,Adrián,Francisco,Rodrigo,Alvaro,MiguelÁngel,Fernando,Santino,Bautista,Agustín,JuanPablo,Vicente,Thiago,Maximiliano,Pablo,Eduardo,Christopher,Kevin,Isaac,JuanDiego,Aarón,Dylan,Jesús,Esteban,Manuel,JuanSebastián,Franco,Lautaro,Miguel,JuanDavid,Ricardo,Bruno,Luciano,Juan,Emilio,JuanEsteban,Julián,Valentino,Javier,Joshua,Rafael,Jorge,José,Luis,DiegoAlejandro,Gael,Óscar,Nahuel,Máximo,Axel,Facundo,Jonathan,Ian,Josué,Camilo,Sergio,Jerónimo,Álex,Mauricio,JuanCamilo,Alonso,Anthony,Dante,Christian,Simón,Patricio,Héctor,Iván,Marcos,Ramiro,Alberto,Matthew,Pedro,Mario,Alan,Arturo,Adrian,Manuel,Jose,Gogo,Pedro,Mariano,Leandro,Gonzalo,Roberto,Daniel,Fernando,Damian,Oscar,Lautaro,Miguel,Diego,Pablo,Raul,Ricardo,Matias,Hector,Juan,Emiliano,Cesar,Gerardo,Emiliano,Francisco,Martin,Leonardo";
	private static String Apellido="Picheira,Bosso,Pereyra,Cartes,Gómez,Fernández,López,Torroija,Estevez,Martínez,Pérez,García,Sánchez,Romero,Sosa,Álvarez,Torres,Ruiz,Ramírez,Flores,Acosta,Benítez,Medina,Suárez,Herrera,Aguirre,Pereyra,Gutiérrez,Giménez,Molina,Silva,Benigar,Castro,Rojas,Ortíz,Núñez,Luna,Juárez,Cabrera,Ríos,Ferreyra,Godoy,Morales,Domínguez,Moreno,Peralta,Vega,Carrizo,Quiroga,Garcia Pacek,Castillo,Ledesma,Muñoz,Ojeda,Ponce,Vera,Vázque,Villalba,Cardozo,Navarro,Ramos,Arias,Coronel,Córdoba,Figueroa,Correa,Cáceres,Vargas,Maldonado,Mansilla,Farías,Rivero,Paz,Miranda,Roldán,Méndez,Lucero,Cruz,Hernández,Agüero,Páez,Blanco,Mendoza,Barrios,Escobar,Ávila,Soria,Leiva,Acuña,Martin,Maidana,Moyano,Cola,Campos,Olivera,Duarte,Soto,Franco,Bravo,Valdéz,Toledo,Andrada,Andrade,Montenegro,Leguizamón,Chávez,Arce,Valdez,Yong Wong";	
	private static String Telefono="299-1111111,299-4235818,299-4554531,299-4235818,";	
	private static String Mail="nico.cuevas91@gmail.com, zequirodriuez@gmail.com, pincheirapabloj@gmail.com, s_a_c_1985@hotmail.com";
	private static String Calle="Jujuy,La Rioja,Salta,Pampa,Misiones,Buenos Ahires,Bahia Blanca,Mendoza,Santafe,Boedo,San Martin,Belgrano,Aconcagua,Domene,Chile,España,Godoy,Perito Moreno";
	private static String Letras="A,B,C,D,E,F,G,H";
	private static String Numeros="1,2,3,4,5,6,7,8,9,10,11,12,13";
	private static String Local="Eq junin,MDQ ,Neuquen 04 ,JuninA-54, QWSA 567";
	private static String Equipo="Z10,Samsung Galaxy S6, Samsung Galaxy S4, Curve8520, IPhone 6";
	private static String ModeloId="60,62,62,60,78";

//	
	public static int ObtenerCantidad()
	{
		return Cantidad;
	}
	
	public static String ObtenerNombre()
	{
		return ObtenerValor(Nombre);
	}
	
	public static String ObtenerApellido()
	{
		return ObtenerValor(Apellido);
	}
	
	public static String ObtenerTelefono()
	{
		return ObtenerValor(Telefono);
	}
	
	public static String ObtenerMail()
	{
		return ObtenerValor(Mail);
	}
		
	public static String ObtenerCalle()
	{
		return ObtenerValor(Calle);
	}
	
	public static String ObtenerNumeros()
	{
		return ObtenerValor(Numeros);
	}
	
	public static String ObtenerLocal()
	{
		return ObtenerValor(Local);
	}
	
	public static String ObtenerLetras(int index)
	{
		String[] partes=Dividir(Letras);
		return partes[index];
	}
	
	public static int Random(int Inicial, int Final)
	{
		Random rnd = new Random();
		int Resul=(int) (rnd.nextDouble() * Final + Inicial);
		return Resul;
	}
	
	private static String ObtenerValor(String valor)
	{
		String[] Partes=Dividir(valor);
		return Partes[Random(0,Partes.length)];
	}
	
	public static String[] Dividir(String palabras)
	{
		String[] partes = palabras.split(",");
		return partes;
	}
	
	
	public static String ObtenerModeloAbreviatura()
	{
		return ObtenerValor(Equipo);
	}
	
	public static String ObtenerModeloId()
	{
		return ObtenerValor(ModeloId);
	}
	
	
}
