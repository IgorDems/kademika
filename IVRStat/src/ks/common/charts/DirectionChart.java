package ks.common.charts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ks.common.interfaces.IChart;
import ks.common.interfaces.SQL;
import ks.db.OracleConnection;
import ks.db.utils.SQLQuery;

/**
 * 	DirectionChart.java - класс для получения данных о количестве потоков 
 * и их идентификаторах для статистики по направлениям 
 *  @author sergey.voloshin
 *  @version 1.0
 *  @see DirectionChart#getCharts(String)
 *  @see OracleConnection
 *  @see SQLQuery
 *  @see IChart
 */
public class DirectionChart implements IChart{

	/**
	 * Метод для получения переченя потоков и их идентификаторах для направления 
	 * 
	 * @param direction Идентификатор направления
	 * @return Перечень потоков и их идентификаторах для направления
	 * @see OracleConnection
	 * @see SQLQuery
	 */
	@Override
	public ArrayList<String> getCharts(String direction) {
		final ArrayList<String> charts = new ArrayList<String>();
		try {
			Connection connection = OracleConnection.getInstanse().getConnection();
			Statement statement = connection.createStatement();
			SQLQuery query = new SQLQuery(SQL.DIRECTION_SELECT.replace("?", direction));
			ResultSet set = statement.executeQuery(query.toString());
			while(set.next()){
				String name = set.getString("name");				
				String trunk = new StringBuffer("trunk").append(set.getString("prin").length() > 1 ? set.getString("prin") : ("0" + set.getString("prin"))).toString();			
				charts.add(charts.size(), name+"/"+trunk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return charts;
	}

}
