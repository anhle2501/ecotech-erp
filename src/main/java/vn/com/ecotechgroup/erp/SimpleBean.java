package vn.com.ecotechgroup.erp;

public class SimpleBean {
	private String username;


	public SimpleBean(String username2) {
		// TODO Auto-generated constructor stub
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
