package ks.db.utils;

/**
 * 	Order.java - �������� ��������� ����� ����������
 *  @author sergey.voloshin
 *  @version 1.0
 */
public enum Order {
	ASC("asc"), DESC("desc");
	
	/**
	 * ��������� ����������� ��� �� Oracle
	 */
	private String order;
	
	
	private Order(String _order) {
		order = _order;
	}
	
	/**
	 * ����� ��� ��������� ���������� ����������� ��� �� Oracle
	 * @return ��������� ����������� ��� �� Oracle
	 */
	@Override
	public String toString() {
		return order;
	}
}
