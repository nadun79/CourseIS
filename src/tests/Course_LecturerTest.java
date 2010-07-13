package tests;

import db.Course_LecturerTable;
import beans.Course;
import beans.Lecturer;
import beans.Course_Lecturer;

import java.util.List;

public class Course_LecturerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(!Course_LecturerTable.create()) {
			System.out.println("Error creating " + Course_Lecturer.TABLE_ID + " table;");
		}

		/* Make some students and courses */
		Lecturer l1 = new Lecturer("l1", "l1");
		Lecturer l2 = new Lecturer("l2", "l2");
		Lecturer l3 = new Lecturer("l3", "l3");
		Course c1 = new Course("c1", "c1", "desc", 2);
		Course c2 = new Course("c2", "c2", "desc", 2);
		Course c3 = new Course("c3", "c3", "desc", 2);
		
		/* Populate some row objects for Course_Student */
		Course_Lecturer cl1 = new Course_Lecturer(c1, l1);
		Course_Lecturer cl2 = new Course_Lecturer(c1, l2);
		Course_Lecturer cl3 = new Course_Lecturer(c2, l3);
		Course_Lecturer cl4 = new Course_Lecturer(c2, l1);
		Course_Lecturer cl5 = new Course_Lecturer(c3, l2);
		
		if(!Course_LecturerTable.insert(cl1)) {
			System.out.println("Error inserting cl1");
		}
		if(!Course_LecturerTable.insert(cl2)) {
			System.out.println("Error inserting cl2");
		}
		if(!Course_LecturerTable.insert(cl3)) {
			System.out.println("Error inserting cl3");
		}
		if(!Course_LecturerTable.insert(cl4)) {
			System.out.println("Error inserting cl4");
		}
		if(!Course_LecturerTable.insert(cl5)) {
			System.out.println("Error inserting cl5");
		}
		
		List<Course_Lecturer> sList = Course_LecturerTable.selectAllByCourse(c1);
		if(sList == null) {
			System.out.println("No Lecturers teaching course " + c1.getCode());
		} else {
			System.out.println("Found: " + sList.toString());
		}

		List<Course_Lecturer> cList = Course_LecturerTable.selectAllByLecturer(l1);
		if(cList == null) {
			System.out.println("Lecturer " + l1.getName() + 
					" not teaching any courses.");
		} else {
			System.out.println("Found: " + cList.toString());
		}
		
		
		if(!Course_LecturerTable.delete(cl1)) {
			System.out.println("Couldn't delete row from " + 
					Course_Lecturer.TABLE_ID + " identified by " + 
					cl1.getC_code() + "," + cl1.getL_name());
		}
		
		if(!Course_LecturerTable.drop()) {
			System.out.println("Error dropping " + Course_Lecturer.TABLE_ID + " table;");
		}

	}

}
