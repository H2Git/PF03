package go.VO;

public class restaurantlistVO {

	String category;
	String restaurant_id;
	String restaurant_name;

	public restaurantlistVO(String category, String restaurant_id, String restaurant_name) {
		super();
		this.category = category;
		this.restaurant_id = restaurant_id;
		this.restaurant_name = restaurant_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getRestaurant_name() {
		return restaurant_name;
	}

	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}

	@Override
	public String toString() {
		return "restaurantlistVO [category=" + category + ", restaurant_id=" + restaurant_id + ", restaurant_name="
				+ restaurant_name + "]";
	}

}
