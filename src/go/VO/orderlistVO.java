package go.VO;

public class orderlistVO {
	
	String orderer;
	String menu_name;
	String price;
	public orderlistVO(String orderer, String menu_name, String price) {
		super();
		this.orderer = orderer;
		this.menu_name = menu_name;
		this.price = price;
	}
	public String getOrderer() {
		return orderer;
	}
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "orderlistVO [orderer=" + orderer + ", menu_name=" + menu_name + ", price=" + price + "]";
	}
	
	
	

}
