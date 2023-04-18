package profile;

public class User {
	private String username;
	private String role;
	private String password;
	private String name;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String username, String role, String password, String name) {
		super();
		this.username = username;
		this.role = role;
		this.password = password;
		this.name = name;
	}

	public String getUsername() {
		return username;
	}


	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return role;
	}

	public void setEmail(String email) {
		this.role = email;
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
