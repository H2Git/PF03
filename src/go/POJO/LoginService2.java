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
import go.VO.userVO;

public class LoginService2 implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("로그인서비스");
		request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.out.println(id);
		System.out.println(pw);
		
		deliveryDAO dao = new deliveryDAO();
		userVO vo = dao.login(id, pw);
		String page = "login2.jsp";
		
		if (vo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("vo", vo);
			String restaurant_id = (String)session.getAttribute("restaurant_id");
			String code_id = (String)session.getAttribute("code_id");
			System.out.println(restaurant_id);
			System.out.println(code_id);
			
			// 로그인 성공하면 식당아이디로 메뉴가져오기
			ArrayList<menulistVO> arr = dao.getmenu(restaurant_id);
			session.setAttribute("menulist", arr);
			page = "menuchoice2.jsp";
		} else {
			System.out.println("로그인실패");

		}
		return page;
	}

}
