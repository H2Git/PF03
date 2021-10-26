package go.POJO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import go.INTER.Command;

public class VillageService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("������");
		request.setCharacterEncoding("euc-kr");
		
		String village = request.getParameter("village");
		System.out.println(village);
		HttpSession session = request.getSession();
		// �˻�â�� ������ �������� �������
		if (village != null) {
			session.setAttribute("village", village);

		} else {
			session.setAttribute("village", "");
		}

		return "main.jsp";
	}

}
