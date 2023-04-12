package register;
public class Register {
	private String email;
	private String position;
	private String username;
	private String password;
	private String confirm_password;
	
	public Register(String email, String position, String username, String password, String confirm_password) {
		super();
		this.email = email;
		this.position = position;
		this.username = username;
		this.password = password;
		this.confirm_password = confirm_password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	
}
