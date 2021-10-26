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

public class MenuSaveService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("�޴����̺� ���� ����");
		HttpSession session = request.getSession();
		request.setCharacterEncoding("euc-kr");
		userVO vo = (userVO) session.getAttribute("vo");
		String[] choice_menu = request.getParameterValues("choice_menu");
		deliveryDAO dao = new deliveryDAO();
		for (int i = 0; i < choice_menu.length; i++) {
			String[] menu_arr = choice_menu[i].split("333");
			System.out.println(menu_arr[0] + menu_arr[1]);
			// �ֹ��Ѹ޴��� �߰��ϰ� �ֹ���, �޴��̸�, ���� ������
			int success = dao.menusave(vo.getId(), vo.getId(), menu_arr);
			System.out.println(success);
		}

		System.out.println("���⼭���� �޴����̺�");

		System.out.println("���⼭���� �޴����̺� ��");

		// �޴��̸�, ����, �Ѱ��� ������
		// ��Ż ���� ������
		String totalprice = dao.totalprice(vo.getId());
		System.out.println(totalprice);

		// �� ����� �ֹ��ߴ��� ������

		return "orderlist.jsp";
	}

}
