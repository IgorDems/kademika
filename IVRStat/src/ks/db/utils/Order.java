package ks.db.utils;

/**
 * 	Order.java - перечень возможных типов сортировки
 *  @author sergey.voloshin
 *  @version 1.0
 */
public enum Order {
	ASC("asc"), DESC("desc");
	
	/**
	 * Строковое обозначение для БД Oracle
	 */
	private String order;
	
	
	private Order(String _order) {
		order = _order;
	}
	
	/**
	 * Метод для получения строкового обозначение для БД Oracle
	 * @return Строковое обозначение для БД Oracle
	 */
	@Override
	public String toString() {
		return order;
	}
}
