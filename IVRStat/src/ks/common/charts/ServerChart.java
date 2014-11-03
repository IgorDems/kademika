package ks.common.charts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ks.common.interfaces.IChart;
import ks.db.OracleConnection;
import ks.db.utils.Order;
import ks.db.utils.SQLQuery;
import ks.db.utils.WhereClause;
import ks.utils.Constants;

/**
 * 	ServerChart.java - класс для получения данных о количестве потоков 
 * и их идентификаторах для статистики по серверам 
 *  @author sergey.voloshin
 *  @version 1.0
 *  @see ServerChart#getCharts(String)
 *  @see OracleConnection
 *  @see SQLQuery
 *  @see IChart
 */
public class ServerChart implements IChart{

	/**
	 * Метод для получения переченя потоков и их идентификаторах для сервера 
	 * 
	 * @param serverName Идентификатор сервера
	 * @return Перечень потоков и их идентификаторах для сервера
	 */
	@Override
	public ArrayList<String> getCharts(String serverName) {
		final ArrayList<String> charts = new ArrayList<String>();
		try {
			Connection connection = OracleConnection.getInstanse().getConnection();
			Statement statement = connection.createStatement();
			SQLQuery query = new SQLQuery();
			query.setTableName("rscc.imchargedump");
			query.addWhereClause("ivrserverid", WhereClause.EQUALS, serverName);
			query.addWhereClause("rownum", WhereClause.LESS, 2);
			query.addOrder("datestrob", Order.DESC);
			ResultSet set = statement.executeQuery(query.toString());
			ResultSetMetaData metaData = set.getMetaData();
			
			int count = metaData.getColumnCount();
			if(set.next())
			for(int i = 1; i <= count; i++){
				String columnName;
				if((columnName = metaData.getColumnName(i).toLowerCase()).contains(Constants.TRUNK.toLowerCase().intern())){
					if(set.getString(columnName) != null && set.getString(columnName).length() > 0){
						charts.add(charts.size(), columnName);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return charts;
	}

}
