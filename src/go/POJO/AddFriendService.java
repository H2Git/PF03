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

public class AddFriendService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		HttpSession session = request.getSession();
		userVO vo = (userVO) session.getAttribute("vo");
		String add_friend_id = request.getParameter("add_friend_id");
		deliveryDAO dao = new deliveryDAO();
		ArrayList<friendlistVO> arr = dao.addfriend(vo.getId(),add_friend_id);
		session.setAttribute("friend_list", arr);
		session.removeAttribute("exist");
		session.removeAttribute("find_user");

		return "menu_invite.jsp";
	}

}
