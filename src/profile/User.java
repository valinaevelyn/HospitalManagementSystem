package profile;

public class User {
	private String username;
	private String email;
	private String password;
	private String name;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String username, String email, String password, String name) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean validatePassword(String password) {
		if(password.compareTo(this.password) == 0) {
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
