package tests;

import db.Course_StudentTable;
import beans.Course;
import beans.Student;
import beans.Course_Student;

import java.util.List;

public class Course_StudentTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(!Course_StudentTable.create()) {
			System.out.println("Error creating Course table;");
		}

		/* Make some students and courses */
		Student s1 = new Student("s1", "s1", "birthday", "email", "phone");
		Student s2 = new Student("s2", "s2", "birthday", "email", "phone");
		Student s3 = new Student("s3", "s3", "birthday", "email", "phone");
		Course c1 = new Course("c1", "c1", "desc", 2);
		Course c2 = new Course("c2", "c2", "desc", 2);
		Course c3 = new Course("c3", "c3", "desc", 2);

		/* Since just mocking, add ids to students */
		s1.setId(1);
		s2.setId(2);
		s3.setId(3);
		
		/* Populate some row objects for Course_Student */
		Course_Student cs1 = new Course_Student(c1, s1);
		Course_Student cs2 = new Course_Student(c1, s2);
		Course_Student cs3 = new Course_Student(c2, s3);
		Course_Student cs4 = new Course_Student(c2, s1);
		Course_Student cs5 = new Course_Student(c3, s2);
		
		if(!Course_StudentTable.insert(cs1)) {
			System.out.println("Error inserting cs1");
		}
		if(!Course_StudentTable.insert(cs2)) {
			System.out.println("Error inserting cs1");
		}
		if(!Course_StudentTable.insert(cs3)) {
			System.out.println("Error inserting cs1");
		}
		if(!Course_StudentTable.insert(cs4)) {
			System.out.println("Error inserting cs1");
		}
		if(!Course_StudentTable.insert(cs5)) {
			System.out.println("Error inserting cs1");
		}
		
		List<Course_Student> sList = Course_StudentTable.selectAllByCourse(c1);
		if(sList == null) {
			System.out.println("No students enrolled in course " + c1.getCode());
		} else {
			System.out.println("Found: " + sList.toString());
		}

		List<Course_Student> cList = Course_StudentTable.selectAllByStudent(s1);
		if(cList == null) {
			System.out.println("Student " + s1.getId() + 
					" not enrolled in any courses.");
		} else {
			System.out.println("Found: " + cList.toString());
		}
		
		
		if(!Course_StudentTable.delete(cs1)) {
			System.out.println("Couldn't delete student_course identified by " + 
					cs1.getcCode() + "," + cs1.getsId());
		}
		
		if(!Course_StudentTable.drop()) {
			System.out.println("Error dropping Course table;");
		}

	}

}
