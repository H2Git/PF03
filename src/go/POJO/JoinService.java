package go.POJO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import go.DAO.deliveryDAO;
import go.INTER.Command;

public class JoinService implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("username");
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		String tel = request.getParameter("tel");

		System.out.println("id : " + id);
		System.out.println("pw : " + pw);
		System.out.println("name : " + name);
		System.out.println("birth : " + birth);
		System.out.println("gender : " + gender);
		System.out.println("tel : " + tel);
		deliveryDAO dao = new deliveryDAO();
		int cnt = dao.join(id, pw, name, birth, gender, tel);

		if (cnt > 0) {
			System.out.println("입력성공");
		}
		return "login.jsp";
	}

}
