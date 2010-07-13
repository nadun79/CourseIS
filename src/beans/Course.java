package beans;

public class Course {
	private String code;		// The course's code, this will be the primary key
	private String title;		// Title of the course
	private String description;	// Description of the course
	private int units;			// Number of units
	
	/* Fully Populated Course */
	public Course(String code, String title, String desc, int units) {
		this.code = code;
		this.title = title;
		this.description = desc;
		this.units = units;
	}
	
	/* Tabla Rasa */
	public Course() {
		// Intentionally blank
	}
	
	/* New Copy */
	public Course(Course c) {
		this.code = c.getCode();
		this.title = c.getTitle();
		this.description = c.getDesc();
		this.units = c.getUnits();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return description;
	}

	public void setDesc(String desc) {
		this.description = desc;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "Course [code=" + code + ", description=" + description + ", title=" + title
				+ ", units=" + units + "]";
	}
	
	/* Useful static attributes */
	public static final String TABLE_ID = "courses";
	
	public static final class Attribute {
		public static final String CODE = "code";
		public static final String TITLE = "title";
		public static final String DESCRIPTION = "description";
		public static final String UNITS = "units";
	}
	
}
