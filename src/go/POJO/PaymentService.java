package go.POJO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import go.DAO.deliveryDAO;
import go.INTER.Command;
import go.VO.userVO;

public class PaymentService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		userVO vo = (userVO) session.getAttribute("vo");
		
		deliveryDAO dao = new deliveryDAO();
		dao.payment(vo.getId());
		userVO uservo = dao.login(vo.getId(), vo.getPw());
		session.setAttribute("vo", uservo);
		session.removeAttribute("menulist");
		session.removeAttribute("cnt");
		session.removeAttribute("orderlistarr");
		session.removeAttribute("menuorderlistarr");
		session.removeAttribute("totalprice");
		session.removeAttribute("ordercomplete_cnt");

		return "main.jsp";
	}

}
