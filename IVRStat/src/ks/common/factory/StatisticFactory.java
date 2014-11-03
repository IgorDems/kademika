package ks.common.factory;

import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import ks.common.ChartMacker;
import ks.common.interfaces.IStatisticManager;
import ks.common.interfaces.Types;
import ks.common.statistic.DirectionStatistic;
import ks.common.statistic.ServerStatistic;
import ks.db.utils.Grouping;

import org.jfree.chart.JFreeChart;

import com.keypoint.PngEncoder;

/**
 * 	StatisticFactory.java - класс для распределиния обращения
 * на получение статистики в зависимости от типа статистики
 *  @author sergey.voloshin
 *  @version 1.0
 *  @see StatisticFactory#proccessRequest(HttpServletRequest)
 *  @see ServerStatistic
 *  @see DirectionStatistic
 *  @see Types
 */
public class StatisticFactory implements Types {

	/**
	 * Метод обрабатывает параметры запроса,
	 * распределяет по типу статистики, 
	 * запускает обработку.
	 * По возврату данных статистики, инициализирует объект создания диаграммы <code>ChartMacker</code>,
	 * вызывает метод создания диаграммы и преобразовует созданную диграмму
	 * в объект с картинкой <code>PngEncoder</code> 
	 * 
	 * @param request <code>HttpServletRequest</code> с набором данных для обработки 
	 * @return <code>PngEncoder</code> объект с картинкой
	 * @see PngEncoder
	 * @see ChartMacker
	 * @see ChartMacker#getChart(HashMap, Grouping, String[])
	 * @see ServerStatistic
	 * @see DirectionStatistic
	 */
	public static final PngEncoder proccessRequest(HttpServletRequest request){
		final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy/HH:mm");
		int TYPE = request.getParameter("type") != null ? Integer.parseInt((String)request.getParameter("type")) : 0;
		HashMap<Date, Double> results = new HashMap<Date, Double>();
		IStatisticManager manager;
		ChartMacker macker;
		int groupingType = Integer.parseInt((String)request.getParameter("grouping"));
		Grouping grouping = Grouping.GET_GROUPING(groupingType);
		String serverName = (String)request.getParameter("name");
		String trunkName = (String)request.getParameter("trunkName");
		try{
			String from = request.getParameter("from");
			String to = request.getParameter("to");
			Date startDate = from.length() > 0 ? sdf.parse(from) : null;
			Date endDate = to.length() > 0 ? sdf.parse(to) : null;
			switch (TYPE) {
			case SERVER:
				manager = new ServerStatistic();
				results = manager.proccess(serverName, trunkName, startDate , endDate, grouping);
				break;
			case DIRECTION:
				manager = new DirectionStatistic();
				results = manager.proccess(serverName, trunkName, startDate , endDate, grouping);
				break;
			}	
			macker = new ChartMacker(startDate, endDate);
		} catch (ParseException e) {
			macker = new ChartMacker(new Date(), new Date());
			e.printStackTrace();
		} catch (java.lang.NumberFormatException e) {
			macker = new ChartMacker(new Date(), new Date());
			e.printStackTrace();
		} 
		
		
		JFreeChart chart = null;
		try {
			chart = macker.getChart(results, grouping, new String []{serverName, trunkName});
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		BufferedImage buf = chart.createBufferedImage(800, 400, null);
		PngEncoder encoder = new PngEncoder(buf, false, 0, 9);
		return encoder;
	}
}
