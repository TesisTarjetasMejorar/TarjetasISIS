package dominio.dom.clasificacionSugerida;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QClasificacionSugerida extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<ClasificacionSugerida> implements PersistableExpression<ClasificacionSugerida>
{
    public static final QClasificacionSugerida jdoCandidate = candidate("this");

    public static QClasificacionSugerida candidate(String name)
    {
        return new QClasificacionSugerida(null, name, 5);
    }

    public static QClasificacionSugerida candidate()
    {
        return jdoCandidate;
    }

    public static QClasificacionSugerida parameter(String name)
    {
        return new QClasificacionSugerida(ClasificacionSugerida.class, name, ExpressionType.PARAMETER);
    }

    public static QClasificacionSugerida variable(String name)
    {
        return new QClasificacionSugerida(ClasificacionSugerida.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression nombre;
    public final StringExpression descripcion;

    public QClasificacionSugerida(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.nombre = new StringExpressionImpl(this, "nombre");
        this.descripcion = new StringExpressionImpl(this, "descripcion");
    }

    public QClasificacionSugerida(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.nombre = new StringExpressionImpl(this, "nombre");
        this.descripcion = new StringExpressionImpl(this, "descripcion");
    }
}
