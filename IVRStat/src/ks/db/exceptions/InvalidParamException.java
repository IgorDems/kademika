package ks.db.exceptions;

import java.sql.SQLException;

@SuppressWarnings("serial")
/**
 * 	InvalidParamException.java - ������ ������������� ���������� ������� 
 *  @author sergey.voloshin
 *  @version 1.0
 */
public class InvalidParamException extends SQLException {

	public InvalidParamException() {
		super();
	}
	
	public InvalidParamException(String throwable) {
		super(throwable);
	}
}
