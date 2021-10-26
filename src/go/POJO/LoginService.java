package go.POJO;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import go.DAO.deliveryDAO;
import go.INTER.Command;
import go.VO.friendlistVO;
import go.VO.userVO;

public class LoginService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("로그인서비스");
		request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		System.out.println();

		System.out.println(id);
		System.out.println(pw);
		deliveryDAO dao = new deliveryDAO();
		userVO vo = dao.login(id, pw);
		String page = "login.jsp";
		
		if (vo != null) {
			page = "main.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("vo", vo);
			session.getAttribute(vo.getId());
			System.out.println(vo.toString());
			System.out.println("서비스에서 로그인성공");
			ArrayList<friendlistVO> arr = dao.friendget(vo.getId());
			session.setAttribute("friend_list", arr);
		} else {
			System.out.println("로그인실패");

		}
		return page;
	}
}