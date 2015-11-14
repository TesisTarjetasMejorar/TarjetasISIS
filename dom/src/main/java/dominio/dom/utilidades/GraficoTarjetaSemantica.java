package dominio.dom.utilidades;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;
import org.apache.isis.applib.adapters.DefaultsProvider;
import org.apache.isis.applib.adapters.EncoderDecoder;
import org.apache.isis.applib.adapters.Parser;
import org.apache.isis.applib.adapters.ValueSemanticsProvider;


public class GraficoTarjetaSemantica implements ValueSemanticsProvider<GraficoTarjeta>
{

	@Override
	public Parser<GraficoTarjeta> getParser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EncoderDecoder<GraficoTarjeta> getEncoderDecoder() {
        return new EncoderDecoder<GraficoTarjeta>() {

            public String toEncodedString(GraficoTarjeta toEncode) {
                return Base64Serializer.toString(toEncode);
            }

            public GraficoTarjeta fromEncodedString(String encodedString) {
                return (GraficoTarjeta) Base64Serializer.fromString(encodedString);
            }
        };
		
	}

	@Override
	public DefaultsProvider<GraficoTarjeta> getDefaultsProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isImmutable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEqualByContent() {
		// TODO Auto-generated method stub
		return true;
	}

}

//***************************************************************************************************************************

class Base64Serializer {

    public static class Exception extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public Exception(java.lang.Exception e) {
            super(e);
        }
    }

    static Object fromString( String s ) {
        final byte [] data = Base64.decodeBase64( s );
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(  data ) );
            return ois.readObject();
        } catch (IOException e) {
            throw new Base64Serializer.Exception(e);
        } catch (ClassNotFoundException e) {
            throw new Base64Serializer.Exception(e);
        } finally {
            try {
                if(ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                throw new Base64Serializer.Exception(e);
            }
        }
    }

    static String toString( Serializable serializable ) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream( baos );
            oos.writeObject( serializable );
            return new String( Base64.encodeBase64( baos.toByteArray() ) );
        } catch (IOException e) {
            throw new Base64Serializer.Exception(e);
        } finally {
            try {
                if(oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                throw new Base64Serializer.Exception(e);
            }
        }
    }
}