package go.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import go.DAO.deliveryDAO;
import go.VO.orderlistVO;
import go.VO.userVO;

/**
 * Servlet implementation class RefreshAjax
 */
@WebServlet("/RefreshAjax")
public class RefreshAjax extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		userVO vo = (userVO)session.getAttribute("vo");
		deliveryDAO dao = new deliveryDAO();
		JsonArray jarr = new JsonArray();
		
		ArrayList<orderlistVO> orderlistarr = dao.getorderlist(vo.getId());
		String order_count = dao.ordercomplete(vo.getId());
		System.out.println("리프레시 : " + order_count);
		for (int i = 0; i < orderlistarr.size(); i++) {
			JsonObject sobj = new JsonObject();
			sobj.addProperty("order", orderlistarr.get(i).getOrderer());
			sobj.addProperty("menu_name", orderlistarr.get(i).getMenu_name());
			sobj.addProperty("price", orderlistarr.get(i).getPrice());
			sobj.addProperty("order_count", order_count);
			jarr.add(sobj);
			
		}
		JsonObject obj = new JsonObject();
		
		obj.add("check", jarr);
		
		
		
		
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();
		System.out.println(obj);
		out.print(obj);

	}

}