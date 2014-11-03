package ks.common.buttons;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ks.common.interfaces.IMenu;
import ks.db.OracleConnection;
import ks.db.utils.Order;
import ks.db.utils.SQLQuery;

/**
 * 	DirectionMenu.java - класс для получения структуры меню 
 * для статистики по направлениям 
 *  @author sergey.voloshin
 *  @version 1.0
 *  @see DirectionMenu#getButtons()
 *  @see OracleConnection
 *  @see SQLQuery
 *  @see IMenu
 */
public class DirectionMenu implements IMenu  {

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
			query.setTableName("ivr_report.dir_ivr_routes");
			query.addSelect("route_id", "id");
			query.addSelect("description", "name");
			query.addOrder("route_id", Order.ASC);
			ResultSet set = statement.executeQuery(query.toString());
			while(set.next()){
				String uin = set.getString("id");
				String name = set.getString("name");
				buttons.add(buttons.size(), new String[]{uin, name});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buttons;
	}

}
