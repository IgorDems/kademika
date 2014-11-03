package ks.common.interfaces;

/**
 * 	SQL.java - Интерфейс статических переменный
 *  для формирования SQL-запроса 
 *  @author Demchenko
 *  @version 1.0
 *  @see SQL#SELECT
 *  @see SQL#FROM
 *  @see SQL#WHERE
 *  @see SQL#UNION_ALL
 *  @see SQL#ORDER
 *  @see SQL#TO_DATE
 *  @see SQL#BETWEEN
 *  @see SQL#AS
 *  @see SQL#AND
 *  @see SQL#AVG
 *  @see SQL#DIRECTION_SELECT
 *  @see SQL#ROW
 *  @see SQL#SPACE
 *  @see SQL#ZPT  
 */
public interface SQL {

	/**
	 * Определение операции SELECT
	 */
	public static final String SELECT = "select";
	
	/**
	 * Определение таблицы
	 */
	public static final String FROM = "from";
	
	/**
	 * Условие
	 */
	public static final String WHERE = "where";
	
	/**
	 * Объдинение запросов
	 */
	public static final String UNION_ALL = "union all";
	
	/**
	 * Группирока
	 */
	public static final String ORDER = "order by";
	
	/**
	 * Определение названия
	 */
	public static final String AS = "as";
	
	/**
	 * Пробел
	 */
	public static final String SPACE = " ";
	
	/**
	 * Конец строки 
	 */
	public static final String ROW = "\n";
	
	/**
	 * Выбор всех параметров
	 */
	public static final String ALL = "*";
	
	/**
	 * Конъюнкция
	 */
	public static final String AND = "and";
	
	/**
	 * Условие BETWEEN
	 */
	public static final String BETWEEN = "between (1? , 2?)";
	
	/**
	 * Запятая
	 */
	public static final String ZPT = ",";
	
	/**
	 * Определение среднего зачения
	 */
	public static final String AVG = "NVL(AVG(NVL(?,0)),0)";
	
	/**
	 * Преобоазование в дату
	 */
	public static final String TO_DATE = "cast(to_timestamp(?, 'DD-MM-YYYY HH24:MI:SS.FF')as date)";
	
	/**
	 * Запрос на получение данных для направлений
	 */
	public static final String DIRECTION_SELECT = "select i.ivrname as name,ip.prin as prin"
			+ " from ivr_report.dir_ivr_msc_pri mp,"
			+ " ivr_report.dir_ivr_mcs_2_ivr mi,ivr_report.dir_ivr_IVRservers i,"
			+ " ivr_report.dir_ivr_pri ip"
			+ " where mp.route_id=? and"
			+ " mp.msc_pri_id=mi.msc_pri_id"
			+ " and mi.ivr_pri_id=ip.ivr_pri_id and ip.ivr_id=i.ivr_id"
			+ " order by mp.routeorder_id";
}
