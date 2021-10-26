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

public class FriendlistService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		HttpSession session = request.getSession();
		userVO vo = (userVO)session.getAttribute("vo");
		deliveryDAO dao = new deliveryDAO();
		ArrayList<friendlistVO> arr = dao.friendget(vo.getId());
		
		session.setAttribute("friend_list", arr);
		
		return "friend.jsp";
	}

}
