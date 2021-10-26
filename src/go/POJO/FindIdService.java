package go.POJO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import go.DAO.deliveryDAO;
import go.INTER.Command;
import go.VO.userVO;

public class FindIdService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ģ��ã�� ����");
		String find_id = request.getParameter("find_id");
		
		deliveryDAO dao = new deliveryDAO();
		userVO find_user = dao.findId(find_id);
		HttpSession session = request.getSession();
		String exist = "����";
		if (find_user!=null) {
			exist = "����";
			session.setAttribute("exist", exist);
			session.setAttribute("find_user", find_user);
		}else {
			session.setAttribute("exist", exist);
			
		}
		
		return "menu_invite.jsp";
	}

}
