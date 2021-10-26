package go.VO;

public class menuorderlistVO {
	
	String menu_name;
	String menu_cnt;
	String menu_total_price;
	public menuorderlistVO(String menu_name, String menu_cnt, String menu_total_price) {
		super();
		this.menu_name = menu_name;
		this.menu_cnt = menu_cnt;
		this.menu_total_price = menu_total_price;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_cnt() {
		return menu_cnt;
	}
	public void setMenu_cnt(String menu_cnt) {
		this.menu_cnt = menu_cnt;
	}
	public String getMenu_total_price() {
		return menu_total_price;
	}
	public void setMenu_total_price(String menu_total_price) {
		this.menu_total_price = menu_total_price;
	}
	@Override
	public String toString() {
		return "menuorderlistVO [menu_name=" + menu_name + ", menu_cnt=" + menu_cnt + ", menu_total_price="
				+ menu_total_price + "]";
	}
	
	
	
	

}
