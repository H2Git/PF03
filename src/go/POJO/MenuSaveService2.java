package go.POJO;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import go.DAO.deliveryDAO;
import go.INTER.Command;
import go.VO.menuorderlistVO;
import go.VO.orderlistVO;
import go.VO.userVO;

public class MenuSaveService2 implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("메뉴세이브 서비스 들어옴");
		HttpSession session = request.getSession();
		request.setCharacterEncoding("euc-kr");
		String code_id = (String)session.getAttribute("code_id");
		userVO vo = (userVO) session.getAttribute("vo");
		String[] choice_menu = request.getParameterValues("choice_menu");
		deliveryDAO dao = new deliveryDAO();
		for (int i = 0; i < choice_menu.length; i++) {
			String[] menu_arr = choice_menu[i].split("333");
			System.out.println(menu_arr[0] + menu_arr[1]);
			// 주문한메뉴를 추가하고 주문자, 메뉴이름, 가격 가져옴
			int success = dao.menusave(code_id, vo.getId(), menu_arr);	
			System.out.println(success);
		}

		System.out.println("여기서부터 메뉴세이브");
		

		System.out.println("여기서부터 메뉴세이브 끝");

		// 메뉴이름, 갯수, 총가격 가져옴
		// 토탈 가격 가져옴
		session.removeAttribute("village");
		session.removeAttribute("restaurantlist");
		session.removeAttribute("restaurant_id");
		session.removeAttribute("find_user");
		session.removeAttribute("exixt");
		session.removeAttribute("r_evaluation");

		// 총 몇명이 주문했는지 가져옴

		return "main.jsp";
	}

}
