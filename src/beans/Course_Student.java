package beans;

public class Course_Student {
	private String cCode;	// A course code
	private int sId;		// A student id
	
	/* Construct with existing course and student */
	public Course_Student(Course c, Student s) {
		this.cCode = c.getCode();
		this.sId = s.getId();
	}
	
	/* Construct with known course and student IDs*/
	public Course_Student(String c_code, int s_id) {
		this.cCode = c_code;
		this.sId = s_id;
	}
	
	/* Only getters since we'll just drop or add rows to change things */
	public String getcCode() {
		return cCode;
	}
	public int getsId() {
		return sId;
	}

	@Override
	public String toString() {
		return "Course_Student [cCode=" + cCode + ", sId=" + sId + "]";
	}
	
	
}
