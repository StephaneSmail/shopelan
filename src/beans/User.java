package beans;

public class User {

	private String username;
	private String password;
	private String email;
	
	/**
	 * @param username
	 * @param password
	 */
	public User(String username, String email) {
		this.username = username;
		this.email = email;
	}
	
	public User(String username, String email, String password) {
		
		this.username = username;
		this.email = email;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [username=" + username + "]";
	}
	
	
}
