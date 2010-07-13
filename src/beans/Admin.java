package beans;

public class Admin {
	private String name;		// This will be unique to keep things simple
	private String password;	// The lecturer's password
	
	/* Fully Populated Lecturer */
	public Admin(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	/* Tabla Rasa */
	public Admin() {
		// Intentionally blank
	}
	
	/* New Copy */
	public Admin(Admin a) {
		this.name = a.getName();
		this.password = a.getPassword();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [name=" + name + ", password=" + password + "]";
	}

	/* Useful static attributes */
	public static final String TABLE_ID = "admins";
	
	public static final class Attribute {
		public static final String NAME = "name";
		public static final String PASSWORD = "password";
	}
	
}
