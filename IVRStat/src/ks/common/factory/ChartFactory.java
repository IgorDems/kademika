package ks.common.factory;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import ks.common.charts.DirectionChart;
import ks.common.charts.ServerChart;
import ks.common.interfaces.IChart;
import ks.common.interfaces.Types;

/**
 * 	ChartFactory.java - класс для распределиния обращения
 * на получение данных о количестве потоков 
 * и их идентификаторах в зависимости от типа статистики
 *  @author sergey.voloshin
 *  @version 1.0
 *  @see ChartFactory#proccessRequest(HttpServletRequest)
 *  @see DirectionChart
 *  @see ServerChart
 *  @see Types  
 */
public class ChartFactory implements Types {

	/**
	 * Метод распределяет обращения по типу статистики
	 * и возвращает перечень данных о потоках
	 * 
	 * @param request HttpServletRequest с параметрами запроса
	 * @return Перечень данных о потоках
	 */
	public static ArrayList<String> proccessRequest(HttpServletRequest request) {
		final int TYPE = request.getParameter("type") != null ? Integer
				.parseInt(request.getParameter("type")) : 0;
		final IChart chart;
		final ArrayList<String> charts;
		switch (TYPE) {
		case SERVER:
			chart = new ServerChart();
			charts = chart.getCharts(request.getParameter("name"));
			break;
		case DIRECTION:
			chart = new DirectionChart();
			charts = chart.getCharts(request.getParameter("name"));
			break;
		default:
			charts = new ArrayList<String>();
			break;
		}
		return charts;
	}
}
