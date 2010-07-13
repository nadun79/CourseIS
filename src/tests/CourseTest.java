package tests;

import db.CourseTable;
import beans.Course;

public class CourseTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(!CourseTable.create()) {
			System.out.println("Error creating Course table;");
		}

		String code = "code";
		String title = "title";
		String description = "description";
		int units = 2;
		Course course = new Course(code, title, description, units);

		Course c1 = CourseTable.insert(course);
		if(c1 == null) {
			System.out.println("Error inserting course");
		}
		
		Course c2 = CourseTable.selectByCode(course.getCode());
		if(c2 == null) {
			System.out.println("Couldn't find course with code " + c1.getCode());
		} else {
			System.out.println("Found: " + c2.toString());
		}
		
		c2.setTitle("Foobar");
		
		Course c3 = CourseTable.update(c2);
		if(c3 == null) {
			System.out.println("Couldn't find course with code " + c2.getCode());
		} else {
			System.out.println("Updated to: " + c3.toString());
		}
		
		if(!CourseTable.delete(c3)) {
			System.out.println("Couldn't delete course with code " + c3.getCode());
		}
		
		if(!CourseTable.drop()) {
			System.out.println("Error dropping Course table;");
		}

	}

}
