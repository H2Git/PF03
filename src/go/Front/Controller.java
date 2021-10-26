package go.Front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import go.INTER.Command;
import go.POJO.AddFriendService;
import go.POJO.ChargeService;
import go.POJO.ChargeService2;
import go.POJO.FindIdService;
import go.POJO.InviteService;
import go.POJO.JoinService;
import go.POJO.LoginService;
import go.POJO.LoginService2;
import go.POJO.LogoutService;
import go.POJO.MenuListService;
import go.POJO.MenuSaveService;
import go.POJO.MenuSaveService2;
import go.POJO.PaymentService;
import go.POJO.RestaurantService;
import go.POJO.VillageService;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(".do페이지입니다.");
		request.setCharacterEncoding("euc-kr");
		String reqURI = request.getRequestURI();

		System.out.println(reqURI);

		String path = request.getContextPath();
		System.out.println(path);

		String resultURI = reqURI.substring(path.length() + 1);
		System.out.println(resultURI);
		String nextpage = "";

		Command com = null;

		if (resultURI.equals("VillageCon.do")) {
			com = new VillageService();

		} else if (resultURI.equals("JoinCon.do")) {
			com = new JoinService();

		} else if (resultURI.equals("LoginCon.do")) {
			com = new LoginService();

		} else if (resultURI.equals("LoginCon2.do")) {
			com = new LoginService2();

		} else if (resultURI.equals("LogoutCon.do")) {
			com = new LogoutService();

		} else if (resultURI.equals("RestaurantCon.do")) {
			com = new RestaurantService();

		} else if (resultURI.equals("MenuListCon.do")) {
			com = new MenuListService();

		} else if (resultURI.equals("FindIdCon.do")) {
			com = new FindIdService();

		} else if (resultURI.equals("AddFriendCon.do")) {
			com = new AddFriendService();

		} else if (resultURI.equals("InviteCon.do")) {
			com = new InviteService();

		} else if (resultURI.equals("MenuSaveCon.do")) {
			com = new MenuSaveService();

		} else if (resultURI.equals("MenuSaveCon2.do")) {
			com = new MenuSaveService2();

		} else if (resultURI.equals("PaymentCon.do")) {
			com = new PaymentService();

		} else if (resultURI.equals("ChargeCon.do")) {
			com = new ChargeService();

		} else if (resultURI.equals("ChargeCon2.do")) {
			com = new ChargeService2();

		}
		
		
		
		
		
		nextpage = com.execute(request, response);

		RequestDispatcher dis = request.getRequestDispatcher(nextpage);
		dis.forward(request, response);
	}

}
