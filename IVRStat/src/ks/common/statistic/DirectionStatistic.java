package ks.common.statistic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import ks.common.interfaces.IStatisticManager;
import ks.db.OracleConnection;
import ks.db.utils.Grouping;
import ks.db.utils.Order;
import ks.db.utils.SQLQuery;
import ks.db.utils.WhereClause;

/**
 * 	DirectionStatistic.java - ����� ��� ��������� ���������� �� �����������
 *  @author sergey.voloshin
 *  @version 1.0
 *  @see IStatisticManager
 *  @see OracleConnection
 *  @see SQLQuery
 */
public class DirectionStatistic implements IStatisticManager {

	
	/**
	 * ����� ������� SQL-������ �� ��������� ������ �� ��,
	 * ������������ ����� �� � ��������� HashMap<Date, Double>
	 * � ������� � �������� ������ � �������� �� ��������
	 * 
	 * @param serverName ������������� �������
	 * @param trunkName ������������� ������
	 * @param from ��������� ����� ����������
	 * @param to �������� ����� ����������
	 * @param grouping ��� ����������� ���������� 
	 * @return HashMap<Date, Double> � ������� � �������� ������ � �������� �� ��������
	 * @see Grouping
	 */
	@Override
	public HashMap<Date, Double> proccess(String serverName, String trunkName,
			Date from, Date to, Grouping grouping) {
		final HashMap<Date, Double> results = new HashMap<Date, Double>();
		SQLQuery query = new SQLQuery();
		try {
			Connection connection = OracleConnection.getInstanse().getConnection();
			Statement statement = connection.createStatement();
			query.setTableName("rscc.imchargedump");
			query.addSelect(trunkName, "trunk");
			query.addSelect("datestrob", "datestrob");
			query.addWhereClause("ivrserverid", WhereClause.EQUALS, serverName);
			if(grouping.equals(Grouping.NONE)){
				query.addWhereClause("datestrob", WhereClause.GREATER, from);
				query.addWhereClause("datestrob", WhereClause.LESS, to);
			} else {
				query.setStartDate(from);
				query.setEndDate(to);
				query.setDateColumn("datestrob");
			}
			query.addGrouping(grouping);
			query.addOrder("datestrob", Order.ASC);
			ResultSet set = statement.executeQuery(query.toString());
			while(set.next()){
				Double s = set.getDouble("trunk");
				Date date = new Date(set.getTimestamp("datestrob").getTime());
				results.put(date, s == null ? 0.0 : s);
			}
			statement.close();
		} catch (SQLException e) {
			System.out.println(query.toString());
			e.printStackTrace();			
		}
		return results;
	}
}
