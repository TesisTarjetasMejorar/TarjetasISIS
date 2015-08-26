package dominio.dom.tarjeta;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QTarjetaSQ extends dominio.dom.tarjeta.QTarjeta
{
    public static final QTarjetaSQ jdoCandidate = candidate("this");

    public static QTarjetaSQ candidate(String name)
    {
        return new QTarjetaSQ(null, name, 5);
    }

    public static QTarjetaSQ candidate()
    {
        return jdoCandidate;
    }

    public static QTarjetaSQ parameter(String name)
    {
        return new QTarjetaSQ(TarjetaSQ.class, name, ExpressionType.PARAMETER);
    }

    public static QTarjetaSQ variable(String name)
    {
        return new QTarjetaSQ(TarjetaSQ.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression decisionTomada;

    public QTarjetaSQ(PersistableExpression parent, String name, int depth)
    {
        super(parent, name, depth);
        this.decisionTomada = new StringExpressionImpl(this, "decisionTomada");
    }

    public QTarjetaSQ(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.decisionTomada = new StringExpressionImpl(this, "decisionTomada");
    }
}
