package go.VO;

public class menulistVO {

	String menu_id;
	String restaurant_name;
	String menu_name;
	String price;
	String image_url;
	int TASTE_GOOD_CNT;
	int TASTE_BAD_CNT;
	int AMOUNT_GOOD_CNT;
	int AMOUNT_BAD_CNT;

	public menulistVO(String menu_id, String restaurant_name, String menu_name, String price, String image_url,
			int tASTE_GOOD_CNT, int tASTE_BAD_CNT, int aMOUNT_GOOD_CNT, int aMOUNT_BAD_CNT) {
		super();
		this.menu_id = menu_id;
		this.restaurant_name = restaurant_name;
		this.menu_name = menu_name;
		this.price = price;
		this.image_url = image_url;
		TASTE_GOOD_CNT = tASTE_GOOD_CNT;
		TASTE_BAD_CNT = tASTE_BAD_CNT;
		AMOUNT_GOOD_CNT = aMOUNT_GOOD_CNT;
		AMOUNT_BAD_CNT = aMOUNT_BAD_CNT;
	}

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public String getRestaurant_name() {
		return restaurant_name;
	}

	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
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

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public int getTASTE_GOOD_CNT() {
		return TASTE_GOOD_CNT;
	}

	public void setTASTE_GOOD_CNT(int tASTE_GOOD_CNT) {
		TASTE_GOOD_CNT = tASTE_GOOD_CNT;
	}

	public int getTASTE_BAD_CNT() {
		return TASTE_BAD_CNT;
	}

	public void setTASTE_BAD_CNT(int tASTE_BAD_CNT) {
		TASTE_BAD_CNT = tASTE_BAD_CNT;
	}

	public int getAMOUNT_GOOD_CNT() {
		return AMOUNT_GOOD_CNT;
	}

	public void setAMOUNT_GOOD_CNT(int aMOUNT_GOOD_CNT) {
		AMOUNT_GOOD_CNT = aMOUNT_GOOD_CNT;
	}

	public int getAMOUNT_BAD_CNT() {
		return AMOUNT_BAD_CNT;
	}

	public void setAMOUNT_BAD_CNT(int aMOUNT_BAD_CNT) {
		AMOUNT_BAD_CNT = aMOUNT_BAD_CNT;
	}

	@Override
	public String toString() {
		return "menulistVO [menu_id=" + menu_id + ", restaurant_name=" + restaurant_name + ", menu_name=" + menu_name
				+ ", price=" + price + ", image_url=" + image_url + ", TASTE_GOOD_CNT=" + TASTE_GOOD_CNT
				+ ", TASTE_BAD_CNT=" + TASTE_BAD_CNT + ", AMOUNT_GOOD_CNT=" + AMOUNT_GOOD_CNT + ", AMOUNT_BAD_CNT="
				+ AMOUNT_BAD_CNT + "]";
	}

}
