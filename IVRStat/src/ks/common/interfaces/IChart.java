package ks.common.interfaces;

import java.util.ArrayList;

/**
 * 	IChart.java - интерфейс данных о количестве потоков 
 *  и их идентификаторах для статистики
 *  @author sergey.voloshin
 *  @version 1.0
 */
public interface IChart {
	public ArrayList<String> getCharts(String name);
}
