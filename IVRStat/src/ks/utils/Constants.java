package ks.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;

/**
 * 	Constants.java - ����� �� ������������ ����������� � �������� ��� web-�������
 *  @author sergey.voloshin
 *  @version 1.0
 *  @see #getUrl(HttpServletRequest)
 *  @see #getTimes()
 *  @see #getStartDate()
 *  @see #getEndDate()
 *  @see #getParams(HttpServletRequest, String)
 *  @see #getDirectionParams(HttpServletRequest, String, String)
 *  @see #URL
 *  @see #TRUNK
 *  @see #PARAMS
 *  @see #TIMES
 *  @see #sdf
 *  @see #PORT_SEPARATOR
 *  @see #PARAMS_START
 *  @see #PARAMS_SEPARATOR
 *  @see #PARAMS
 *  @see #PARAM_TYPE
 *  @see #PARAM_TRUNK
 *  @see #PARAM_TO
 *  @see #PARAM_NAME
 *  @see #PARAM_GROUPING
 *  @see #PARAM_FROM
 *  @see #HTTP
 *  @see #CONTEXT_SEPARATOR
 */
public class Constants {

	private Constants() {
	}
	
	/**
	 * ��������� ����� �������� ������
	 */
	public static final String TRUNK = "trunk";

	/**
	 * ������� �������� URL ����������
	 */
	private static StringBuffer URL = null;
	
	/**
	 * ������� ���������
	 */
	private static StringBuffer PARAMS = null;
	
	/**
	 * �������� ��������� ����������� 
	 */
	private static ArrayList<String> TIMES = null;
	
	/**
	 * ��������� ������ ������
	 */
	private static final String HTTP = "http://";
	
	/**
	 * ����������� ����������
	 */
	private static final String CONTEXT_SEPARATOR = "/";
	
	/**
	 * ����������� �����
	 */
	private static final String PORT_SEPARATOR = ":";
	
	/**
	 * ����������� ���������� �������
	 */
	private static final String PARAMS_START = "?";
	
	/**
	 * ����������� ���������� �������
	 */
	private static final String PARAMS_SEPARATOR = "&";
	
	/**
	 * �������� ��������� ��� ���������� 
	 */
	private static final String PARAM_TYPE = "type=";
	
	/**
	 * �������� ��������� �������� �������
	 */
	private static final String PARAM_NAME = "name=";
	
	/**
	 * �������� ��������� ������� ������ ���������� 
	 */
	private static final String PARAM_FROM = "from=";
	
	/**
	 * �������� ��������� ������� ��������� ���������� 
	 */
	private static final String PARAM_TO = "to=";
	
	/**
	 * �������� ��������� �������� ������
	 */
	private static final String PARAM_TRUNK = "trunkName=";
		
	/**
	 * �������� ��������� ���� ����������� 
	 */
	private static final String PARAM_GROUPING = "grouping=";
	
	/**
	 * ��������������� ���� � ������ � ��������� ������� 
	 */
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy/HH:mm");
	
	/**
	 * ����� ������������ ��� ��������� �������� ������������ WEB-���������� � ������ context path-�
	 * 
	 * @param request HttpServletRequest ��������
	 * @return ������ �������� url-������� ���������� 
	 */
	public static String getUrl(HttpServletRequest request) {
		if (URL == null) {
			URL = new StringBuffer();
			final String serverName = request.getServerName();
			final int serverPort = request.getServerPort();
			final String contextPath = request.getContextPath();

			URL.append(HTTP).append(serverName).append(PORT_SEPARATOR).append(
					serverPort).append(contextPath).append(CONTEXT_SEPARATOR);
		}
		return URL.toString();
	}

	/**
	 * ����� ������������ ��� ��������� ������ ���������� � ������������ ��������������� ������
	 * 
	 * @param request HttpServletRequest ��������
	 * @param trunkName ������������� ������
	 * @return ������ ���������� ��� �������� 
	 */
	public static String getParams(HttpServletRequest request, String trunkName) {
		PARAMS = new StringBuffer();
		PARAMS.append(PARAMS_START).append(PARAM_TYPE).append(
				request.getParameter("type")).append(PARAMS_SEPARATOR).append(
				PARAM_NAME).append(request.getParameter("name")).append(
				PARAMS_SEPARATOR).append(PARAM_FROM).append(
				request.getParameter("from")).append(PARAMS_SEPARATOR).append(
				PARAM_TO).append(request.getParameter("to")).append(
				PARAMS_SEPARATOR).append(PARAM_GROUPING).append(
				request.getParameter("grouping")).append(PARAMS_SEPARATOR)
				.append(PARAM_TRUNK).append(trunkName);
		return PARAMS.toString();
	}

	/**
	 * ����� ������������ ��� ��������� ������ ���������� 
	 * � ������������ ��������������� ������
	 * � ��������� �������
	 * 
	 * @param request HttpServletRequest ��������
	 * @param trunkName ������������� ������
	 * @return ������ ���������� ��� �������� 
	 */
	public static String getDirectionParams(HttpServletRequest request,
			String serverName, String trunkName) {
		PARAMS = new StringBuffer();
		PARAMS.append(PARAMS_START).append(PARAM_TYPE).append(
				request.getParameter("type")).append(PARAMS_SEPARATOR).append(
				PARAM_NAME).append(serverName).append(PARAMS_SEPARATOR).append(
				PARAM_FROM).append(request.getParameter("from")).append(
				PARAMS_SEPARATOR).append(PARAM_TO).append(
				request.getParameter("to")).append(PARAMS_SEPARATOR).append(
				PARAM_GROUPING).append(request.getParameter("grouping"))
				.append(PARAMS_SEPARATOR).append(PARAM_TRUNK).append(trunkName);
		return PARAMS.toString();
	}

	/**
	 * ����� ���������� �������� ��������� �����������
	 * 
	 * @return �������� ��������� �����������
	 */
	public static ArrayList<String> getTimes() {
		if (TIMES == null) {
			TIMES = new ArrayList<String>();
			int hour = 0;
			int minutes = 0;

			while (hour < 24) {
				StringBuffer time = new StringBuffer();
				time.append(("" + hour).length() < 2 ? ("0" + hour) : hour);
				time.append(":");
				time.append(("" + minutes).length() < 2 ? ("0" + minutes)
						: minutes);
				TIMES.add(time.toString());

				minutes += 15;
				if (minutes > 59) {
					hour++;
					minutes = 0;
				}
			}
		}
		return TIMES;
	}

	/**
	 * ����� ���������� ���� ������ ���������� �� ���������
	 * 
	 * @return ���� ������ ���������� �� ���������
	 */
	public static String getStartDate() {
		Calendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		String res = sdf.format(calendar.getTime());
		return res;
	}

	/**
	 * ����� ���������� ���� ��������� ���������� �� ���������
	 * 
	 * @return ���� ��������� ���������� �� ���������
	 */
	public static String getEndDate() {
		Calendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.setTime(new Date());
		String res = sdf.format(calendar.getTime());
		return res;
	}
}
