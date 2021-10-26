package go.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import go.VO.friendlistVO;
import go.VO.menulistVO;
import go.VO.menuorderlistVO;
import go.VO.orderlistVO;
import go.VO.payVO;
import go.VO.r_evaluationVO;
import go.VO.restaurantlistVO;
import go.VO.userVO;

public class deliveryDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	Statement csmt = null;
	int cnt = 0;

	// 서버통신메소드
	public void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
//			String dbid = "system";
//			String dbpw = "1234";
			 String dbid = "hr";
			 String dbpw = "hr";

			conn = DriverManager.getConnection(url, dbid, dbpw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 서버 닫기 메소드
	public void close() {
		try {
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원가입 메소드
	public int join(String id, String pw, String name, String birth, String gender, String tel) {

		try {
			getConn();

			String sql = "insert into a_delivery_user values(?,?,?,?,?,?,3000,sysdate)";
			System.out.println("오케이1");
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, pw);
			pst.setString(3, name);
			pst.setString(4, birth);
			pst.setString(5, gender);
			pst.setString(6, tel);

			cnt = pst.executeUpdate();
			System.out.println(cnt);
			System.out.println("오케이2");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// 로그인 메소드
	public userVO login(String id, String pw) {
		userVO vo = null;
		try {
			getConn();
			String sql = "select * from a_delivery_user where user_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String get_pw = rs.getString(2);
				String get_name = rs.getString(3);
				String get_birth = rs.getString(4);
				String get_gender = rs.getString(5);
				String get_tel = rs.getString(6);
				int get_money = Integer.parseInt(rs.getString(7));

				if (pw.equals(get_pw)) {
					vo = new userVO(id, get_pw, get_name, get_birth, get_gender, get_tel, get_money);
					System.out.println(vo.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	// 충전 메소드
	public userVO charge(String id, int totalmoney) {
		userVO vo = null;
		String sql = null;
		try {
			getConn();
			sql = "UPDATE A_DELIVERY_USER SET MONEY=? WHERE USER_ID = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, totalmoney);
			pst.setString(2, id);

			pst.executeUpdate();

			getConn();
			sql = "select * from a_delivery_user where user_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String get_pw = rs.getString(2);
				String get_name = rs.getString(3);
				String get_birth = rs.getString(4);
				String get_gender = rs.getString(5);
				String get_tel = rs.getString(6);
				int get_money = Integer.parseInt(rs.getString(7));

				vo = new userVO(id, get_pw, get_name, get_birth, get_gender, get_tel, get_money);
				System.out.println(vo.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	// 친구리스트 가져오기 메소드
	public ArrayList<friendlistVO> friendget(String id) {
		ArrayList<friendlistVO> arr = new ArrayList<friendlistVO>();
		friendlistVO vo = null;
		try {
			getConn();
			String sql = "SELECT USER_ID ,USER_NAME ,TEL FROM A_DELIVERY_USER WHERE USER_ID IN (SELECT FRIEND_ID FROM A_FRIEND_LIST WHERE USER_ID = ?) ORDER BY USER_NAME";
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			System.out.print("친구목록 디에이오");
			while (rs.next()) {

				String get_friend_id = rs.getString(1);
				String get_friend_name = rs.getString(2);
				String get_friend_tel = rs.getString(3);

				vo = new friendlistVO(get_friend_id, get_friend_name, get_friend_tel);
				arr.add(vo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return arr;
	}

	// 친구추가 메소드
	public ArrayList<friendlistVO> addfriend(String user_id, String friend_id) {
		try {
			getConn();
			String sql = "INSERT INTO A_FRIEND_LIST VALUES(?, ?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_id);
			pst.setString(2, friend_id);

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return friendget(user_id);
	}

	// 식당의 메뉴가져오는 메소드
	public ArrayList<menulistVO> getmenu(String restaurant_id) {
		ArrayList<menulistVO> arr = new ArrayList<menulistVO>();
		menulistVO vo = null;
		try {
			getConn();
			String sql = "SELECT menu_id, restaurant_name, menu_name, price, image_url, TASTE_GOOD_CNT,TASTE_BAD_CNT,AMOUNT_GOOD_CNT,AMOUNT_BAD_CNT FROM A_RESTAURANT_MENU WHERE RESTAURANT_ID =?";
			System.out.println(sql);
			System.out.println("restaurant_id:"+restaurant_id);
			pst = conn.prepareStatement(sql);
			pst.setString(1, restaurant_id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				String get_menu_id = rs.getString(1);
				String get_restaurant_name = rs.getString(2);
				String get_menu_name = rs.getString(3);
				String get_price = rs.getString(4);
				String get_image_url = rs.getString(5);
				int get_taste_good = rs.getInt(6);
				int get_taste_bad = rs.getInt(7);
				int get_amount_good = rs.getInt(8);
				int get_amount_bad = rs.getInt(9);

				vo = new menulistVO(get_menu_id, get_restaurant_name, get_menu_name, get_price, get_image_url,get_taste_good,get_taste_bad,get_amount_good,get_amount_bad);
				arr.add(vo);
			}
			System.out.println(arr.get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return arr;
	}

	// 동네별 식당 다긁어오는 메소드
	public ArrayList<restaurantlistVO> getrestaurant(String village) {
		ArrayList<restaurantlistVO> arr = new ArrayList<restaurantlistVO>();
		restaurantlistVO vo = null;
		System.out.println(village);

		try {
			getConn();
			String sql = "SELECT category, restaurant_id, restaurant_name FROM A_RESTAURANT_MENU WHERE VILLAGE = ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, village);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				String get_category = rs.getString(1);
				String get_restaurant_id = rs.getString(2);
				String get_restaurant_name = rs.getString(3);

				vo = new restaurantlistVO(get_category, get_restaurant_id, get_restaurant_name);
				arr.add(vo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return arr;
	}

	// 먹고싶은 메뉴 임시테이블에 추가하는 메소드
	public int menusave(String code_id, String user_id, String[] menu_arr) {
		String sql = null;
		int num=0;
		try {
			
			getConn();
			sql = "INSERT INTO A_DELIVERY_LIST VALUES (?,?,?,?,sysdate)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, code_id);
			pst.setString(2, user_id);
			pst.setString(3, menu_arr[0]);
			pst.setInt(4, Integer.parseInt(menu_arr[1]));

			pst.executeUpdate();
			num = 1;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return num;
	}

	// 주문자, 메뉴이름, 가격 정보가져오는 메소드
	public ArrayList<orderlistVO> getorderlist(String code_id) {
		ArrayList<orderlistVO> orderlistarr = new ArrayList<orderlistVO>();
		orderlistVO orderlistvo = null;
		System.out.println("getorderlist : "+code_id);
		try {

			getConn();
			String sql = "SELECT adu.USER_NAME ,arm.MENU_NAME ,adl.PRICE price\r\n"
					+ "FROM A_DELIVERY_LIST adl, A_RESTAURANT_MENU arm, A_DELIVERY_USER adu \r\n"
					+ "WHERE adl.CODE_ID = ? AND adl.menu_id=arm.MENU_ID AND adl.USER_ID = adu.USER_ID";
			pst = conn.prepareStatement(sql);
			pst.setString(1, code_id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String get_user_name = rs.getNString(1);
				String get_menu_name = rs.getString(2);
				String get_price = rs.getString(3);

				orderlistvo = new orderlistVO(get_user_name, get_menu_name, get_price);
				orderlistarr.add(orderlistvo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return orderlistarr;
	}

	// 뷰 생성 메소드 (메뉴이름별 메소드를 위해 생성)
//	public void createview(String code_id) {
//
//		try {
//
//			getConn();
//
//			String sql = "CREATE VIEW " + code_id+ " AS SELECT MENU_ID, price , COUNT(MENU_ID) cnt,sum(price) total FROM A_DELIVERY_LIST WHERE CODE_ID = '"
//					+ code_id + "' GROUP BY MENU_ID,price";
//
//			csmt = conn.createStatement();
//
//			csmt.executeUpdate(sql);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//
//	}

	// 뷰 삭제 메소드(결제 완료 후 진행)
	public void deleteview(String code_id) {

		try {

			getConn();

			String sql = "DROP VIEW " + code_id + "";
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	// 처음 리더가 메뉴세이브하고 메뉴이름별 카운트, 가격 메소드
//	public ArrayList<menuorderlistVO> menuorderlist(String code_id) {
//		ArrayList<menuorderlistVO> menuorderlistarr = new ArrayList<menuorderlistVO>();
//		menuorderlistVO menuorderlistVO = null;
//		String sql = null;
//		try {
//			createview(code_id);
//
//			getConn();
//
//			sql = "SELECT arm.menu_name, " + code_id + ".price, cnt, total FROM A_RESTAURANT_MENU arm," + code_id
//					+ " WHERE arm.MENU_ID = " + code_id + ".MENU_ID";
//			System.out.println(sql);
//			pst = conn.prepareStatement(sql);
//			ResultSet rs = pst.executeQuery();
//
//			while (rs.next()) {
//				String get_menu_name = rs.getNString(1);
//				String get_menu_cnt = rs.getString(3);
//				String get_total_price = rs.getString(4);
//
//				menuorderlistVO = new menuorderlistVO(get_menu_name, get_menu_cnt, get_total_price);
//				menuorderlistarr.add(menuorderlistVO);
//
//			}
//			
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//
//		return menuorderlistarr;
//	}

	// 리프레시로 메뉴이름별 카운트, 가격 메소드
	public ArrayList<menuorderlistVO> re_menuorderlist(String code_id) {
		ArrayList<menuorderlistVO> menuorderlistarr = new ArrayList<menuorderlistVO>();
		menuorderlistVO menuorderlistVO = null;
		String sql = null;
		try {

			getConn();

			sql = "SELECT arm.menu_name, " + code_id + ".price, cnt, total FROM A_RESTAURANT_MENU arm," + code_id
					+ " WHERE arm.MENU_ID = " + code_id + ".MENU_ID";
			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String get_menu_name = rs.getNString(1);
				String get_menu_cnt = rs.getString(3);
				String get_total_price = rs.getString(4);

				menuorderlistVO = new menuorderlistVO(get_menu_name, get_menu_cnt, get_total_price);
				menuorderlistarr.add(menuorderlistVO);

			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return menuorderlistarr;
	}

	// 주문리스트의 토탈 금액 확인하는 메소드
	public String totalprice(String code_id) {
		String get_total_price = null;
		try {
			getConn();
			String sql = "SELECT sum(price) AS total_price FROM A_DELIVERY_LIST WHERE CODE_ID = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, code_id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				get_total_price = rs.getString(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return get_total_price;
	}
	public void cnt_createview(String code_id) {

		try {

			getConn();

			String sql = "CREATE VIEW "+code_id+" AS SELECT user_id FROM A_DELIVERY_LIST adl WHERE CODE_ID = "+code_id+" GROUP BY user_id";

			pst = conn.prepareStatement(sql);

			csmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public String ordercomplete(String code_id) {
		String get_ordercomplete = null;
		cnt_createview(code_id);
		String sql = null;
		try {
			
			getConn();
			sql = "CREATE VIEW "+code_id+" AS SELECT user_id FROM A_DELIVERY_LIST adl WHERE CODE_ID = '"+code_id+"' GROUP BY user_id";
			csmt = conn.createStatement();
			csmt.executeUpdate(sql);
			
			getConn();
			sql = "SELECT COUNT(user_id) FROM "+code_id+"";
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			getConn();
			sql = "Drop view "+code_id+"";
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();

			if (rs.next()) {
				get_ordercomplete = rs.getString(1);

			}
			System.out.println(get_ordercomplete);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return get_ordercomplete;
	}
	

	public void payment(String code_id) {
		String sql = null;
		payVO vo = null;

		try {

			getConn();
			sql = "CREATE VIEW "+code_id+" AS SELECT user_id , sum(price) pricee FROM A_DELIVERY_LIST WHERE CODE_ID = '"+code_id+"' GROUP BY user_id";
			csmt = conn.createStatement();
			csmt.executeUpdate(sql);
			
			getConn();
			sql = "SELECT "+code_id+".USER_ID, adu.MONEY - PRICEE balance FROM "+code_id+" , A_DELIVERY_USER adu WHERE "+code_id+".USER_ID = adu.USER_ID";
			pst = conn.prepareStatement(sql);
			ResultSet rs =  pst.executeQuery();
			
			getConn();
			sql = "INSERT INTO A_ORDER_LOG (CODE_ID, USER_ID, MENU_ID, PRICE, ORDER_DATE) SELECT * FROM A_DELIVERY_LIST WHERE CODE_ID=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, code_id);
			pst.executeUpdate();
			
			getConn();
			sql = "Drop view "+code_id+"";
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();

			while (rs.next()) {
				String get_id = rs.getString(1);
				String get_balance = rs.getString(2);

				vo = new payVO(get_id, get_balance);
				charge(vo.getOrdered_id(), Integer.parseInt(vo.getBalance()));

			}
			deletelist(code_id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void deletelist(String code_id) {

		try {

			getConn();
			String sql = "DELETE FROM A_DELIVERY_LIST WHERE CODE_ID = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, code_id);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public userVO findId(String find_id) {
		System.out.println("친구찾기 디에이오");
		userVO vo = null;
		try {
			getConn();
			String sql = "select * from a_delivery_user where user_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, find_id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String get_id = rs.getString(1);
				String get_pw = rs.getString(2);
				String get_name = rs.getString(3);
				String get_birth = rs.getString(4);
				String get_gender = rs.getString(5);
				String get_tel = rs.getString(6);
				int get_money = Integer.parseInt(rs.getString(7));
				System.out.println(get_pw);
				if (find_id.equals(get_id)) {
					vo = new userVO(get_id, get_pw, get_name, get_birth, get_gender, get_tel, get_money);
					System.out.println(vo.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		System.out.println(vo);
		return vo;
	}

	public r_evaluationVO restaurant_info(String restaurant_id) {
		r_evaluationVO vo = null;
		
		try {
			getConn();
			
			String sql = "SELECT * FROM A_RESTAURANT WHERE RESTAURANT_ID = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, restaurant_id);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				String get_restaurant_name = rs.getString(2);
				int get_taste_good = rs.getInt(3);
				int get_taste_bad = rs.getInt(4);
				int get_amount_good = rs.getInt(5);
				int get_amount_bad = rs.getInt(6);
				int get_speed_good = rs.getInt(7);
				int get_speed_bad = rs.getInt(8);
				int get_review_total = rs.getInt(9);
				int get_master_percent = rs.getInt(10);
				
				vo = new r_evaluationVO(restaurant_id, get_restaurant_name, get_taste_good, get_taste_bad, get_amount_good, get_amount_bad, get_speed_good, get_speed_bad, get_review_total, get_master_percent);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return vo;
	}

}
