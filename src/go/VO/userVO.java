package go.VO;

public class userVO {
	String id;
	String pw;
	String user_name;
	String birth;
	String gender;
	String tel;
	int money;
	
	public userVO(String id, String pw, String user_name, String birth, String gender, String tel, int money) {
		super();
		this.id = id;
		this.pw = pw;
		this.user_name = user_name;
		this.birth = birth;
		this.gender = gender;
		this.tel = tel;
		this.money = money;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "userVO [id=" + id + ", pw=" + pw + ", user_name=" + user_name + ", birth=" + birth + ", gender="
				+ gender + ", tel=" + tel + ", money=" + money + "]";
	}
	
	
	
	

}