package ks.db.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 	Grouping.java - перечень возможных типов группировки
 *  @author sergey.voloshin
 *  @version 1.0
 */
public enum Grouping {
	NONE(0,15, "", ""), MINUTE(1,1, "MI", "dd.MM.yyyy HH:mm"), HOUR(2,1, "HH", "dd.MM.yyyy HH"), DAY(3,1, "DD", "dd.MM.yyyy"), WEEK(4,1, "WW", "dd.MM.yyyy"), MONTH(5,1, "MM", "MM.yyyy");

	/**
	 * Идентификатор типа
	 */
	private int type;
	
	/**
	 * Количественный указатель интервала
	 */
	private int interval;
	
	/**
	 * Строковое обозначение для БД Oracle
	 */
	private String measure;
	
	/**
	 * Формат преобразования даты
	 */
	private String sdf;
	
	private Grouping(int _type, int _interval, String _measure, String _sdf) {
		type = _type;
		interval = _interval;
		measure = _measure;
		sdf = _sdf;
	}
	
	public final static Grouping GET_GROUPING(int _type){
		switch(_type){
		case 0:
			return NONE;
		case 1:
			return MINUTE;
		case 2:
			return HOUR;
		case 3:
			return DAY;
		case 4:
			return WEEK;
		case 5:
			return MONTH;
		default:
			return NONE;
		}
	}
	
	/**
	 * Метод для получения идентификатора типа
	 * @return Идентификатор типа
	 */
	public int getType() {
		int ret = 0;
		switch(type){
		case 0:
			ret = Calendar.SECOND;
			break;
		case 1:
			ret = Calendar.MINUTE;
			break;
		case 2:
			ret = Calendar.HOUR;
			break;
		case 3:
			ret = Calendar.DATE;
			break;
		case 4:
			ret = Calendar.WEEK_OF_MONTH;
			break;
		case 5:
			ret = Calendar.MONTH;
			break;
		}
		return ret;
	}
	
	/**
	 * Метод для получения количественного указателя интервала
	 * @return Количественный указатель интервала
	 */
	public int getInterval() {
		return interval;
	}
	
	/**
	 * Метод для получения строкового обозначение для БД Oracle
	 * @return Строковое обозначение для БД Oracle
	 */
	public String getMeasure() {
		return measure;
	}
	
	/**
	 * Метод для получения преобразователя даты
	 * @return Преобразователь даты
	 * @see SimpleDateFormat
	 */
	public SimpleDateFormat getSdf() {
		return new SimpleDateFormat(sdf);
	}
}
