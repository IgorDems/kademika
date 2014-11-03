package ks.db.utils;

/**
 * 	IChart.java - перечень возможных типов сравнения данных
 *  @author sergey.voloshin
 *  @version 1.0
 */
public enum WhereClause {
	EQUALS("="), GREATER(">"), LESS("<");
	
	/**
	 * Строковое обозначение для БД Oracle
	 */
	private String clause;
	
	private WhereClause(String _clause){
		clause = _clause;
	}
	
	/**
	 * Метод для получения строкового обозначение для БД Oracle
	 * @return Строковое обозначение для БД Oracle
	 */
	@Override
	public String toString() {
		return clause;
	}
}
