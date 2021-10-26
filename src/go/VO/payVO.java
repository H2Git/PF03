package go.VO;

public class payVO {
	String ordered_id;
	String balance;
	public payVO(String ordered_id, String balance) {
		super();
		this.ordered_id = ordered_id;
		this.balance = balance;
	}
	public String getOrdered_id() {
		return ordered_id;
	}
	public void setOrdered_id(String ordered_id) {
		this.ordered_id = ordered_id;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "payVO [ordered_id=" + ordered_id + ", balance=" + balance + "]";
	}
	
	
	
	
}
