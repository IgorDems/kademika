package ks.common.interfaces;

import java.util.Date;
import java.util.HashMap;

import ks.db.utils.Grouping;

/**
 * 	IStatisticManager.java - интерфейс статистики
 *  @author sergey.voloshin
 *  @version 1.0
 */
public interface IStatisticManager {
	public HashMap<Date, Double> proccess(String serverName, String trunkName, Date from, Date to, Grouping grouping); 
}
