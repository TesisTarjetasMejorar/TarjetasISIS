package dominio.dom.lugarObservacion;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QLugarObservacion extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<LugarObservacion> implements PersistableExpression<LugarObservacion>
{
    public static final QLugarObservacion jdoCandidate = candidate("this");

    public static QLugarObservacion candidate(String name)
    {
        return new QLugarObservacion(null, name, 5);
    }

    public static QLugarObservacion candidate()
    {
        return jdoCandidate;
    }

    public static QLugarObservacion parameter(String name)
    {
        return new QLugarObservacion(LugarObservacion.class, name, ExpressionType.PARAMETER);
    }

    public static QLugarObservacion variable(String name)
    {
        return new QLugarObservacion(LugarObservacion.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression nombre;
    public final StringExpression descripcion;

    public QLugarObservacion(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.nombre = new StringExpressionImpl(this, "nombre");
        this.descripcion = new StringExpressionImpl(this, "descripcion");
    }

    public QLugarObservacion(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.nombre = new StringExpressionImpl(this, "nombre");
        this.descripcion = new StringExpressionImpl(this, "descripcion");
    }
}
