package dominio.dom.tarjeta;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QTarjetaHSES extends dominio.dom.tarjeta.QTarjeta
{
    public static final QTarjetaHSES jdoCandidate = candidate("this");

    public static QTarjetaHSES candidate(String name)
    {
        return new QTarjetaHSES(null, name, 5);
    }

    public static QTarjetaHSES candidate()
    {
        return jdoCandidate;
    }

    public static QTarjetaHSES parameter(String name)
    {
        return new QTarjetaHSES(TarjetaHSES.class, name, ExpressionType.PARAMETER);
    }

    public static QTarjetaHSES variable(String name)
    {
        return new QTarjetaHSES(TarjetaHSES.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression AccionRealizada;

    public QTarjetaHSES(PersistableExpression parent, String name, int depth)
    {
        super(parent, name, depth);
        this.AccionRealizada = new StringExpressionImpl(this, "AccionRealizada");
    }

    public QTarjetaHSES(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.AccionRealizada = new StringExpressionImpl(this, "AccionRealizada");
    }
}
