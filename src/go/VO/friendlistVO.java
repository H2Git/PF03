package go.VO;

public class friendlistVO {
	String friend_id;
	String friend_name;
	String friend_tel;
	public friendlistVO(String friend_id, String friend_name, String friend_tel) {
		super();
		this.friend_id = friend_id;
		this.friend_name = friend_name;
		this.friend_tel = friend_tel;
	}
	public String getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}
	public String getFriend_name() {
		return friend_name;
	}
	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}
	public String getFriend_tel() {
		return friend_tel;
	}
	public void setFriend_tel(String friend_tel) {
		this.friend_tel = friend_tel;
	}
	@Override
	public String toString() {
		return "friendlistVO [friend_id=" + friend_id + ", friend_name=" + friend_name + ", friend_tel=" + friend_tel
				+ "]";
	}
	
	
	
	
	
	

}
