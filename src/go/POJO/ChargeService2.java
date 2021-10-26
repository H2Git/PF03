package go.POJO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import go.DAO.deliveryDAO;
import go.INTER.Command;
import go.VO.userVO;

public class ChargeService2 implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		HttpSession session = request.getSession();
		userVO vo = (userVO)session.getAttribute("vo");
		System.out.println("차지포조성공"+vo.toString());
		String id = vo.getId();
		System.out.println(vo.getMoney());
		int totalmoney = vo.getMoney()+Integer.parseInt(request.getParameter("charge_money"));
		deliveryDAO dao = new deliveryDAO();
		vo = dao.charge(id, totalmoney);
		if (vo != null) {
			
			session.setAttribute("vo", vo);
		} else {
			System.out.println("로그인실패");

		}
		return "menuchoice2.jsp";
	}

}
