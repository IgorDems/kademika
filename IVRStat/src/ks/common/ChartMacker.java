package ks.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import ks.db.utils.Grouping;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StackedXYBarRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.RangeType;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.data.time.TimePeriod;
import org.jfree.data.time.TimeTableXYDataset;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

/**
 * 	ChartMacker.java - класс для создания диаграмм 
 *  @author Voloshyn
 *  @version 1.0
 * @param <JFreeChart>
 *  @see ChartMacker#ChartMacker(Date, Date)
 *  @see ChartMacker#calendar
 *  @see ChartMacker#startDate
 *  @see ChartMacker#endDate
 *  @see ChartMacker#MINIMAL
 *  @see ChartMacker#NORMAL
 *  @see ChartMacker#MAX
 *  @see ChartMacker#getChart(HashMap, Grouping, String[])
 */

public class ChartMacker<JFreeChart> {

	
	private final Calendar calendar = new GregorianCalendar();
	
	/**
	 * Начальное время статистики
	 */
	private Date startDate = null;
	
	/**
	 * Конечное время статистики
	 */
	private Date endDate = null;
	
	/**
	 * Граница малой нагрузки потока (определяется количеством каналов)
	 */
	private static final double MINIMAL = 20.0;
	
	/**
	 * Граница средней нагрузки потока (определяется количеством каналов)
	 */
	private static final double NORMAL = 25.0;
	
	/**
	 * Граница критической нагрузки потока (определяется количеством каналов)
	 */
	private static final double MAX = 30.0;
	
	/**
	 * Инициализация объекта с указанием границ статистики
	 * 
	 * @param startDate_value начальное время статистики
	 * @param endDate_value конечное время статистики
	 */
	public ChartMacker(final Date startDate_value, final Date endDate_value) {
		startDate = startDate_value;
		endDate = endDate_value;
	}
	
	/**
	 * Метод формирует диаграмму статистики на основании 
	 * идентификаторов сервера и потока,
	 * начальном и конечном времени статистики,
	 * типе группировки статистики
	 * 
	 * @param statS Данные о нагрузке
	 * @param grouping Тип группировки статистики
	 * @param names Идентификаторы сервера и потока соответсвенно
	 * @return Диаграмма статистики
	 * @throws java.lang.IllegalArgumentException
	 * @throws ParseException
	 */
	public JFreeChart getChart(HashMap<Date, Double> statS, Grouping grouping, String[] names) throws java.lang.IllegalArgumentException, ParseException {
		TimeTableXYDataset dataset = new TimeTableXYDataset();
		Boolean equalsSet = Boolean.FALSE;
		if(grouping.equals(Grouping.WEEK)){
			Boolean firstFound = Boolean.FALSE;
			Iterator<Date> keys = new TreeSet<Date>(statS.keySet()).iterator();
			if(!firstFound && keys.hasNext()){
				Date first = keys.next();
				if(first.after(startDate)){
					while(first.after(startDate)){
						calendar.setTime(first);
						calendar.add(Calendar.WEEK_OF_MONTH, -1);
						first.setTime(calendar.getTimeInMillis());
					}
				}
				startDate.setTime(first.getTime());
				firstFound = Boolean.TRUE;
			}
		}
		calendar.setTime(startDate);
		final SimpleDateFormat sdf = grouping.getSdf();
		while(!calendar.getTime().after(endDate)){
			final Date start = sdf.parse(sdf.format(calendar.getTime()));
			calendar.add(grouping.getType(), grouping.getInterval());
			final Date end = sdf.parse(sdf.format(calendar.getTime().after(endDate) ? endDate : calendar.getTime()));
			if(equalsSet) break;
			if(end.equals(endDate)) equalsSet = Boolean.TRUE;		
			
			TimePeriod period = new SimpleTimePeriod(start, end);
			Double stat = statS.get(start) == null ? 0 : statS.get(start);
			if(stat < MINIMAL){
				dataset.add(period, stat, "Низкая нагрузка");
				dataset.add(period, 0.0, "Средняя нагрузка");
				dataset.add(period, 0.0, "Критическая нагрузка");
			} else if(stat >= MINIMAL && stat < NORMAL){
				dataset.add(period, MINIMAL, "Низкая нагрузка");
				dataset.add(period, stat-MINIMAL, "Средняя нагрузка");
				dataset.add(period, 0.0, "Критическая нагрузка");
			} else if(stat >= NORMAL && stat <= MAX){
				dataset.add(period, MINIMAL, "Низкая нагрузка");
				dataset.add(period, NORMAL-MINIMAL, "Средняя нагрузка");
				dataset.add(period, stat-NORMAL, "Критическая нагрузка");
			}
		}

		XYBarRenderer renderer = new StackedXYBarRenderer(0.0);
		renderer.setBarPainter(new StandardXYBarPainter());
		renderer.setDrawBarOutline(true);
		renderer.setShadowVisible(false);

		final GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.green
				, 0.0f, 0.0f, Color.green);
		final GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.yellow,
				0.0f, 0.0f, Color.yellow);
		final GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red,
				0.0f, 0.0f, Color.red);
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		renderer.setSeriesPaint(2, gp2);

		XYPlot plot = new XYPlot(dataset, new DateAxis("Время"),
				new NumberAxis("Количество каналов"), renderer);

		plot.getDomainAxis().setLowerMargin(0.0);
		plot.getDomainAxis().setUpperMargin(0.0);
		NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
		rangeAxis.setTickUnit(new NumberTickUnit(2));
		rangeAxis.setRangeType(RangeType.POSITIVE);
		rangeAxis.setRange(0,30);
		plot.setNoDataMessage("Информация не найдена");
		plot.setNoDataMessageFont(new Font("SansSerif", Font.BOLD, 20));
		plot.setNoDataMessagePaint(Color.BLUE);
		
		double max = Double.NEGATIVE_INFINITY;
		double maxPosition = Double.NEGATIVE_INFINITY;
		double sum = 0.0;
		int sumSize = 0;
		Iterator<Date> keys = statS.keySet().iterator();
		while(keys.hasNext()){
			Double stat = statS.get(keys.next());
			if(stat != 0){
				sum += stat;
        		sumSize++;
        	}
            if (stat> maxPosition) maxPosition = stat;
		}
		try{
			if(maxPosition != 0 && !Double.isNaN(maxPosition) && !Double.isInfinite(maxPosition)){
				BigDecimal x = new BigDecimal(maxPosition);
				max = x.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			} else {
				max = 0.0;
				maxPosition = 0.0;
			}
		} catch (java.lang.NumberFormatException e) {
        	System.err.println("maxPosition = " + maxPosition);
        	System.err.println("max = " + max);
        	e.printStackTrace();
		}
		
		final IntervalMarker maxLoad = new IntervalMarker(maxPosition,maxPosition);
        maxLoad.setLabel("Максимальное = " + max);
        maxLoad.setLabelPaint(new Color(31, 5, 225));
        maxLoad.setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        maxLoad.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
        maxLoad.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        maxLoad.setPaint(Color.green);//new Color(222, 222, 255, 128)
        plot.addRangeMarker(maxLoad, Layer.FOREGROUND);
        Double avgPosition = sum/sumSize;
        Double avg = Double.NEGATIVE_INFINITY;
        try{
	        if(avgPosition != 0 && !Double.isNaN(avgPosition) && !Double.isInfinite(avgPosition)){
	        	BigDecimal x = new BigDecimal(avgPosition);
		        avg = x.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	        } else {
	        	avg = 0.0;
	        	avgPosition = 0.0;
	        }
        } catch (java.lang.NumberFormatException e) {
        	System.err.println("avg = " + avg);
        	System.err.println("sum = " + sum);
        	System.err.println("sumSize = " + sumSize);
        	e.printStackTrace();
		}
        final IntervalMarker avgLoad = new IntervalMarker(avgPosition, avgPosition);
        avgLoad.setLabel("Среднее = " + avg);
        avgLoad.setLabelPaint(new Color(31, 5, 225));
        avgLoad.setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        avgLoad.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        avgLoad.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        avgLoad.setPaint(Color.green);//new Color(222, 222, 255, 128)
        plot.addRangeMarker(avgLoad, Layer.FOREGROUND);

		JFreeChart chart = new JFreeChart(plot);
		chart.setTitle("Нагрузка сервера " + names[0] + " " + names[1]);
		return chart;
	}
}
