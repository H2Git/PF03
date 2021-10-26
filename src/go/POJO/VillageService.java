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
		
		System.out.println("빌리지");
		request.setCharacterEncoding("euc-kr");
		
		String village = request.getParameter("village");
		System.out.println(village);
		HttpSession session = request.getSession();
		// 검색창에 내용이 있을경우와 없을경우
		if (village != null) {
			session.setAttribute("village", village);

		} else {
			session.setAttribute("village", "");
		}

		return "main.jsp";
	}

}
