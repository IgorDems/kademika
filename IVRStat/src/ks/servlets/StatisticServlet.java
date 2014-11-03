package ks.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.keypoint.PngEncoder;
import ks.common.factory.StatisticFactory;

@SuppressWarnings("serial")
/**
 * 	StatisticServlet.java - ������� ��� ��������� ��������� � ���� ��������
 *  @author sergey.voloshin
 *  @version 1.0
 */
public class StatisticServlet extends HttpServlet {
	
	
	/**
	 * ����� ���������� ��������� ��� �������� 
	 * 
	 * @param req <code>HttpServletRequest</code>
	 * @param resp <code>HttpServletResponse</code>
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		resp.setContentType("image/png");
		ServletOutputStream out = resp.getOutputStream();
		new StatisticFactory();
		new StatisticFactory();
		final PngEncoder encoder = StatisticFactory.proccessRequest(req); 
		out.write( encoder.pngEncode() );
		out.close();
	}

	/**
	 * ����� �������������� POST ������ �� ���������  
	 * 
	 * @param req <code>HttpServletRequest</code>
	 * @param resp <code>HttpServletResponse</code>
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}
