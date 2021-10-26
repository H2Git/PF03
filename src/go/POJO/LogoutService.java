package go.POJO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import go.INTER.Command;

public class LogoutService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("vo");
		session.removeAttribute("friend_list");
		session.removeAttribute("menulist");
		session.removeAttribute("village");
		session.removeAttribute("restaurantlist");
		session.removeAttribute("restaurant_id");
		session.removeAttribute("find_user");
		session.removeAttribute("exixt");
		session.removeAttribute("r_evaluation");
		
		

		return "main.jsp";
	}
}
