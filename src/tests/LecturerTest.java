package tests;

import db.LecturerTable;
import beans.Lecturer;

public class LecturerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(!LecturerTable.create()) {
			System.out.println("Error creating lecturer table;");
		}

		String name = "name";
		String password = "password";
		Lecturer lecturer = new Lecturer(name, password);

		Lecturer l1 = LecturerTable.insert(lecturer);
		if(l1 == null) {
			System.out.println("Error inserting lecturer");
		}
		
		Lecturer l2 = LecturerTable.selectByName(lecturer.getName());
		if(l2 == null) {
			System.out.println("Couldn't find lecturer with id " + l1.getName());
		} else {
			System.out.println("Found: " + l2.toString());
		}
		
		l2.setPassword("Anonymous");
		
		Lecturer l3 = LecturerTable.update(l2);
		if(l3 == null) {
			System.out.println("Couldn't find lecturer with name " + l2.getName());
		} else {
			System.out.println("Updated to: " + l3.toString());
		}
		
		if(!LecturerTable.delete(l3)) {
			System.out.println("Couldn't delete lecturer with name " + l3.getName());
		}
		
		if(!LecturerTable.drop()) {
			System.out.println("Error dropping lecturer table;");
		}

	}

}
