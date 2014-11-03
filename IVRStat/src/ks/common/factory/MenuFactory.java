package ks.common.factory;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import ks.common.buttons.DirectionMenu;
import ks.common.buttons.ServerMenu;
import ks.common.interfaces.IMenu;
import ks.common.interfaces.Types;

/**
 * 	MenuFactory.java - класс для распределиния обращения
 * на получение структуры меню в зависимости от типа статистики
 *  @author sergey.voloshin
 *  @version 1.0
 *  @see MenuFactory#proccessRequest(HttpServletRequest)
 *  @see ServerMenu
 *  @see DirectionMenu
 *  @see Types
 */
public class MenuFactory implements Types  {

	/**
	 * Метод распределяет обращения по типу статистики
	 * и возвращает перечень структуру меню
	 * 
	 * @param request HttpServletRequest с параметрами запроса
	 * @return Структура меню
	 */
	public static ArrayList<String[]> proccessRequest(HttpServletRequest request){
		final int TYPE = request.getParameter("type") != null ? Integer.parseInt(request.getParameter("type")) : 0;
		final IMenu menu;
		final ArrayList<String[]> buttons;
		switch (TYPE) {
			case SERVER:
				menu = new ServerMenu();
				buttons = menu.getButtons();
				break;
			case DIRECTION:
				menu = new DirectionMenu();
				buttons = menu.getButtons();
				break;
			default:
				buttons = new ArrayList<String[]>();
				break;
		}
		return buttons;
	}
}
