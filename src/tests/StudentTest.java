package tests;

import db.StudentTable;
import beans.Student;

public class StudentTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(!StudentTable.create()) {
			System.out.println("Error creating student table;");
		}

		String name = "name";
		String password = "password";
		String birthday = "01/01/2001";
		String email = "email";
		String phone = "phone";
		Student student = new Student(name, password, birthday, email, phone);

		Student s1 = StudentTable.insert(student);
		if(s1 == null) {
			System.out.println("Error inserting student");
		}
		
		Student s2 = StudentTable.selectByID(student.getId());
		if(s2 == null) {
			System.out.println("Couldn't find student with id " + s1.getId());
		} else {
			System.out.println("Found: " + s2.toString());
		}
		
		s2.setName("Anonymous");
		s2.setPhone("1234567890");
		
		Student s3 = StudentTable.update(s2);
		if(s3 == null) {
			System.out.println("Couldn't find student with id " + s2.getId());
		} else {
			System.out.println("Updated to: " + s3.toString());
		}
		
		if(!StudentTable.delete(s3)) {
			System.out.println("Couldn't delete student with id " + s3.getId());
		}
		
		if(!StudentTable.drop()) {
			System.out.println("Error dropping student table;");
		}

	}

}
