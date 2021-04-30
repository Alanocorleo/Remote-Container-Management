package management;

public final class User {

	private String username;
	private String password;
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void load(ClientDatabase c) {
		
		if (this.username.equals("company")) {
			this.password = username;
			
		} 
		
		else {
		c.print();
		System.out.println(this.username);

		this.password = c.getPassword(this.username);
		System.out.println(this.password);
		}
		// dummy: this means that password and username have to be the same
		
	}
	
	public boolean passwordMatches(String password) {
		return (this.password.equals(password));
	}
	
}
