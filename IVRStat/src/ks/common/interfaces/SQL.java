package ks.common.interfaces;

/**
 * 	SQL.java - ��������� ����������� ����������
 *  ��� ������������ SQL-������� 
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
	 * ����������� �������� SELECT
	 */
	public static final String SELECT = "select";
	
	/**
	 * ����������� �������
	 */
	public static final String FROM = "from";
	
	/**
	 * �������
	 */
	public static final String WHERE = "where";
	
	/**
	 * ���������� ��������
	 */
	public static final String UNION_ALL = "union all";
	
	/**
	 * ����������
	 */
	public static final String ORDER = "order by";
	
	/**
	 * ����������� ��������
	 */
	public static final String AS = "as";
	
	/**
	 * ������
	 */
	public static final String SPACE = " ";
	
	/**
	 * ����� ������ 
	 */
	public static final String ROW = "\n";
	
	/**
	 * ����� ���� ����������
	 */
	public static final String ALL = "*";
	
	/**
	 * ����������
	 */
	public static final String AND = "and";
	
	/**
	 * ������� BETWEEN
	 */
	public static final String BETWEEN = "between (1? , 2?)";
	
	/**
	 * �������
	 */
	public static final String ZPT = ",";
	
	/**
	 * ����������� �������� �������
	 */
	public static final String AVG = "NVL(AVG(NVL(?,0)),0)";
	
	/**
	 * �������������� � ����
	 */
	public static final String TO_DATE = "cast(to_timestamp(?, 'DD-MM-YYYY HH24:MI:SS.FF')as date)";
	
	/**
	 * ������ �� ��������� ������ ��� �����������
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
