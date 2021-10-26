package go.POJO;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import go.DAO.deliveryDAO;
import go.INTER.Command;
import go.VO.restaurantlistVO;

public class RestaurantService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 에러 없는 서버
//		request.setCharacterEncoding("euc-kr");
//		HttpSession session = request.getSession();
//		
//		String village = (String)session.getAttribute("village");
//		System.out.println(village);
//		
//		deliveryDAO dao = new deliveryDAO();
//		ArrayList<restaurantlistVO> restaurantlist =dao.getrestaurant(village);
//		
//		if (restaurantlist!=null) {
//			session.setAttribute("restaurantlist", restaurantlist);
//		}
		
		return "restaurant.jsp";
	}

}
