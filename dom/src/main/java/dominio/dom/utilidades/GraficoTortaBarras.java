package dominio.dom.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import com.googlecode.wickedcharts.highcharts.options.Axis;
import com.googlecode.wickedcharts.highcharts.options.ChartOptions;
import com.googlecode.wickedcharts.highcharts.options.Cursor;
import com.googlecode.wickedcharts.highcharts.options.DataLabels;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.PlotOptions;
import com.googlecode.wickedcharts.highcharts.options.PlotOptionsChoice;
import com.googlecode.wickedcharts.highcharts.options.SeriesType;
import com.googlecode.wickedcharts.highcharts.options.Title;
import com.googlecode.wickedcharts.highcharts.options.Tooltip;
import com.googlecode.wickedcharts.highcharts.options.color.HexColor;
import com.googlecode.wickedcharts.highcharts.options.color.HighchartsColor;
import com.googlecode.wickedcharts.highcharts.options.color.NullColor;
import com.googlecode.wickedcharts.highcharts.options.color.RadialGradient;
import com.googlecode.wickedcharts.highcharts.options.functions.PercentageFormatter;
import com.googlecode.wickedcharts.highcharts.options.series.Point;
import com.googlecode.wickedcharts.highcharts.options.series.PointSeries;
import com.googlecode.wickedcharts.highcharts.options.series.Series;


public class GraficoTortaBarras extends Options {
	private static final long serialVersionUID = 1L;
	
	public GraficoTortaBarras(Map<String, AtomicInteger> a){
	
		setChartOptions(new ChartOptions()
        .setPlotBackgroundColor(new NullColor())
        .setPlotBorderWidth(null)
        .setPlotShadow(Boolean.FALSE));
    
    setTitle(new Title("Grafico Tarjetas por Cliente"));

    setSubtitle(new Title("Los meses que no aparecen, es por que no se registran cargas de tarjetas"));
    
    
    PercentageFormatter formatter = new PercentageFormatter();
    setTooltip(
            new Tooltip()
                .setFormatter(
                        formatter)
                .       setPercentageDecimals(1));

    setPlotOptions(new PlotOptionsChoice()
        .setPie(new PlotOptions()
        .setAllowPointSelect(Boolean.TRUE)
        .setCursor(Cursor.POINTER)
        .setDataLabels(new DataLabels()
        .setEnabled(Boolean.TRUE)
        .setColor(new HexColor("#000000"))
        .setConnectorColor(new HexColor("#000000"))
        .setFormatter(formatter))));
    //*********************************************************************************
    Series<Point> series = new PointSeries()
        .setType(SeriesType.COLUMN);
    List<String> titles = new ArrayList<String>();
    

    //*********************************************************************************
  
    int i=0;
    for (Map.Entry<String, AtomicInteger> entry : a.entrySet()) {
        series
        .addPoint(
                new Point(entry.getKey(), entry.getValue().get()).setColor(
                        new RadialGradient()
                            .setCx(0.5)
                            .setCy(0.3)
                            .setR(0.7)
                                .addStop(0, new HighchartsColor(i))
                                .addStop(1, new HighchartsColor(i).brighten(-0.3f))));

        titles.add(""+entry.getKey());
        i++;
    }
    
    this.setxAxis(new Axis().setCategories(titles));

 

    //*********************************************************************************
    addSeries(series);
	}
	

	

}

