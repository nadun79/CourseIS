package beans;

public class Lecturer {
	private String name;		// This will be unique to keep things simple
	private String password;	// The lecturer's password
	
	/* Fully Populated Lecturer */
	public Lecturer(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	/* Tabla Rasa */
	public Lecturer() {
		// Intentionally blank
	}
	
	/* New Copy */
	public Lecturer(Lecturer s) {
		this.name = s.getName();
		this.password = s.getPassword();
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
		return "Lecturer [name=" + name + ", password=" + password + "]";
	}

	/* Useful static attributes */
	public static final String TABLE_ID = "lecturers";
	
	public static final class Attribute {
		public static final String NAME = "name";
		public static final String PASSWORD = "password";
	}
	
}
