package vn.com.ecotechgroup.erp;

public class SimpleBean2 {
	private String username;


	public SimpleBean2(String username2) {
	    this.username = username2;
	}

	@Override
	public String toString() {
		return "This is a simple bean, name: " + username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
