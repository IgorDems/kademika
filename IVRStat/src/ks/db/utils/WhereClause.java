package ks.db.utils;

/**
 * 	IChart.java - �������� ��������� ����� ��������� ������
 *  @author sergey.voloshin
 *  @version 1.0
 */
public enum WhereClause {
	EQUALS("="), GREATER(">"), LESS("<");
	
	/**
	 * ��������� ����������� ��� �� Oracle
	 */
	private String clause;
	
	private WhereClause(String _clause){
		clause = _clause;
	}
	
	/**
	 * ����� ��� ��������� ���������� ����������� ��� �� Oracle
	 * @return ��������� ����������� ��� �� Oracle
	 */
	@Override
	public String toString() {
		return clause;
	}
}
