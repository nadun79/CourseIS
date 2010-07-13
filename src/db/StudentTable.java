package db;

import java.sql.ResultSet;
import java.util.Formatter;


import beans.Student;

public class StudentTable {

	public static Boolean create() {
		String idField = "id INTEGER NOT NULL AUTO_INCREMENT";
		String nameField = "name VARCHAR(40) NOT NULL";
		String passwordField = "password VARCHAR(32) NOT NULL";
		String birthdayField = "birthday VARCHAR(10) NOT NULL";
		String emailField = "email VARCHAR(40) NOT NULL";
		String phoneField = "phone VARCHAR(10) NOT NULL";
		
		Formatter fields = new Formatter();
		fields.format("%s, %s, %s, %s, %s, %s", idField, nameField, 
				passwordField, birthdayField, emailField, phoneField);
		
		Formatter f = new Formatter();
		f.format("CREATE TABLE students(%s, " +
				"PRIMARY KEY (id))", fields.toString());
		
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			System.out.println("Created Student Table");
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
		return true;
	}
	
	public static Student insert(Student student) {
		String query = "INSERT INTO students " +
		"(name, password, birthday, email, phone) VALUES " +
		"('%s', '%s', '%s', '%s', '%s')";
		

		Formatter f = new Formatter();
		f.format(query, student.getName(), student.getPassword(),
				student.getBirthday(), student.getEmail(), student.getPhone());
				
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			System.out.println("Added Row to Student Table");
			db.close();
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
		/* Get Id of newly added student */
		ResultSet result;
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT id FROM students");
			if(!result.last()) {	// No such row
				db.close();
				return null;
			}
			student.setId(result.getInt("id"));
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		return new Student(student);
	}
	
	public static Student selectByID(Integer id) {
		ResultSet result;
		Student student;
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT * FROM students WHERE id = '" + id + "'");
			
			/* Populate a new student object */
			if(!result.first()) {	// No such row
				db.close();
				return null;
			}

			student = new Student();
			student.setId(result.getInt("id"));
			student.setBirthday(result.getString("birthday"));
			student.setEmail(result.getString("email"));
			student.setName(result.getString("name"));
			student.setPassword(result.getString("password"));
			student.setPhone(result.getString("phone"));
			
			//System.out.println("Retrieved Student with ID = " + student.getId());
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		
		return student;
	}
	
	/* ID should never be changed manually, other values will be updated */
	public static Student update(Student student) {
		String query = "UPDATE students SET " +
		"name = '%s', password = '%s', birthday = '%s', email = '%s', phone = '%s'" +
		"WHERE id = '%s'";

		Formatter f = new Formatter();
		f.format(query, student.getName(), student.getPassword(),
				student.getBirthday(), student.getEmail(), student.getPhone(), 
				student.getId());
		
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			System.out.println("Updated Row in Student Table");
			db.close();
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
		return new Student(student);
	}

	public static Boolean delete(Student student) {
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute("DELETE FROM students " +
					"WHERE id = '" + student.getId() + "'");
			System.out.println("Removed Student");
			db.close();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static Boolean drop() {
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute("DROP TABLE students");
			System.out.println("Dropped Student Table");
			db.close();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
}
