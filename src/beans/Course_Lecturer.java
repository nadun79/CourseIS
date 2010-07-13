package beans;

public class Course_Lecturer {
	private String c_code;	// A course code
	private String l_name;	// A lecturer name
	
	/* Construct with existing course and student */
	public Course_Lecturer(Course c, Lecturer l) {
		this.c_code = c.getCode();
		this.l_name = l.getName();
	}
	
	/* Construct with known course and student IDs*/
	public Course_Lecturer(String c_code, String l_name) {
		this.c_code = c_code;
		this.l_name = l_name;
	}

	public String getC_code() {
		return c_code;
	}

	public String getL_name() {
		return l_name;
	}

	@Override
	public String toString() {
		return "Course_Lecturer [c_code=" + c_code + ", l_name=" + l_name + "]";
	}
	
	/* Useful static attributes */
	public static final String TABLE_ID = "course_lecturer";
	
	public static final class Attribute {

		public static final String C_CODE = "c_code";
		public static final String L_NAME = "l_name";
	}
}
