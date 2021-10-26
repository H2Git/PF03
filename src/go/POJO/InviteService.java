package go.POJO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import go.INTER.Command;
import go.VO.userVO;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class InviteService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("euc-kr");
		HttpSession session = request.getSession();
		String[] invite_id = request.getParameterValues("invite_id");
		String restaurant_id = (String)session.getAttribute("restaurant_id");
		userVO vo = (userVO)session.getAttribute("vo");

		session.setAttribute("cnt", invite_id.length+1);
		String url = "http://127.0.0.1:8077/delivery_minsu_project/login2.jsp?restaurant_id=" + restaurant_id + "&code_id="+ vo.getId();

		String api_key = "NCS5APDFLEIRHXKZ";
		String api_secret = "4SVXO7UGAER7VST04NXTPMQ2TMMJE2RL";
		Message coolsms = new Message(api_key, api_secret);

		ArrayList<String> phone = new ArrayList<String>();
		for (int i = 0; i < invite_id.length; i++) {
			phone.add(invite_id[i]);
		}

		if (phone.size() > 0) {

			for (int i = 0; i < phone.size(); i++) {
				
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("to", phone.get(i));
				params.put("from", "01023998278");
				params.put("type", "SMS");
				params.put("text", url);
				params.put("app_version", "test app 1.2");

				try {
					JSONObject obj = (JSONObject) coolsms.send(params);
					System.out.println(obj.toString());
				} catch (CoolsmsException e) {

				}

			}
		}
		phone.clear();

		return "menuchoice.jsp";
	}

}
