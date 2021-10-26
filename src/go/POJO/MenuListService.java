package go.POJO;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import go.DAO.deliveryDAO;
import go.INTER.Command;
import go.VO.menulistVO;
import go.VO.r_evaluationVO;

public class MenuListService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String restaurant_id = request.getParameter("restaurant_id");
		
		System.out.println(restaurant_id);
		deliveryDAO dao = new deliveryDAO();
		
		// 추가 1
		r_evaluationVO r_evo = dao.restaurant_info(restaurant_id);
		System.out.println(r_evo.getRESTAURANT_NAME());
		ArrayList<menulistVO> arr = dao.getmenu(restaurant_id);
		HttpSession session = request.getSession();
		session.setAttribute("restaurant_id", restaurant_id);
		// 추가 1
		session.setAttribute("r_evaluation", r_evo);
		session.setAttribute("menulist", arr);
		
		return "menu_invite.jsp";
	}

}