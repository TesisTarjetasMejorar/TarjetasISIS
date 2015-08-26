package dominio.dom.tarjeta;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QTarjeta extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<Tarjeta> implements PersistableExpression<Tarjeta>
{
    public static final QTarjeta jdoCandidate = candidate("this");

    public static QTarjeta candidate(String name)
    {
        return new QTarjeta(null, name, 5);
    }

    public static QTarjeta candidate()
    {
        return jdoCandidate;
    }

    public static QTarjeta parameter(String name)
    {
        return new QTarjeta(Tarjeta.class, name, ExpressionType.PARAMETER);
    }

    public static QTarjeta variable(String name)
    {
        return new QTarjeta(Tarjeta.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression numTarjetaTesco;
    public final ObjectExpression<org.joda.time.LocalDate> fechaReporte;
    public final ObjectExpression<org.joda.time.LocalDate> fechaCarga;
    public final StringExpression lineaNegocio;
    public final dominio.dom.clasificacionSugerida.QClasificacionSugerida clasifSug;
    public final dominio.dom.lugarObservacion.QLugarObservacion lugarObs;
    public final dominio.dom.equipo.QEquipo equipo;
    public final ObjectExpression<Evento> evento;
    public final BooleanExpression estado;
    public final BooleanExpression resuelto;
    public final BooleanExpression reportado;

    public QTarjeta(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.numTarjetaTesco = new StringExpressionImpl(this, "numTarjetaTesco");
        this.fechaReporte = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "fechaReporte");
        this.fechaCarga = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "fechaCarga");
        this.lineaNegocio = new StringExpressionImpl(this, "lineaNegocio");
        if (depth > 0)
        {
            this.clasifSug = new dominio.dom.clasificacionSugerida.QClasificacionSugerida(this, "clasifSug", depth-1);
        }
        else
        {
            this.clasifSug = null;
        }
        if (depth > 0)
        {
            this.lugarObs = new dominio.dom.lugarObservacion.QLugarObservacion(this, "lugarObs", depth-1);
        }
        else
        {
            this.lugarObs = null;
        }
        if (depth > 0)
        {
            this.equipo = new dominio.dom.equipo.QEquipo(this, "equipo", depth-1);
        }
        else
        {
            this.equipo = null;
        }
        this.evento = new ObjectExpressionImpl<Evento>(this, "evento");
        this.estado = new BooleanExpressionImpl(this, "estado");
        this.resuelto = new BooleanExpressionImpl(this, "resuelto");
        this.reportado = new BooleanExpressionImpl(this, "reportado");
    }

    public QTarjeta(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.numTarjetaTesco = new StringExpressionImpl(this, "numTarjetaTesco");
        this.fechaReporte = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "fechaReporte");
        this.fechaCarga = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "fechaCarga");
        this.lineaNegocio = new StringExpressionImpl(this, "lineaNegocio");
        this.clasifSug = new dominio.dom.clasificacionSugerida.QClasificacionSugerida(this, "clasifSug", 5);
        this.lugarObs = new dominio.dom.lugarObservacion.QLugarObservacion(this, "lugarObs", 5);
        this.equipo = new dominio.dom.equipo.QEquipo(this, "equipo", 5);
        this.evento = new ObjectExpressionImpl<Evento>(this, "evento");
        this.estado = new BooleanExpressionImpl(this, "estado");
        this.resuelto = new BooleanExpressionImpl(this, "resuelto");
        this.reportado = new BooleanExpressionImpl(this, "reportado");
    }
}
