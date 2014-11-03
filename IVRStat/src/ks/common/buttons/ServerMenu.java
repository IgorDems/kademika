package ks.common.buttons;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ks.common.interfaces.IMenu;
import ks.db.OracleConnection;
import ks.db.utils.SQLQuery;

/**
 * 	ServerMenu.java - класс для получения структуры меню 
 * для статистики по серверам
 *  @author sergey.voloshin
 *  @version 1.0
 *  @see ServerMenu#getButtons()
 *  @see OracleConnection
 *  @see SQLQuery
 *  @see IMenu
 */
public class ServerMenu implements IMenu {

	/**
	 * Метод для получения названия кнопок и идентификаторов
	 * @return Перечень названий кнопок и идентификаторов
	 * @see OracleConnection
 	 * @see SQLQuery
 	 */
	@Override
	public ArrayList<String[]> getButtons() {
		final ArrayList<String[]> buttons = new ArrayList<String[]>();
		try {
			final Connection connection = OracleConnection.getInstanse().getConnection();
			final Statement statement = connection.createStatement();
			SQLQuery query = new SQLQuery();
			query.setTableName("ivr_report.dir_ivr_ivrservers");
			query.addSelect("ivrname", "name");
			query.addSelect("active", "active");
			ResultSet set = statement.executeQuery(query.toString());
			while(set.next()){
				String name = set.getString("name");
				String active = set.getString("active");
				buttons.add(buttons.size(), new String[]{/*uin, */name, active});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buttons;
	}
	
	
}
